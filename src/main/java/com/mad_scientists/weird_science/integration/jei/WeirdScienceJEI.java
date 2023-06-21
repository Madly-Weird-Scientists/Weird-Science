package com.mad_scientists.weird_science.integration.jei;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.content.block.modification_station.Modification;
import com.mad_scientists.weird_science.content.block.modification_station.ModificationMenu;
import com.mad_scientists.weird_science.content.block.modification_station.ModificationScreen;
import com.mad_scientists.weird_science.init.AllBlocks;
import com.mad_scientists.weird_science.init.AllRecipeTypes;
import com.mad_scientists.weird_science.integration.jei.categories.ModificationCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

@JeiPlugin
@SuppressWarnings("removal")
public class WeirdScienceJEI implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(WeirdScience.ID, "jei_plugin");
    }
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                ModificationCategory(registration.getJeiHelpers().getGuiHelper()));
    }
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<Modification> recipes = rm.getAllRecipesFor(Modification.Type.INSTANCE);
        registration.addRecipes(new RecipeType<>(ModificationCategory.UID, Modification.class), recipes);
    }
    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(AllBlocks.MODIFICATION_STATION.get()), WeirdScienceRecipeTypes.MODIFICATION);
    }
    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(ModificationScreen.class, 100, 56, 128, 45, WeirdScienceRecipeTypes.MODIFICATION);
    }
    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(ModificationMenu.class, WeirdScienceRecipeTypes.MODIFICATION, 0, 2, 9, 36);
    }
}
