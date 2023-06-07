package com.mad_scientists.weird_science.block.modification_station;

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

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class Modification implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;
    final Integer flux;
    final Integer warp;
    final Integer quanta;
    public Modification(ResourceLocation id, ItemStack output,
                        NonNullList<Ingredient> recipeItems, Integer flux, Integer warp, Integer quanta) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
        this.flux = flux;
        this.warp = warp;
        this.quanta = quanta;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        return recipeItems.get(0).test(pContainer.getItem(1));
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return recipeItems;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        ItemStack itemstack = this.output.copy();
        return getItemStack(itemstack, pContainer.getItem(0), flux, warp, quanta);
    }

    static ItemStack getItemStack(ItemStack itemstack, ItemStack item, Integer flux, Integer warp, Integer quanta) {
        CompoundTag compoundtag = item.getTag();
        if (compoundtag != null) {

            itemstack.setTag(compoundtag.copy());
            if (itemstack.getTag() != null && flux != 0) {
                int baseFlux = itemstack.getTag().getInt("Flux");
                itemstack.getTag().putInt("Flux", baseFlux + flux);
            }
            if (itemstack.getTag() != null && warp != 0) {
                int baseWarp = itemstack.getTag().getInt("Warp");
                itemstack.getTag().putInt("Warp", baseWarp + warp);
            }
            if (itemstack.getTag() != null && quanta != 0) {
                int baseQuanta = itemstack.getTag().getInt("Quanta");
                itemstack.getTag().putInt("Quanta", baseQuanta + warp);
            }
        }

        return itemstack;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
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
            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "addition");
            Integer flux = GsonHelper.getAsInt(json, "flux");
            Integer warp = GsonHelper.getAsInt(json, "warp");
            Integer quanta = GsonHelper.getAsInt(json, "quanta");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);


            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new Modification(id, output, inputs, flux, warp, quanta);
        }

        @Override
        public Modification fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            inputs.replaceAll(ignored -> Ingredient.fromNetwork(buf));
            Integer flux = buf.readInt();
            Integer warp = buf.readInt();
            Integer quanta = buf.readInt();
            ItemStack output = buf.readItem();
            return new Modification(id, output, inputs, flux, warp, quanta);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, Modification recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
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
