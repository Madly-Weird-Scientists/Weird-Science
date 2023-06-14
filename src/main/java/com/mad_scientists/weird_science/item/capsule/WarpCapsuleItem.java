package com.mad_scientists.weird_science.item.capsule;

import com.mad_scientists.weird_science.WeirdScience;
import com.mad_scientists.weird_science.util.Lang;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class WarpCapsuleItem extends Item {
    public WarpCapsuleItem(Properties properties) {
        super(properties);
    }

    static int max = 8;

    @Override
    public void fillItemCategory(@NotNull CreativeModeTab group, @NotNull NonNullList<ItemStack> items) {
        if (group == WeirdScience.TAB) {
            ItemStack stack = new ItemStack(this);
            CompoundTag nbt = stack.getOrCreateTag();
            nbt.putInt("Warp", 0);
            items.add(stack);
            ItemStack stackFull = new ItemStack(this);
            CompoundTag nbt1 = stackFull.getOrCreateTag();
            nbt1.putInt("Warp", 8);
            items.add(stackFull);
        }
    }
    @Override
    public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(itemstack, world, entity, slot, selected);
        CompoundTag tag = itemstack.getTag();
        if (tag != null) {
            if (tag.getInt("Warp") > max) {
                tag.putInt("Warp", max);
            }
        }
        if (tag == null) {
            tag = new CompoundTag();
            tag.putInt("Warp", 0);
            itemstack.setTag(tag);
        }
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        CompoundTag tag = stack.getTag();
        if (tag != null) {
            String percentage = Lang.translateDirect("capsule.value.percentage." + stack.getTag().getInt("Warp")).withStyle(ChatFormatting.LIGHT_PURPLE).getString();

            tooltip.add(Component.nullToEmpty(Lang.translateDirect("capsule.value.capacity", percentage).withStyle(ChatFormatting.GRAY).getString()));
            tooltip.add(Component.nullToEmpty(Lang.translateDirect("capsule.value.warp." + stack.getTag().getInt("Warp")).withStyle(ChatFormatting.LIGHT_PURPLE).getString()));
        }
        if (tag == null) {
            tooltip.add(Component.nullToEmpty(Lang.translateDirect("capsule.value.capacity", "capsule.value.percentage.0").withStyle(ChatFormatting.GRAY).getString()));
            tooltip.add(Component.nullToEmpty(Lang.translateDirect("capsule.value.warp.0").withStyle(ChatFormatting.GREEN).getString()));
        }

    }
}
