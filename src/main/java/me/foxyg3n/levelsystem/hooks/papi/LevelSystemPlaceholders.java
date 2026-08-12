package me.foxyg3n.levelsystem.hooks.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.foxyg3n.levelsystem.LevelSystem;
import me.foxyg3n.levelsystem.data.entities.LevelInfo;
import me.foxyg3n.levelsystem.level.PlayerLevelInfo;
import me.foxyg3n.levelsystem.level.PlayerLevelManager;
import me.foxyg3n.foxlib.common.utils.MathUtils;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class LevelSystemPlaceholders extends PlaceholderExpansion {

    private static final DecimalFormat decimalFormat = new DecimalFormat("0.##");

    @Override
    public @NotNull String getIdentifier() {
        return "LevelSystem";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Sharyxxx";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        if(player == null) return "";

        PlayerLevelInfo playerLevelInfo = LevelSystem.getInstance().getPlayerLevelManager().getPlayerLevelInfo(player);
        int level = playerLevelInfo.getLevel();
        double exp = playerLevelInfo.getExperience();

        switch (params) {
            case "level":
                return String.valueOf(level);
            case "integer":
                return String.valueOf(level).replace(".0", "");
            case "experience":
                return decimalFormat.format(exp);
            case "experienceprogress":
                LevelInfo nextLevelInfo = playerLevelInfo.getNextLevelInfo();
                if(nextLevelInfo == null) return getProgressBar(1);

                double requiredExperience = nextLevelInfo.getRequiredExperience();
                double progress = exp / requiredExperience;

                return getProgressBar(progress);
            case "nextlevelexp":
                LevelInfo nextLevelInfo1 = playerLevelInfo.getNextLevelInfo();
                if(nextLevelInfo1 == null) {
                    return "";
                }
                double requiredExperience1 = nextLevelInfo1.getRequiredExperience();
                String decimal = decimalFormat.format(requiredExperience1);
                return "/ " + decimal;
        }
        return null;
    }

    private String getProgressBar(double progress) {
        progress = MathUtils.clamp(progress, 0, 1);
        String redSegment = "§c▅";
        String greenSegment = "§a▅";

        StringBuilder progressBar = new StringBuilder();
        progress = Math.floor(progress * 10);
        for(int i = 0; i < 10; i++) {
            if(progress > i) progressBar.append(greenSegment);
            else progressBar.append(redSegment);
        }

        return progressBar.toString();
    }
}
