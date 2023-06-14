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
import net.minecraft.world.level.Level;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class FluxCapsuleRecipe extends CustomRecipe {
    public FluxCapsuleRecipe(ResourceLocation id) {
        super(id);
    }
    private static final int maxFlux = FluxCapsuleItem.max;
    private static final Ingredient FLUX_INGREDIENT = Ingredient.of(AllTags.Items.FLUX_RICH);
    private static final Ingredient CAPSULE_INGREDIENT = Ingredient.of(AllItems.CAPSULE.get());
    @Override
    public boolean matches(CraftingContainer inventory, Level world) {
        int capsules = 0;
        int additions = 0;
        boolean flag = false;

        for (int i = 0; i < inventory.getContainerSize(); ++i) {
            ItemStack stack = inventory.getItem(i);
            if (!stack.isEmpty()) {
                if (CAPSULE_INGREDIENT.test(stack)) {
                    if (flag) {
                        return false;
                    }

                    flag = true;
                } else {
                    if (FLUX_INGREDIENT.test(stack)) {
                        ++additions;
                        if (i > maxFlux) {
                            return false;
                        }
                    }
                }

                if (additions > maxFlux || capsules > 1) {
                    return false;
                }
            }
        }

        return flag && additions >= 1;
    }

    @Override
    public ItemStack assemble(CraftingContainer inventory) {
        int count = 0;

        for(int j = 0; j < inventory.getContainerSize(); ++j) {
            ItemStack itemstack1 = inventory.getItem(j);
            if (!itemstack1.isEmpty()) {
                if (FLUX_INGREDIENT.test(itemstack1)) {
                    ++count;
                }
            }
        }

        ItemStack modifiedCapsule = new ItemStack(AllItems.FLUX_CAPSULE.get());
        CompoundTag compoundtag = modifiedCapsule.getOrCreateTag();
        compoundtag.putInt("Flux", count);

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
