package me.foxyg3n.levelsystem.utils;

import me.foxyg3n.levelsystem.LevelSystem;
import me.foxyg3n.levelsystem.config.Config;
import org.bukkit.entity.Player;

public class Messenger {

    private static final Config config = LevelSystem.getInstance().getPluginConfig();

    public static void sendMessage(Player player, String message) {
        player.sendMessage(config.prefix + message);
    }

}
