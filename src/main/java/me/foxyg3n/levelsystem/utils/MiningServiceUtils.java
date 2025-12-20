package me.foxyg3n.levelsystem.utils;

import me.foxyg3n.foxlib.bukkit.services.mining.MiningContext;
import me.foxyg3n.foxlib.bukkit.utils.LocationUtils;
import me.foxyg3n.foxlib.bukkit.utils.PlayerUtils;
import me.foxyg3n.levelsystem.LevelSystem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Map;

public class MiningServiceUtils {

    private static final Map<Material, Double> blocks = LevelSystem.getInstance().getPluginConfig().blocks;

    public static void handleBlockBreakExpGain(MiningContext miningContext) {
        Player player = miningContext.getPlayer();
        Collection<Block> affectedBlocks = miningContext.getAffectedBlocks();

        ItemStack tool = PlayerUtils.getPlayerTool(player);
        if(tool == null || tool.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) return;

        for (Block block : affectedBlocks) {
            Location blockLocation = LocationUtils.fixToCenter(block.getLocation());

            if(!blocks.containsKey(block.getType())) return;

            Double expFromBlock = blocks.get(block.getType());
            EventHelper.handleExpGain(player, blockLocation, expFromBlock);
        }
    }
}
