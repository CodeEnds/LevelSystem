package me.foxyg3n.levelsystem.listeners;

import me.foxyg3n.levelsystem.LevelSystem;
import me.foxyg3n.levelsystem.config.Config;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class MobSpawnListener implements Listener {

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event) {
        Config.ExpLimitConfig expLimitConfig = LevelSystem.getInstance().getPluginConfig().expLimit;
        if(!expLimitConfig.enabled) return;

        if(event.getSpawnReason() == CreatureSpawnEvent.SpawnReason.SPAWNER) {
            LivingEntity entity = event.getEntity();
            PersistentDataContainer persistentDataContainer = entity.getPersistentDataContainer();
            persistentDataContainer.set(Config.ExpLimitConfig.EXP_LIMIT_KEY, PersistentDataType.STRING, "");
        }
    }

}
