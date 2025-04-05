package me.foxyg3n.levelsystem.commands;

import dev.jorel.commandapi.CommandAPICommand;
import me.foxyg3n.levelsystem.utils.Messenger;

public class TestCommand extends Command {

    public TestCommand() {
        command = new CommandAPICommand("test")
                .withAliases("test")
                .withPermission("levelsystem.command.test")
                .executesPlayer((player, args) -> {
                    Messenger.sendMessage(player, "&aTest command executed!");
                });
    }

}
