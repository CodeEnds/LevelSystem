package me.foxyg3n.levelsystem.listeners;

import me.foxyg3n.foxlib.bukkit.utils.LocationUtils;
import me.foxyg3n.levelsystem.LevelSystem;
import me.foxyg3n.levelsystem.events.PreEXPGainEvent;
import me.foxyg3n.levelsystem.level.PlayerLevelInfo;
import me.foxyg3n.levelsystem.level.PlayerLevelManager;
import me.foxyg3n.levelsystem.utils.ArmorStandDisplay;
import me.foxyg3n.levelsystem.utils.EventHelper;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class BlockBreakListener implements Listener {

    private static final Map<Material, Double> blocks = LevelSystem.getInstance().getPluginConfig().blocks;

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if(event.isCancelled()) return;

        Player player = event.getPlayer();
        Block block = event.getBlock();
        Location blockLocation = LocationUtils.fixToCenter(event.getBlock().getLocation());

        if(!blocks.containsKey(block.getType())) return;

        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        if(itemInMainHand.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) return;

        if(block.getBlockData().getMaterial().equals(Material.ANCIENT_DEBRIS)) {
            event.setDropItems(false);
            //noinspection DataFlowIssue
            blockLocation.getWorld().dropItem(blockLocation, new ItemStack(Material.NETHERITE_SCRAP));
        }

        Double expFromBlock = blocks.get(block.getType());
        EventHelper.handleExpGain(player, blockLocation, expFromBlock);
    }
}
