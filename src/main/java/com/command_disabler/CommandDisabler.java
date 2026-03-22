package com.command_disabler;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.CommandEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod("command_disabler")
public class CommandDisabler {
    public CommandDisabler(IEventBus modEventBus) {
        NeoForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        disable(dispatcher, "msg");
        disable(dispatcher, "tell");
        disable(dispatcher, "w");
        disable(dispatcher, "teammsg");
        disable(dispatcher, "tm");
    }

    private void disable(CommandDispatcher<CommandSourceStack> dispatcher, String name) {
        dispatcher.getRoot().getChildren().removeIf(node -> node.getName().equals(name));

        dispatcher.register(
                Commands.literal(name)
                        .requires(source -> false)
        );
    }
}
