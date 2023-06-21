package com.mad_scientists.weird_science.content.item.gel.capsule;

import net.minecraft.world.item.Item;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class CapsuleItem extends Item {
    public CapsuleItem(Properties properties) {
        super(properties);
    }

    //@Override
    //public void appendHoverText(ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
    //    tooltip.add(Component.nullToEmpty(Lang.translateDirect("capsule.value.capacity.empty").withStyle(ChatFormatting.GRAY).getString()));
    //        tooltip.add(Component.nullToEmpty(Lang.translateDirect("capsule.value.empty").withStyle(ChatFormatting.GREEN).getString()));
    //}
}
