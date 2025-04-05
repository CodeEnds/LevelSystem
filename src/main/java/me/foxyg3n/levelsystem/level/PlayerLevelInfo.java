package me.foxyg3n.levelsystem.level;

import me.foxyg3n.foxlib.common.utils.MathUtils;
import me.foxyg3n.foxlib.common.utils.RandomUtils;
import me.foxyg3n.foxlib.libs.panda.std.Option;
import me.foxyg3n.levelsystem.LevelSystem;
import me.foxyg3n.levelsystem.data.entities.LevelInfo;
import me.foxyg3n.levelsystem.events.LevelUpEvent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

public class PlayerLevelInfo {

    private static final List<LevelInfo> levels = LevelSystem.getInstance().getPluginConfig().levels;

    private final OfflinePlayer player;
    private int level;
    private double experience;

    public PlayerLevelInfo(OfflinePlayer player) {
        this.player = player;
        this.level = 1;
        this.experience = 0;
    }

    public OfflinePlayer getOfflinePlayer() {
        return player;
    }

    public Option<Player> getPlayer() {
        return Option.of(player.getPlayer());
    }

    public @NotNull LevelInfo getCurrentLevelInfo() {
        return levels.get(this.level - 1);
    }

    public @Nullable LevelInfo getPreviousLevelInfo() {
        if(this.level <= 1) return null;
        return levels.get(this.level - 2);
    }

    public @Nullable LevelInfo getNextLevelInfo() {
        if(this.level >= levels.size()) return null;
        return levels.get(this.level);
    }

    public int getLevel() {
        return this.level;
    }

    public double getExperience() {
        return this.experience;
    }

    public void setLevel(int value) {
        this.level = MathUtils.clamp(value, 1, levels.size());
    }

    public void setExperience(double value) {
        value = MathUtils.round(value, 2);
        LevelInfo nextLevelInfo = getNextLevelInfo();
        this.experience = MathUtils.clamp(value, 0, nextLevelInfo != null ? nextLevelInfo.getRequiredExperience() : Integer.MAX_VALUE);
    }

    public void addExperience(double value) {
        experience += value;

        while(true) {
            LevelInfo nextLevelInfo = getNextLevelInfo();
            if(nextLevelInfo == null) break;

            double requiredExperience = nextLevelInfo.getRequiredExperience();
            if(experience < requiredExperience) break;
            experience -= requiredExperience;
            level++;

            LevelUpEvent levelUpEvent = new LevelUpEvent(player.getPlayer(), this);
            Bukkit.getPluginManager().callEvent(levelUpEvent);
        }
    }

    public void removeExperience(double value) {
        experience -= value;

        while(true) {
            LevelInfo previousLevelInfo = getPreviousLevelInfo();
            if(previousLevelInfo == null) {
                experience = Math.max(0, experience);
                break;
            }

            double requiredExperience = previousLevelInfo.getRequiredExperience();
            if(experience >= 0) break;
            experience += requiredExperience;
            level--;
        }
    }

    public void addLevel(int value) {
        int temp = level;

        level += value;
        level = MathUtils.clamp(level, 1, levels.size());

        if (level != temp) {
            LevelUpEvent levelUpEvent = new LevelUpEvent(player.getPlayer(), this);
            Bukkit.getPluginManager().callEvent(levelUpEvent);
        }
    }

    public void removeLevel(int value) {
        level -= value;
        level = MathUtils.clamp(level, 1, levels.size());
    }
}
