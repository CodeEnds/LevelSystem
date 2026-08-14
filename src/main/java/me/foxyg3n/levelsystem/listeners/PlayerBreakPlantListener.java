package me.foxyg3n.levelsystem.listeners;

import me.foxyg3n.foxlib.bukkit.utils.LocationUtils;
import me.foxyg3n.foxlib.bukkit.utils.PlayerUtils;
import me.foxyg3n.levelsystem.LevelSystem;
import me.foxyg3n.levelsystem.utils.EventHelper;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class PlayerBreakPlantListener implements Listener {

    private static final Map<Material, Double> plants = LevelSystem.getInstance().getPluginConfig().plants;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(event.isCancelled()) return;

        Player player = event.getPlayer();
        Block block = event.getBlock();

        if(!plants.containsKey(block.getType())) return;

        ItemStack tool = PlayerUtils.getPlayerTool(player);
        if (tool != null && tool.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) return;

        if(block.getBlockData() instanceof Ageable) {
            Ageable ageable = (Ageable) block.getBlockData();
            if(ageable.getAge() != ageable.getMaximumAge()) return;
        }

        double expFromPlant = plants.get(block.getType());
        EventHelper.handleExpGain(player, LocationUtils.fixToCenter(block.getLocation()), expFromPlant);
    }
}
