package com.mad_scientists.weird_science.integration.jei;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.content.block.modification_station.Modification;
import mezz.jei.api.recipe.RecipeType;

public class WeirdScienceRecipeTypes {
    public static final RecipeType<Modification> MODIFICATION = RecipeType.create(WeirdScience.ID, "cooking", Modification.class);
}
