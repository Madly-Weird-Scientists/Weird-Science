package com.mad_scientists.weird_science.content.item;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.content.item.gadget.LensRequiringItem;
import com.mad_scientists.weird_science.foundation.util.Lang;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class ValueHoldingItem extends Item {
    public ValueHoldingItem(Properties properties) {
        super(properties);
    }
    @Override
    public void fillItemCategory(@NotNull CreativeModeTab group, @NotNull NonNullList<ItemStack> items) {
        if (group == WeirdScience.TAB) {
            ItemStack stack = new ItemStack(this);
            CompoundTag nbt = stack.getOrCreateTag();
            nbt.putBoolean("isLensPresent", true);
            nbt.putInt("Flux", 0);
            nbt.putInt("Warp", 0);
            nbt.putInt("Quanta", 0);
            items.add(stack);
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        CompoundTag tag = stack.getTag();
        if (tag != null) {
            if (tag.getBoolean("isLensPresent")) {
                tooltip.add(Component.nullToEmpty(Lang.translateDirect("component.flux", stack.getTag().getInt("Flux")).withStyle(ChatFormatting.GREEN).getString()));
                tooltip.add(Component.nullToEmpty(Lang.translateDirect("component.warp", stack.getTag().getInt("Warp")).withStyle(ChatFormatting.LIGHT_PURPLE).getString()));
                tooltip.add(Component.nullToEmpty(Lang.translateDirect("component.quanta", stack.getTag().getInt("Quanta")).withStyle(ChatFormatting.AQUA).getString()));
            }
        }
    }
}
