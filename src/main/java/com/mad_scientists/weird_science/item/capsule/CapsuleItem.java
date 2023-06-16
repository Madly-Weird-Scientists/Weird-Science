package com.mad_scientists.weird_science.item.capsule;

import com.mad_scientists.weird_science.util.Lang;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
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
