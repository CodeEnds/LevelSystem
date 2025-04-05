package me.foxyg3n.levelsystem.utils;

import me.foxyg3n.levelsystem.LevelSystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitTask;

import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicReference;

public class ArmorStandDisplay {

    public static void displayExpGain(Location location, double expToDisplay) {
        ArmorStand armorStand = createArmorStand(location, ChatColor.YELLOW + "" + expToDisplay + " EXP");

        AtomicReference<Double> heightDifference = new AtomicReference<>((double) 0);
        BukkitTask moveTask = Bukkit.getScheduler().runTaskTimer(LevelSystem.getInstance(), () -> {
            double heightDifferenceValue = heightDifference.updateAndGet(value -> value + 0.2);
            armorStand.teleport(location.clone().add(0, heightDifferenceValue, 0));
        }, 1, 2);

        Bukkit.getScheduler().runTaskLater(LevelSystem.getInstance(), () -> {
            moveTask.cancel();
            armorStand.remove();
        }, 20);
    }

    private static ArmorStand createArmorStand(Location location, @Nullable String customName) {
        if(location == null || location.getWorld() == null) return null;

        return location.getWorld().spawn(location, ArmorStand.class, armorStand -> {
            armorStand.setVisible(false);
            armorStand.setMarker(true);
            armorStand.setSmall(true);
            armorStand.setGravity(false);
            armorStand.setInvulnerable(false);
            armorStand.setRemoveWhenFarAway(true);
            armorStand.setCustomNameVisible(true);
            if(customName != null) armorStand.setCustomName(customName);
        });
    }

}
