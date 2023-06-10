package com.mad_scientists.weird_science.item;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.init.AllItems;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.theillusivec4.curios.api.CuriosApi;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings({"deprecation"})
public class LensRequiringItem extends Item {
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

}
