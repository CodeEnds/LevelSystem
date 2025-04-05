package me.foxyg3n.levelsystem.utils;

import me.foxyg3n.levelsystem.LevelSystem;
import me.foxyg3n.levelsystem.config.Config;
import me.foxyg3n.levelsystem.data.entities.LevelInfo;
import me.foxyg3n.levelsystem.level.PlayerLevelInfo;

public class PlayerLevelUtils {

    private static final Config config = LevelSystem.getInstance().getPluginConfig();

    public static double getExpToNextLevel(PlayerLevelInfo playerLevelInfo) {
        if(config.levels.size() <= playerLevelInfo.getLevel()) return -1;

        LevelInfo levelInfo = config.levels.get(playerLevelInfo.getLevel());

        return levelInfo.getRequiredExperience() - playerLevelInfo.getExperience();
    }

}
