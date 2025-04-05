package me.foxyg3n.levelsystem.events;

import me.foxyg3n.levelsystem.level.PlayerLevelInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PreEXPGainEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private boolean isCancelled;

    private final PlayerLevelInfo playerLevelInfo;
    private double gainedExp;

    public PreEXPGainEvent(Player player, PlayerLevelInfo playerLevelInfo, double gainedExp) {
        super(player);
        this.playerLevelInfo = playerLevelInfo;
        this.gainedExp = gainedExp;
    }

    public PlayerLevelInfo getPlayerLevelInfo() {
        return playerLevelInfo;
    }

    public double getGainedExp() {
        return gainedExp;
    }

    public void setGainedEXP(double gainedExp) {
        this.gainedExp = gainedExp;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
