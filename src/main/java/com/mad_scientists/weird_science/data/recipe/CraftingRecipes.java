package com.mad_scientists.weird_science.data.recipe;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.init.AllBlocks;
import com.mad_scientists.weird_science.init.AllItems;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

public class CraftingRecipes {
    public CraftingRecipes() {
    }
    public static String Q = "###";
    public static String ID = WeirdScience.ID;
    public static void register(Consumer<FinishedRecipe> consumer) {
        recipesBlocks(consumer);
    }
    public static void recipesBlocks(Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped((ItemLike) AllBlocks.DARKENED_ALUMINUM_PANEL.get(), 4)
                .pattern("##")
                .pattern("##")
                .define('#', (ItemLike) AllItems.DARKENED_ALUMINUM.get())
                .unlockedBy("has_" + AllItems.DARKENED_ALUMINUM.getId(),
                        InventoryChangeTrigger.TriggerInstance.hasItems((ItemLike) AllItems.DARKENED_ALUMINUM.get()))
                .save(consumer, new ResourceLocation(ID, AllBlocks.DARKENED_ALUMINUM_PANEL.getId().getPath()));
        ShapedRecipeBuilder.shaped((ItemLike) AllBlocks.ENERGIZED_ALUMINUM_PANEL.get(), 4)
                .pattern("##")
                .pattern("##")
                .define('#', (ItemLike) AllItems.ENERGIZED_ALUMINUM.get())
                .unlockedBy("has_" + AllItems.ENERGIZED_ALUMINUM.getId(),
                        InventoryChangeTrigger.TriggerInstance.hasItems((ItemLike) AllItems.ENERGIZED_ALUMINUM.get()))
                .save(consumer, new ResourceLocation(ID, AllBlocks.ENERGIZED_ALUMINUM_PANEL.getId().getPath()));
    }
}
