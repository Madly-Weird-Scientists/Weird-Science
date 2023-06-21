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
    private final ItemStack output;
    private final Ingredient base;
    private final Ingredient addition;
    final Integer flux;
    final Integer warp;
    final Integer quanta;
    public Modification(ResourceLocation id, ItemStack output,
                        Ingredient base, Ingredient addition, Integer flux, Integer warp, Integer quanta) {
        this.id = id;
        this.output = output;
        this.base = base;
        this.addition = addition;
        this.flux = flux;
        this.warp = warp;
        this.quanta = quanta;
    }
    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        return this.base.test(pContainer.getItem(0)) && this.addition.test(pContainer.getItem(1));
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        ItemStack itemstack = this.output.copy();
        CompoundTag compoundtag = pContainer.getItem(0).getOrCreateTag();
        int baseFlux = compoundtag.getInt("Flux");
        output.getOrCreateTag().putInt("Flux", baseFlux + flux);
        int baseWarp = compoundtag.getInt("Warp");
        output.getOrCreateTag().putInt("Warp", baseWarp + warp);
        int baseQuanta = compoundtag.getInt("Quanta");
        output.getOrCreateTag().putInt("Quanta", baseQuanta + warp);
        itemstack.setTag(compoundtag.copy());
        String basePrimary = compoundtag.getString("Primary");
        String baseSecondary = compoundtag.getString("Secondary");
        output.getOrCreateTag().putString("Primary", basePrimary);
        output.getOrCreateTag().putString("Secondary", baseSecondary);
        return itemstack;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        SimpleContainer pContainer = new SimpleContainer(ItemStack.EMPTY, ItemStack.EMPTY);
        ItemStack itemstack = this.output.copy();
        CompoundTag compoundtag = pContainer.getItem(0).getOrCreateTag();
        int baseFlux = compoundtag.getInt("Flux");
        output.getOrCreateTag().putInt("Flux", baseFlux + flux);
        int baseWarp = compoundtag.getInt("Warp");
        output.getOrCreateTag().putInt("Warp", baseWarp + warp);
        int baseQuanta = compoundtag.getInt("Quanta");
        output.getOrCreateTag().putInt("Quanta", baseQuanta + warp);
        itemstack.setTag(compoundtag.copy());
        String basePrimary = compoundtag.getString("Primary");
        String baseSecondary = compoundtag.getString("Secondary");
        output.getOrCreateTag().putString("Primary", basePrimary);
        output.getOrCreateTag().putString("Secondary", baseSecondary);
        return itemstack;
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
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            Ingredient base = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "base"));
            Ingredient addition = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "addition"));
            Integer flux = GsonHelper.getAsInt(json, "flux");
            Integer warp = GsonHelper.getAsInt(json, "warp");
            Integer quanta = GsonHelper.getAsInt(json, "quanta");
            return new Modification(id, output, base, addition, flux, warp, quanta);
        }

        @Override
        public Modification fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            Ingredient base = Ingredient.fromNetwork(buf);
            Ingredient addition = Ingredient.fromNetwork(buf);
            Integer flux = buf.readInt();
            Integer warp = buf.readInt();
            Integer quanta = buf.readInt();
            ItemStack output = buf.readItem();
            return new Modification(id, output, base, addition, flux, warp, quanta);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, Modification recipe) {
            recipe.base.toNetwork(buf);
            recipe.addition.toNetwork(buf);
            buf.writeInt(recipe.flux);
            buf.writeInt(recipe.warp);
            buf.writeInt(recipe.quanta);
            buf.writeItem(recipe.output);
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
