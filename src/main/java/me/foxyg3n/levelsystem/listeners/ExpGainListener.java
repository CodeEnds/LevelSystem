package me.foxyg3n.levelsystem.listeners;

import me.foxyg3n.levelsystem.events.PreEXPGainEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ExpGainListener implements Listener {

    @EventHandler
    public void onExpGain(PreEXPGainEvent event) {
        if(event.getPlayer().hasPermission("levelsystem.boostexp.legendary")) {
            event.setGainedEXP(event.getGainedExp() * 1.3);
        } else if(event.getPlayer().hasPermission("levelsystem.boostexp.mythical")) {
            event.setGainedEXP(event.getGainedExp() * 1.2);
        }
    }

}
