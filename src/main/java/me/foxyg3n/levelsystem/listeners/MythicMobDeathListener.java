package me.foxyg3n.levelsystem.listeners;

import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import io.lumine.mythic.core.mobs.ActiveMob;
import me.foxyg3n.levelsystem.LevelSystem;
import me.foxyg3n.levelsystem.utils.EventHelper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Map;

public class MythicMobDeathListener implements Listener {

    private static Map<String, Double> mythicMobs;

    public static void loadMythicMobs() {
        mythicMobs = LevelSystem.getInstance()
                .getPluginConfig()
                .mythicMobs;
    }

    @EventHandler
    public void onMythicMobDeath(MythicMobDeathEvent event) {

        if (!Bukkit.getPluginManager().isPluginEnabled("MythicMobs")) return;
        if (!(event.getKiller() instanceof Player killer)) return;

        String mobName = event.getMobType().getInternalName();

        if (!mythicMobs.containsKey(mobName)) return;

        ActiveMob victim = event.getMob();
        double expFromMob = mythicMobs.get(mobName);

        EventHelper.handleExpGain(
                killer,
                victim.getEntity().getBukkitEntity().getLocation(),
                expFromMob
        );
    }
}
