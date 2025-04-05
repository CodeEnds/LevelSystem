package me.foxyg3n.levelsystem.listeners;

import me.foxyg3n.foxlib.libs.panda.std.Option;
import me.foxyg3n.foxlib.libs.panda.std.stream.PandaStream;
import me.foxyg3n.levelsystem.LevelSystem;
import me.foxyg3n.levelsystem.data.entities.MaterialOrEntity;
import me.foxyg3n.levelsystem.utils.EventHelper;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.Map;

public class PlayerFishListener implements Listener {

    private static final Map<MaterialOrEntity, Double> fish = LevelSystem.getInstance().getPluginConfig().fishing;

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        if(event.isCancelled()) return;

        Player player = event.getPlayer();
        Entity entity = event.getCaught();

        if (entity == null) return;
        if (event.getHook().getState() != FishHook.HookState.BOBBING) return;

        if(event.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY) || event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
            Material itemType = ((Item) event.getCaught()).getItemStack().getType();
            Option<Double> itemOption = PandaStream.of(fish.entrySet())
                    .find(entry -> entry.getKey().isMaterial() && entry.getKey().getAsMaterial().equals(itemType))
                    .map(Map.Entry::getValue);

            itemOption.peek(expFromItem -> {
                EventHelper.handleExpGain(player, entity.getLocation(), expFromItem);
            });
        }
    }
}