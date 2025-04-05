package me.foxyg3n.levelsystem.listeners;

import me.foxyg3n.levelsystem.LevelSystem;
import me.foxyg3n.levelsystem.utils.EventHelper;
import me.foxyg3n.levelsystem.utils.MythicUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

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

        EventHelper.handleExpGain(killer, victim.getLocation(), expFromMob);
    }
}
