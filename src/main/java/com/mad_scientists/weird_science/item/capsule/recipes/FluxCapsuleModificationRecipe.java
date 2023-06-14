package com.mad_scientists.weird_science.item.capsule.recipes;

import com.mad_scientists.weird_science.init.AllItems;
import com.mad_scientists.weird_science.init.AllSpecialRecipes;
import com.mad_scientists.weird_science.init.AllTags;
import com.mad_scientists.weird_science.item.capsule.FluxCapsuleItem;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RepairItemRecipe;
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class FluxCapsuleModificationRecipe extends CustomRecipe {
    public FluxCapsuleModificationRecipe(ResourceLocation id) {
        super(id);
    }
    private static final int maxFlux = FluxCapsuleItem.max;
    private static final Ingredient FLUX_INGREDIENT = Ingredient.of(AllTags.Items.FLUX_RICH);
    private static final Ingredient CAPSULE_INGREDIENT = Ingredient.of(AllItems.FLUX_CAPSULE.get());
    @Override
    public boolean matches(CraftingContainer inventory, Level world) {
        int capsules = 0;
        int additions = 0;
        boolean flag = false;

        for (int i = 0; i < inventory.getContainerSize(); ++i) {
            int subtract = 0;
            ItemStack stack = inventory.getItem(i);
            CompoundTag tag = stack.getTag();
            if (tag != null) {
                subtract = tag.getInt("Flux");
            }
            if (!stack.isEmpty()) {
                if (CAPSULE_INGREDIENT.test(stack)) {
                    if (flag) {
                        return false;
                    }

                    flag = true;
                } else {
                    if (FLUX_INGREDIENT.test(stack)) {
                        ++additions;
                        if (i > maxFlux - subtract) {
                            return false;
                        }
                    }
                }

                if (additions > maxFlux - subtract || capsules > 1) {
                    return false;
                }
            }
        }

        return flag && additions >= 1;
    }

    @Override
    public ItemStack assemble(CraftingContainer inventory) {
        int count = 0;
        ItemStack oldCapsule = ItemStack.EMPTY;
        for(int j = 0; j < inventory.getContainerSize(); ++j) {
            ItemStack stack = inventory.getItem(j);
            if (!stack.isEmpty()) {
                if ((stack.getItem()) instanceof FluxCapsuleItem) {
                    oldCapsule = stack;
                }
            }
            ItemStack itemstack1 = inventory.getItem(j);
            if (!itemstack1.isEmpty()) {
                if (FLUX_INGREDIENT.test(itemstack1)) {
                    ++count;
                }
            }
        }
        CompoundTag oldTag = oldCapsule.getTag();
        int oldFlux = oldTag.getInt("Flux");
        ItemStack modifiedCapsule = new ItemStack(AllItems.FLUX_CAPSULE.get());
        CompoundTag compoundtag = modifiedCapsule.getOrCreateTag();
        compoundtag.putInt("Flux", count + oldFlux);
        if (compoundtag.getInt("Flux") > maxFlux) {
            return ItemStack.EMPTY;
        }
        //if (oldCapsule.hasTag()) {
        //    modifiedCapsule.setTag(oldCapsule.getTag().copy());
        //}
        return modifiedCapsule;
    }


    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return AllSpecialRecipes.FLUX_CAPSULING.getSerializer();
    }
}
