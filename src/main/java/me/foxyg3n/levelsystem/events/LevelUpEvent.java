package me.foxyg3n.levelsystem.events;

import me.foxyg3n.levelsystem.level.PlayerLevelInfo;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class LevelUpEvent extends PlayerEvent {

    private static final  HandlerList HANDLERS = new HandlerList();

    private final PlayerLevelInfo playerLevelInfo;

    public LevelUpEvent(Player player, PlayerLevelInfo playerLevelInfo) {
        super(player);
        this.playerLevelInfo = playerLevelInfo;
        player.playSound(
                player.getLocation(),
                Sound.ENTITY_PLAYER_LEVELUP,
                1.0f,
                1.0f
        );
        player.getWorld().spawnParticle(
                Particle.TOTEM,
                player.getLocation().add(0, 1, 0),
                30,
                0.5, 1.0, 0.5,
                0.2
        );
        player.sendTitle(ChatColor.YELLOW + "NOWY " + ChatColor.GOLD + "POZIOM!", "", 10, 60, 10);
    }

    public PlayerLevelInfo getPlayerLevelInfo() {
        return playerLevelInfo;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
