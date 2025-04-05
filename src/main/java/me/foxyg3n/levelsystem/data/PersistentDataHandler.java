package me.foxyg3n.levelsystem.data;

import me.foxyg3n.levelsystem.LevelSystem;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import java.time.Duration;

public class PersistentDataHandler {

    private BukkitTask persistenceTask;

    public void startHandler() {
        if(persistenceTask != null) return;

        Duration interval = LevelSystem.getInstance().getPluginConfig().saveDataInvterval;
        int intervalInTicks = (int) interval.toSeconds() * 20;

        persistenceTask = Bukkit.getScheduler().runTaskTimerAsynchronously(LevelSystem.getInstance(), () -> {
            LevelSystem.getInstance().getPlayerLevelManager().save();
        }, intervalInTicks, intervalInTicks);
    }

    public void stopHandler() {
        if(persistenceTask == null) return;

        persistenceTask.cancel();
        persistenceTask = null;
    }

    public void reloadHandler() {
        stopHandler();
        startHandler();
    }

}
