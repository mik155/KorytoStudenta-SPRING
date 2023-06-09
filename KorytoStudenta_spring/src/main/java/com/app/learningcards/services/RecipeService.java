package com.app.learningcards.services;

import com.app.learningcards.models.User;
import com.app.learningcards.models.recipe.Recipe;
import com.app.learningcards.models.recipe.RecipeCategory;
import com.app.learningcards.repository.RecipeRepository;
import com.app.learningcards.request.RecipesByCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecipeService
{
    private RecipeRepository recipeRepository;

    public Recipe getRecipe(Long id)
    {
        Recipe recipe = recipeRepository.getReferenceById(id);
        return recipe;
    }
    public List<Recipe> getRecipes(RecipeCategory category)
    {
        return recipeRepository.findByCategory(category);
    }

    public List<Recipe> getRecipes(List<RecipeCategory> categories)
    {
        List<Recipe> recipes = new LinkedList<>();
        if(categories != null)
            categories.forEach( c ->
            {
                recipes.addAll(recipeRepository.findByCategory(c));
            });
        return recipes;
    }
    public List<Recipe> getRecipes(List<RecipeCategory> categories, User user)
    {
        List<Recipe> recipes = new LinkedList<>();
        if(categories != null)
            categories.forEach( c ->
            {
                recipes.addAll(recipeRepository.findByCategory(c));
            });

        if(user == null)
            return recipes;

        if(user.getFavRecipes() != null)
            recipes.addAll(user.getFavRecipes());

        return recipes;
    }

    public Recipe saveRecipe(Recipe recipe)
    {
        return recipeRepository.save(recipe);
    }

    public List<Recipe> getAllRecipes()
    {
        return recipeRepository.findAll();
    }

    public List<Recipe> deleteRecipe(Long recipeId, List<RecipeCategory> categories, User user)
    {
        if(Objects.equals(recipeRepository.getReferenceById(recipeId).getCreatorId(), user.getId()))
            recipeRepository.deleteById(recipeId);
        return getRecipes(categories, user);
    }


}