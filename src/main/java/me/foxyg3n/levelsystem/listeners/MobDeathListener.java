package me.foxyg3n.levelsystem.listeners;

import me.foxyg3n.levelsystem.LevelSystem;
import me.foxyg3n.levelsystem.config.Config;
import me.foxyg3n.levelsystem.utils.EventHelper;
import me.foxyg3n.levelsystem.utils.MythicUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Map;


public class MobDeathListener implements Listener {

    private static final Map<EntityType, Double> mobs = LevelSystem.getInstance().getPluginConfig().mobs;

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        LivingEntity victim = event.getEntity();
        Player killer = victim.getKiller();
        if(killer == null) return;

        if(!mobs.containsKey(victim.getType())) return;
        if(Bukkit.getPluginManager().isPluginEnabled("MythicMobs") && MythicUtils.getFromEntity(victim).isPresent()) return;

        double expFromMob = mobs.get(victim.getType());
        expFromMob = handleExpThroughMultiplier(event, expFromMob);

        EventHelper.handleExpGain(killer, victim.getLocation(), expFromMob);
    }

    private double handleExpThroughMultiplier(EntityDeathEvent event, double expGained) {
        Config.ExpLimitConfig expLimitConfig = LevelSystem.getInstance().getPluginConfig().expLimit;
        if(!expLimitConfig.enabled) return expGained;

        LivingEntity entity = event.getEntity();
        PersistentDataContainer persistentDataContainer = entity.getPersistentDataContainer();
        if(persistentDataContainer.has(Config.ExpLimitConfig.EXP_LIMIT_KEY, PersistentDataType.STRING)) {
            double multiplier = expLimitConfig.expMultiplier;
            return (double) Math.round(expGained * multiplier);
        }

        return expGained;
    }
}
