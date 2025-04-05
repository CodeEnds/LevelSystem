package me.foxyg3n.levelsystem.events;

import me.foxyg3n.levelsystem.level.PlayerLevelInfo;
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
