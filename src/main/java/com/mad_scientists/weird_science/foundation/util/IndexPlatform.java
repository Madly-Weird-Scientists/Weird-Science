package com.mad_scientists.weird_science.foundation.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.util.NonNullSupplier;

import java.util.function.Supplier;

public class IndexPlatform {

    public static Supplier<RecipeSerializer<?>> registerRecipeSerializer(ResourceLocation id, NonNullSupplier<RecipeSerializer<?>> sup) {
        throw new AssertionError();
    }
}
