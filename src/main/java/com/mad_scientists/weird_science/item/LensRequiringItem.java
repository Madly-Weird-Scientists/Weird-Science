package com.mad_scientists.weird_science.item;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.item.component.DynamicModelItem;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class LensRequiringItem extends Item implements DynamicModelItem {
    public LensRequiringItem(Properties properties) {
        super(properties);
    }
    @Override
    public void fillItemCategory(@NotNull CreativeModeTab group, @NotNull NonNullList<ItemStack> items) {
        if (group == WeirdScience.TAB) {
            ItemStack stack = new ItemStack(this);
            CompoundTag nbt = stack.getOrCreateTag();
            nbt.putBoolean("isLensPresent", false);
            items.add(stack);
        }
    }

    @Override
    public Optional<ResourceLocation> getDynamicModelId(ItemStack var1) {
        CompoundTag nbt = var1.getTag();
        if (nbt == null) {
            return Optional.empty();
        } else {
            return nbt.isEmpty() ? Optional.empty() : Optional.ofNullable(ResourceLocation.tryParse(nbt.getString("Secondary")));
        }
    }
}
