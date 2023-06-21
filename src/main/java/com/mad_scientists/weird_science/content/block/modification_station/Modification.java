package com.mad_scientists.weird_science.content.block.modification_station;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mad_scientists.weird_science.WeirdScience;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SmithingTableBlock;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class Modification implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack base;
    private final Ingredient addition;
    final int flux;
    final int warp;
    final int quanta;

    public Modification(ResourceLocation id, ItemStack base, Ingredient addition, int flux, int warp, int quanta) {
        this.id = id;
        this.base = base;           // item to modify
        this.addition = addition;   // ingredient key
        this.flux = flux;           // added flux
        this.warp = warp;           // added warp
        this.quanta = quanta;       // added quanta
    }
    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        return Ingredient.of(this.base).test(pContainer.getItem(0))
                && this.addition.test(pContainer.getItem(1));
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        ItemStack itemstack = this.base.copy();
        CompoundTag compoundtag = pContainer.getItem(0).getTag();
        if (compoundtag != null) {
            itemstack.setTag(compoundtag.copy());
        }
        CompoundTag tag = itemstack.getOrCreateTag();

        int flux = tag.getInt("Flux");
        tag.putInt("Flux", flux + this.flux);

        int warp = tag.getInt("Warp");
        tag.putInt("Warp", warp + this.warp);

        int quanta = tag.getInt("Quanta");
        tag.putInt("Quanta", quanta + this.quanta);

        return itemstack;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.base.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Modification.Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Modification.Type.INSTANCE;
    }

    public static class Type implements RecipeType<Modification> {
        private Type() { }
        public static final Modification.Type INSTANCE = new Modification.Type();
        public static final String ID = "modification";
    }

    public static class Serializer implements RecipeSerializer<Modification> {
        public static final Modification.Serializer INSTANCE = new Modification.Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(WeirdScience.ID,"modification");

        @Override
        public Modification fromJson(ResourceLocation id, JsonObject json) {
            ItemStack base = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "base"));
            Ingredient addition = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "addition"));
            int flux = GsonHelper.getAsInt(json, "flux");
            int warp = GsonHelper.getAsInt(json, "warp");
            int quanta = GsonHelper.getAsInt(json, "quanta");
            return new Modification(id, base, addition, flux, warp, quanta);
        }

        @Override
        public Modification fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            ItemStack base = buf.readItem();
            Ingredient addition = Ingredient.fromNetwork(buf);
            int flux = buf.readInt();
            int warp = buf.readInt();
            int quanta = buf.readInt();
            return new Modification(id, base, addition, flux, warp, quanta);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, Modification recipe) {
            buf.writeItem(recipe.base);
            recipe.addition.toNetwork(buf);
            buf.writeInt(recipe.flux);
            buf.writeInt(recipe.warp);
            buf.writeInt(recipe.quanta);
        }

        @Override
        public RecipeSerializer<?> setRegistryName(ResourceLocation name) {
            return INSTANCE;
        }

        @Nullable
        @Override
        public ResourceLocation getRegistryName() {
            return ID;
        }

        @Override
        public Class<RecipeSerializer<?>> getRegistryType() {
            return Modification.Serializer.castClass();
        }

        @SuppressWarnings("unchecked") // Need this wrapper, because generics
        private static <G> Class<G> castClass() {
            return (Class<G>) RecipeSerializer.class;
        }
    }
}
