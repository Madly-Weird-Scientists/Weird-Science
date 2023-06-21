package com.mad_scientists.weird_science.content.command;

import com.mad_scientists.weird_science.content.command.componentmodify.Quanta;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ModifyQuanta {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("modifyComponentQuanta").requires(s -> s.hasPermission(2)).then(Commands.argument("quanta", DoubleArgumentType.doubleArg(0)).executes(arguments -> {
            ServerLevel world = arguments.getSource().getLevel();
            Entity entity = arguments.getSource().getEntity();
            if (entity == null)
                entity = FakePlayerFactory.getMinecraft(world);
            Quanta.execute(arguments, entity);
            return 0;
        }))
        );
    }
}
