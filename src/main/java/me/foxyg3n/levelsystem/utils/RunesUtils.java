package me.foxyg3n.levelsystem.utils;

import me.nietwojanazwa.runy.Runy;
import org.bukkit.entity.Player;

public class RunesUtils {

    public static boolean isRunesBarActive(Player player) {
        return Runy.isActionBarActive.containsKey(player);
    }

}
