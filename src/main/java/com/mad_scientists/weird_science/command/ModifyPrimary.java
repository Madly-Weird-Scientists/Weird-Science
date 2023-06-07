package com.mad_scientists.weird_science.command;

import com.mad_scientists.weird_science.command.componentmodify.Primary;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.MessageArgument;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ModifyPrimary {
    @SubscribeEvent
    public static void registerCommand(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("modifyComponentPrimary").requires(s -> s.hasPermission(2))
                .then(Commands.argument("primary", MessageArgument.message()).executes(arguments -> {
                    ServerLevel world = arguments.getSource().getLevel();
                    Entity entity = arguments.getSource().getEntity();
                    if (entity == null)
                        entity = FakePlayerFactory.getMinecraft(world);
                    Primary.execute(arguments, entity);
                    return 0;
                }))
        );
    }
}
