package com.mad_scientists.weird_science.command.componentmodify;

import com.mad_scientists.weird_science.init.AllItems;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class Primary {
    public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
        if (entity == null)
            return;
        if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == AllItems.COMPONENT.get()) {
            (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag().putString("Primary", (new Object() {
                public String getMessage() {
                    try {
                        return MessageArgument.getMessage(arguments, "primary").getString();
                    } catch (CommandSyntaxException ignored) {
                        return "";
                    }
                }
            }).getMessage());
        }
    }

}
