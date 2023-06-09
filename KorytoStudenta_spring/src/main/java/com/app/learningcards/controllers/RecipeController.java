package com.app.learningcards.controllers;

import java.util.List;
import java.util.Objects;

import com.app.learningcards.models.User;
import com.app.learningcards.models.recipe.Recipe;
import com.app.learningcards.request.DeleteRecipeRequest;
import com.app.learningcards.request.NewRecipeRequest;
import com.app.learningcards.request.RecipesByCategory;
import com.app.learningcards.services.RecipeService;
import com.app.learningcards.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static jakarta.persistence.GenerationType.UUID;

@RestController
@RequestMapping(path = "/api/v1/recipe")
@AllArgsConstructor
public class RecipeController
{
    private RecipeService recipeService;
    private UserService userService;

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@RequestBody NewRecipeRequest request, @AuthenticationPrincipal User user)
    {
        Recipe recipe = Recipe.builder()
                .name(request.getName())
                .category(request.getCategory())
                .description(request.getDescription())
                .ingredients(request.getIngredients())
                .minutesToMake(request.getMinutesToMake())
                .ingriedientNumber(request.getIngredients() != null ? request.getIngredients().size() : 0)
                .likes(0)
                .photoPath(request.getPhotoPath())
                .creatorId(user.getId())
                .build();
        Recipe newRecipe = recipeService.saveRecipe(recipe);
        return ResponseEntity.ok(newRecipe);
    }

    @GetMapping(path="/all")
    public ResponseEntity<List<Recipe>> getAllRecipes()
    {
        return new ResponseEntity<>(recipeService.getAllRecipes(), HttpStatus.OK);
    }

    @PostMapping(path="/load")
    public ResponseEntity<List<Recipe>> getRecipesByCategory(@RequestBody RecipesByCategory categories,
                                                             @AuthenticationPrincipal User user)
    {
        if(categories.isUserRecipes() && user != null)
        {
            return new ResponseEntity<>(recipeService.getRecipes(categories.getCategories(), user), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(recipeService.getRecipes(categories.getCategories()), HttpStatus.OK);
        }
    }

    @PutMapping(path="/description/{recipeId}")
    public ResponseEntity<Recipe>  changeRecipe(@PathVariable("recipeId") Long recipeId,
                                                @RequestParam(value = "description", required = true) String description)
    {
            Recipe recipe = recipeService.getRecipe(recipeId);
            recipe.setDescription(description);
            recipeService.saveRecipe(recipe);
            return ResponseEntity.ok(recipe);
    }

    @PutMapping(path="/like/{recipeId}")
    public void  likeRecipe(@PathVariable("recipeId") Long recipeId, @AuthenticationPrincipal User user)
    {
        Recipe recipe = recipeService.getRecipe(recipeId);
        if(!user.getFavRecipes().contains(recipe))
        {
            recipe.setLikes(recipe.getLikes() + 1);
            user.favRecipes.add(recipe);
            user.setFavRecipes(user.getFavRecipes());
            userService.saveUserData(user);
        }
    }

    @PutMapping(path="/dislike/{recipeId}")
    public void  dislikeRecipe(@PathVariable("recipeId") Long recipeId, @AuthenticationPrincipal User user)
    {

        Recipe recipe = recipeService.getRecipe(recipeId);
        if(user.getFavRecipes().contains(recipe))
        {
            recipe.setLikes(recipe.getLikes() - 1);
            user.favRecipes.removeIf( r -> Objects.equals(r.id, recipe.getId()));
            user.setFavRecipes(user.getFavRecipes());
            recipeService.saveRecipe(recipe);
            userService.saveUserData(user);
        }
    }

    @PostMapping(path="/delete")
    public void deleteFile(@RequestBody DeleteRecipeRequest request, @AuthenticationPrincipal User user)
    {
        recipeService.deleteRecipe(request.getRecipeId(), request.getCategories(), user);
    }

    private User getLoggedInUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null)
            return null;
        return userService.getUser(auth.getName());
    }
}
