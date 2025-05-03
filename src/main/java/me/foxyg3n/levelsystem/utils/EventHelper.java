package me.foxyg3n.levelsystem.utils;

import me.foxyg3n.levelsystem.LevelSystem;
import me.foxyg3n.levelsystem.events.PreEXPGainEvent;
import me.foxyg3n.levelsystem.level.PlayerLevelInfo;
import me.foxyg3n.levelsystem.level.PlayerLevelManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class EventHelper {

    private static final PlayerLevelManager playerLevelManager = LevelSystem.getInstance().getPlayerLevelManager();

    public static void handleExpGain(Player player, Location displayLocation, double expGained) {
        if(expGained == 0.0) return;

        PlayerLevelInfo playerLevelInfo = playerLevelManager.getPlayerLevelInfo(player);
        PreEXPGainEvent expGainEvent = new PreEXPGainEvent(player, playerLevelInfo, expGained);
        Bukkit.getPluginManager().callEvent(expGainEvent);

        if(expGainEvent.isCancelled()) return;
        expGained = expGainEvent.getGainedExp();

        playerLevelInfo.addExperience(expGained);

        ArmorStandDisplay.displayExpGain(displayLocation, expGained);
    }

}
