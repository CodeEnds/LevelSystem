package me.foxyg3n.levelsystem.utils;

import me.foxyg3n.levelsystem.data.entities.LevelInfo;
import me.foxyg3n.levelsystem.level.PlayerLevelInfo;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;

import java.text.DecimalFormat;

public class ActionBarHelper {

    private static final ComponentBuilder actionBarMessage = new ComponentBuilder();
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.##");

    static {
        actionBarMessage.append(new ComponentBuilder("⚔").color(ChatColor.GOLD).create());
        actionBarMessage.append(new ComponentBuilder(" Poziom: ").color(ChatColor.YELLOW).create());
        actionBarMessage.append(new ComponentBuilder("").color(ChatColor.AQUA).create()); // level
        actionBarMessage.append(new ComponentBuilder(" | ").color(ChatColor.YELLOW).create());
        actionBarMessage.append(new ComponentBuilder("⛏").color(ChatColor.GOLD).create());
        actionBarMessage.append(new ComponentBuilder(" Doświadczenie: ").color(ChatColor.YELLOW).create());
        actionBarMessage.append(new ComponentBuilder("").color(ChatColor.AQUA).create()); // experience
    }

    public static BaseComponent[] getActionBarMessage(PlayerLevelInfo playerLevelInfo) {
        int level = playerLevelInfo.getLevel();
        double exp = playerLevelInfo.getExperience();
        LevelInfo nextLevelInfo = playerLevelInfo.getNextLevelInfo();

        ComponentBuilder message = new ComponentBuilder(actionBarMessage);

        ((TextComponent) message.getComponent(2)).setText(String.valueOf(level));
        ((TextComponent) message.getComponent(6)).setText(decimalFormat.format(exp));

        if(nextLevelInfo != null) {
            double requiredExp = nextLevelInfo.getRequiredExperience();
            message.append(new ComponentBuilder("/").color(ChatColor.YELLOW).create());
            message.append(new ComponentBuilder(decimalFormat.format(requiredExp)).color(ChatColor.AQUA).create());
        }

        return message.create();
    }

}
