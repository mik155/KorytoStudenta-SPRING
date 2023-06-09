package com.app.learningcards.models.recipe;

import com.app.learningcards.models.Ingriedient;
import com.app.learningcards.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Recipes")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recipe
{
    @Id
    @SequenceGenerator(
            name = "recipe_sequence",
            sequenceName = "recipe_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "recipe_sequence"
    )
    public Long id;
    @Enumerated(EnumType.STRING)
    protected RecipeCategory category;
    protected String name;
    protected String description;
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    public List<Ingriedient> ingredients;
    protected int minutesToMake;
    protected int ingriedientNumber;
    protected int likes;
    protected String photoPath;
    protected Long creatorId;

    public boolean equals(Object obj)
    {
        if(! (obj instanceof Recipe other))
            return false;
        return Objects.equals(other.id, id);
    }
}