package me.foxyg3n.levelsystem.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.*;
import me.foxyg3n.levelsystem.LevelSystem;
import me.foxyg3n.levelsystem.level.PlayerLevelInfo;
import me.foxyg3n.levelsystem.level.PlayerLevelManager;
import me.foxyg3n.levelsystem.utils.Messenger;
import org.bukkit.OfflinePlayer;

public class LevelCommand extends Command {

    private final PlayerLevelManager playerLevelManager = LevelSystem.getInstance().getPlayerLevelManager();

    public LevelCommand() {
        command = new CommandAPICommand("level")
                .withAliases("poziom")
                .withPermission("levelsystem.command.level")
                .withSubcommands(
                        set(),
                        add(),
                        remove(),
                        info()
                );
    }

    public CommandAPICommand set() {
        return new CommandAPICommand("set")
                .withArguments(
                        new StringArgument("type").replaceSuggestions(ArgumentSuggestions.strings("level", "exp")),
                        new DoubleArgument("number").replaceSafeSuggestions(SafeSuggestions.suggest(info -> new Double[] { 1D }))
                )
                .withOptionalArguments(
                        new OfflinePlayerArgument("player")
                )
                .executesPlayer((player, args) -> {
                    OfflinePlayer offlinePlayer = (OfflinePlayer) args.getOrDefault("player", player);
                    PlayerLevelInfo playerLevelInfo = playerLevelManager.getPlayerLevelInfo(offlinePlayer);

                    String type = (String) args.get("type");
                    Number number = (Number) args.get("number");

                    if(type.equals("level")) playerLevelInfo.setLevel(number.intValue());
                    else if(type.equals("exp")) playerLevelInfo.setExperience(number.doubleValue());

                    playerLevelManager.save();
                });
    }

    public CommandAPICommand add() {
        return new CommandAPICommand("add")
                .withArguments(
                        new StringArgument("type").replaceSuggestions(ArgumentSuggestions.strings("level", "exp")),
                        new DoubleArgument("number").replaceSafeSuggestions(SafeSuggestions.suggest(info -> new Double[] { 1D }))
                )
                .withOptionalArguments(
                        new OfflinePlayerArgument("player")
                )
                .executesPlayer((player, args) -> {
                    OfflinePlayer offlinePlayer = (OfflinePlayer) args.getOrDefault("player", player);
                    PlayerLevelInfo playerLevelInfo = playerLevelManager.getPlayerLevelInfo(offlinePlayer);

                    String type = (String) args.get("type");
                    Number number = (Number) args.get("number");

                    if(type.equals("level")) playerLevelInfo.addLevel(number.intValue());
                    else if(type.equals("exp")) playerLevelInfo.addExperience(number.doubleValue());

                    playerLevelManager.save();
                });
    }

    public CommandAPICommand remove() {
        return new CommandAPICommand("remove")
                .withArguments(
                        new StringArgument("type").replaceSuggestions(ArgumentSuggestions.strings("level", "exp")),
                        new DoubleArgument("number").replaceSafeSuggestions(SafeSuggestions.suggest(info -> new Double[] { 1D }))
                )
                .withOptionalArguments(
                        new OfflinePlayerArgument("player")
                )
                .executesPlayer((player, args) -> {
                    OfflinePlayer offlinePlayer = (OfflinePlayer) args.getOrDefault("player", player);
                    PlayerLevelInfo playerLevelInfo = playerLevelManager.getPlayerLevelInfo(offlinePlayer);

                    String type = (String) args.get("type");
                    Number number = (Number) args.get("number");

                    if(type.equals("level")) playerLevelInfo.removeLevel(number.intValue());
                    else if(type.equals("exp")) playerLevelInfo.removeExperience(number.doubleValue());

                    playerLevelManager.save();
                });
    }

    public CommandAPICommand info() {
        return new CommandAPICommand("info")
                .withArguments(
                        new StringArgument("type").replaceSuggestions(ArgumentSuggestions.strings("level", "exp"))
                )
                .withOptionalArguments(
                        new OfflinePlayerArgument("player")
                )
                .executesPlayer((player, args) -> {
                    OfflinePlayer offlinePlayer = (OfflinePlayer) args.getOrDefault("player", player);
                    PlayerLevelInfo playerLevelInfo = playerLevelManager.getPlayerLevelInfo(offlinePlayer);

                    String type = (String) args.get("type");

                    if(type.equals("level")) Messenger.sendMessage(player, "Poziom gracza " + offlinePlayer.getName() + ": " + playerLevelInfo.getLevel());
                    else if(type.equals("exp")) Messenger.sendMessage(player, "Doświadczenie gracza " + offlinePlayer.getName() + ": " + playerLevelInfo.getExperience());
                });
    }

}
