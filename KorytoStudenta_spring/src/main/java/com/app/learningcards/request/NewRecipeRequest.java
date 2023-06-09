package com.app.learningcards.request;

import com.app.learningcards.models.Ingriedient;
import com.app.learningcards.models.User;
import com.app.learningcards.models.recipe.RecipeCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewRecipeRequest
{
    protected RecipeCategory category;
    protected String name;
    protected String description;
    public List<Ingriedient> ingredients;
    protected int minutesToMake;
    protected int ingriedientNumber;
    protected User author;
    protected String photoPath;
}
