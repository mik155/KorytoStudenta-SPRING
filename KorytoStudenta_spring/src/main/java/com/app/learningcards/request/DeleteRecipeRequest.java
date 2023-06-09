package com.app.learningcards.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRecipeRequest extends RecipesByCategory
{
    private Long recipeId;
}
