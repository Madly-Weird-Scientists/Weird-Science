package com.mad_scientists.weird_science.block.modification_station;

import com.google.gson.JsonObject;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.stream.Stream;

import static com.mad_scientists.weird_science.block.modification_station.Modification.getItemStack;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class ModificationRecipe implements Recipe<Container> {
    final Ingredient base;
    final Ingredient addition;
    final ItemStack result;
    final Integer flux;
    final Integer warp;
    final Integer quanta;
    private final ResourceLocation id;


    public ModificationRecipe(ResourceLocation resourceLocation, Ingredient base, Ingredient addition, ItemStack result, Integer flux, Integer warp, Integer quanta) {
        this.id = resourceLocation;
        this.base = base;
        this.addition = addition;
        this.result = result;
        this.flux = flux;
        this.warp = warp;
        this.quanta = quanta;
    }

    public boolean matches(Container container, Level level) {
        return this.base.test(container.getItem(0)) && this.addition.test(container.getItem(1));
    }

    public ItemStack assemble(Container container) {
        ItemStack itemstack = this.result.copy();
        return getItemStack(itemstack, container.getItem(0), flux, warp, quanta);
    }

    public boolean canCraftInDimensions(int i, int i1) {
        return i * i1 >= 2;
    }

    public ItemStack getResultItem() {
        return this.result;
    }

    public boolean isAdditionIngredient(ItemStack stack) {
        return this.addition.test(stack);
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(Blocks.SMITHING_TABLE);
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializer.SMITHING;
    }

    public RecipeType<?> getType() {
        return RecipeType.SMITHING;
    }

    public boolean isIncomplete() {
        return Stream.of(this.base, this.addition).anyMatch(ForgeHooks::hasNoElements);
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<ModificationRecipe> {
        public ModificationRecipe fromJson(ResourceLocation location, JsonObject jsonObject) {
            Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(jsonObject, "base"));
            Ingredient ingredient1 = Ingredient.fromJson(GsonHelper.getAsJsonObject(jsonObject, "addition"));
            ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
            Integer flux = GsonHelper.getAsInt(jsonObject, "flux");
            Integer warp = GsonHelper.getAsInt(jsonObject, "warp");
            Integer quanta = GsonHelper.getAsInt(jsonObject, "quanta");
            return new ModificationRecipe(location, ingredient, ingredient1, itemstack, flux, warp, quanta);
        }

        public ModificationRecipe fromNetwork(ResourceLocation location, FriendlyByteBuf buf) {
            Ingredient ingredient = Ingredient.fromNetwork(buf);
            Ingredient ingredient1 = Ingredient.fromNetwork(buf);
            ItemStack itemstack = buf.readItem();
            Integer flux = buf.readInt();
            Integer warp = buf.readInt();
            Integer quanta = buf.readInt();
            return new ModificationRecipe(location, ingredient, ingredient1, itemstack, flux, warp, quanta);
        }

        public void toNetwork(FriendlyByteBuf buf, ModificationRecipe modificationRecipe) {
            modificationRecipe.base.toNetwork(buf);
            modificationRecipe.addition.toNetwork(buf);
            modificationRecipe.flux.intValue();
            modificationRecipe.warp.intValue();
            modificationRecipe.quanta.intValue();
            buf.writeItem(modificationRecipe.result);
        }
    }
}
