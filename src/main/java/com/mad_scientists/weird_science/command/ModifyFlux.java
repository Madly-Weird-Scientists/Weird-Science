package com.mad_scientists.weird_science.command;

import com.mad_scientists.weird_science.command.componentmodify.Flux;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ModifyFlux {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("modifyComponentFlux").requires(s -> s.hasPermission(2)).then(Commands.argument("flux", DoubleArgumentType.doubleArg(0)).executes(arguments -> {
            ServerLevel world = arguments.getSource().getLevel();
            Entity entity = arguments.getSource().getEntity();
            if (entity == null)
                entity = FakePlayerFactory.getMinecraft(world);
            Flux.execute(arguments, entity);
            return 0;
        }))
        );
    }
}
