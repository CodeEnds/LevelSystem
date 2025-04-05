package me.foxyg3n.levelsystem.commands;

import dev.jorel.commandapi.CommandAPICommand;

public abstract class Command {

    protected CommandAPICommand command;

    public CommandAPICommand getCommand() {
        return command;
    };

}
