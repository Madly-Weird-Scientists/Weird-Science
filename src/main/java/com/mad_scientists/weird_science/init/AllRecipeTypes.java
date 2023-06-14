package com.mad_scientists.weird_science.init;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.block.modification_station.Modification;
import com.mad_scientists.weird_science.block.tinkers_table.TinkeringRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllRecipeTypes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, WeirdScience.ID);

    public static final RegistryObject<RecipeSerializer<TinkeringRecipe>> TINKERING =
            SERIALIZERS.register("tinkering", () -> TinkeringRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<Modification>> MODIFICATION =
            SERIALIZERS.register("modification", () -> Modification.Serializer.INSTANCE);
    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
