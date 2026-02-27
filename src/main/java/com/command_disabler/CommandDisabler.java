package com.command_disabler;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod("command_disabler")
public class CommandDisabler {
    public CommandDisabler(IEventBus modEventBus) {
        NeoForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        removeCommand(dispatcher, "msg");
        removeCommand(dispatcher, "tell");
        removeCommand(dispatcher, "teammsg");
        removeCommand(dispatcher, "tm");
    }

    private void removeCommand(CommandDispatcher<CommandSourceStack> dispatcher, String name) {
        dispatcher.getRoot().getChildren().removeIf(node -> node.getName().equals(name));
    }
}
