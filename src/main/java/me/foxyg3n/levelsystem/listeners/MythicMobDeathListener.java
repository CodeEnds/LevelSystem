package me.foxyg3n.levelsystem.listeners;

import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import io.lumine.mythic.core.mobs.ActiveMob;
import me.foxyg3n.levelsystem.LevelSystem;
import me.foxyg3n.levelsystem.utils.EventHelper;
import me.foxyg3n.levelsystem.utils.MythicUtils;
import me.foxyg3n.foxlib.libs.panda.std.stream.PandaStream;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Map;
import java.util.stream.Collectors;

public class MythicMobDeathListener implements Listener {

    private static final Map<MythicMob, Double> mythicMobs = PandaStream.of(LevelSystem.getInstance().getPluginConfig().mythicMobs.entrySet())
            .map(entry -> Map.entry(MythicUtils.getMythicMob(entry.getKey()), entry.getValue()))
            .filter(entry -> entry.getKey().isPresent())
            .map(entry -> Map.entry(entry.getKey().get(), entry.getValue()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    @EventHandler
    public void onMythicMobDeath(MythicMobDeathEvent event) {
        if(!Bukkit.getPluginManager().isPluginEnabled("MythicMobs")) return;
        if(!(event.getKiller() instanceof Player)) return;

        MythicMob mobType = event.getMobType();
        if(!mythicMobs.containsKey(mobType)) return;

        Player killer = (Player) event.getKiller();
        ActiveMob victim = event.getMob();

        double expFromMob = mythicMobs.get(mobType);

        EventHelper.handleExpGain(killer, victim.getEntity().getBukkitEntity().getLocation(), expFromMob);
    }
}
