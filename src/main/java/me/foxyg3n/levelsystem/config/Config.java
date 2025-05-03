package me.foxyg3n.levelsystem.config;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.Exclude;
import me.foxyg3n.levelsystem.LevelSystem;
import me.foxyg3n.levelsystem.data.entities.MaterialOrEntity;
import me.foxyg3n.levelsystem.data.entities.LevelInfo;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config extends OkaeriConfig {

    @Comment("")
    @Comment("Prefix do wysyłania wiadomości")
    public String prefix = "&8[&6LevelSystem&8] &7";

    @Comment("")
    @Comment("Co jaki czas mają być zapisywane poziomy graczy do pliku")
    public Duration saveDataInvterval = Duration.ofMinutes(5);

    public static class ExpLimitConfig extends OkaeriConfig {
        @Exclude
        public static final NamespacedKey EXP_LIMIT_KEY = new NamespacedKey(LevelSystem.getInstance(), "exp_limit");
        public boolean enabled = true;
        public double expMultiplier = 0.5;
    }

    @Comment("")
    @Comment("Obniża otrzymywane doświadczenie z mobów ze spawnerów")
    public ExpLimitConfig expLimit = new ExpLimitConfig();

    @Comment("")
    public List<LevelInfo> levels = new ArrayList<>();
    @Comment("")
    public Map<EntityType, Double> mobs = new HashMap<>();
    @Comment("")
    public Map<String, Double> mythicMobs = new HashMap<>();
    @Comment("")
    public Map<Material, Double> blocks = new HashMap<>();
    @Comment("")
    public Map<MaterialOrEntity, Double> fishing = new HashMap<>();
    @Comment("")
    public Map<Material, Double> plants = new HashMap<>();

    {
        levels.add(new LevelInfo(1,  0));
        levels.add(new LevelInfo(2, 1000));
        levels.add(new LevelInfo(3, 2500));
        levels.add(new LevelInfo(4, 5000));
        levels.add(new LevelInfo(5, 7500));
        levels.add(new LevelInfo(6, 10000));
        levels.add(new LevelInfo(7, 15000));
        levels.add(new LevelInfo(8, 20000));
        levels.add(new LevelInfo(9, 25000));
        levels.add(new LevelInfo(10, 30000));
        levels.add(new LevelInfo(11, 40000));

        mobs.put(EntityType.ZOMBIFIED_PIGLIN, 25.0);
        mobs.put(EntityType.ZOMBIE, 15.0);
        mobs.put(EntityType.CREEPER, 15.0);
        mobs.put(EntityType.SKELETON, 15.0);
        mobs.put(EntityType.ENDERMAN, 30.0);
        mobs.put(EntityType.BLAZE, 25.0);
        mobs.put(EntityType.CAVE_SPIDER, 20.0);
        mobs.put(EntityType.DROWNED, 25.0);
        mobs.put(EntityType.ELDER_GUARDIAN, 100.0);
        mobs.put(EntityType.ENDERMITE, 5.0);
        mobs.put(EntityType.EVOKER, 100.0);
        mobs.put(EntityType.GHAST, 80.0);
        mobs.put(EntityType.GUARDIAN, 30.0);
        mobs.put(EntityType.HOGLIN, 30.0);
        mobs.put(EntityType.HUSK, 15.0);
        mobs.put(EntityType.MAGMA_CUBE, 20.0);
        mobs.put(EntityType.SLIME, 15.0);
        mobs.put(EntityType.PHANTOM, 20.0);
        mobs.put(EntityType.PIGLIN, 25.0);
        mobs.put(EntityType.PIGLIN_BRUTE, 25.0);
        mobs.put(EntityType.PILLAGER, 30.0);
        mobs.put(EntityType.RAVAGER, 60.0);
        mobs.put(EntityType.SHULKER, 40.0);
        mobs.put(EntityType.SILVERFISH, 10.0);
        mobs.put(EntityType.SKELETON_HORSE, 30.0);
        mobs.put(EntityType.SPIDER, 15.0);
        mobs.put(EntityType.STRAY, 25.0);
        mobs.put(EntityType.VEX, 10.0);
        mobs.put(EntityType.VINDICATOR, 30.0);
        mobs.put(EntityType.WARDEN, 300.0);
        mobs.put(EntityType.WITHER, 400.0);
        mobs.put(EntityType.WITCH, 40.0);
        mobs.put(EntityType.WITHER_SKELETON, 40.0);
        mobs.put(EntityType.ZOGLIN, 40.0);
        mobs.put(EntityType.ZOMBIE_HORSE, 30.0);
        mobs.put(EntityType.ZOMBIE_VILLAGER, 60.0);
        mobs.put(EntityType.ENDER_DRAGON, 400.0);

        mythicMobs.put("Bagniak", 50.0);
        mythicMobs.put("Odchłaniak", 50.0);
        mythicMobs.put("Dagon", 80.0);
        mythicMobs.put("Ognik", 80.0);
        mythicMobs.put("Skelder", 100.0);

        blocks.put(Material.COPPER_ORE, 5.0);
        blocks.put(Material.DEEPSLATE_COPPER_ORE, 5.0);
        blocks.put(Material.COAL_ORE, 5.0);
        blocks.put(Material.DEEPSLATE_COAL_ORE, 5.0);
        blocks.put(Material.IRON_ORE, 10.0);
        blocks.put(Material.DEEPSLATE_IRON_ORE, 10.0);
        blocks.put(Material.GOLD_ORE, 25.0);
        blocks.put(Material.DEEPSLATE_GOLD_ORE, 25.0);
        blocks.put(Material.REDSTONE_ORE, 15.0);
        blocks.put(Material.DEEPSLATE_REDSTONE_ORE, 15.0);
        blocks.put(Material.LAPIS_ORE, 40.0);
        blocks.put(Material.DEEPSLATE_LAPIS_ORE, 40.0);
        blocks.put(Material.DIAMOND_ORE, 50.0);
        blocks.put(Material.DEEPSLATE_DIAMOND_ORE, 50.0);
        blocks.put(Material.EMERALD_ORE, 50.0);
        blocks.put(Material.DEEPSLATE_EMERALD_ORE, 50.0);
        blocks.put(Material.NETHER_GOLD_ORE, 15.0);
        blocks.put(Material.NETHER_QUARTZ_ORE, 10.0);

        fishing.put(MaterialOrEntity.of(EntityType.COD), 15.0);
        fishing.put(MaterialOrEntity.of(EntityType.SALMON), 20.0);
        fishing.put(MaterialOrEntity.of(EntityType.TROPICAL_FISH), 40.0);
        fishing.put(MaterialOrEntity.of(EntityType.PUFFERFISH), 25.0);
        fishing.put(MaterialOrEntity.of(Material.BOW), 25.0);
        fishing.put(MaterialOrEntity.of(Material.ENCHANTED_BOOK), 25.0);
        fishing.put(MaterialOrEntity.of(Material.FISHING_ROD), 25.0);
        fishing.put(MaterialOrEntity.of(Material.NAME_TAG), 25.0);
        fishing.put(MaterialOrEntity.of(Material.NAUTILUS_SHELL), 25.0);
        fishing.put(MaterialOrEntity.of(Material.SADDLE), 25.0);
        fishing.put(MaterialOrEntity.of(Material.LILY_PAD), 10.0);
        fishing.put(MaterialOrEntity.of(Material.BOWL), 10.0);
        fishing.put(MaterialOrEntity.of(Material.LEATHER), 10.0);
        fishing.put(MaterialOrEntity.of(Material.LEATHER_BOOTS), 10.0);
        fishing.put(MaterialOrEntity.of(Material.ROTTEN_FLESH), 10.0);
        fishing.put(MaterialOrEntity.of(Material.STICK), 10.0);
        fishing.put(MaterialOrEntity.of(Material.STRING), 10.0);
        fishing.put(MaterialOrEntity.of(Material.POTION), 10.0);
        fishing.put(MaterialOrEntity.of(Material.BONE), 10.0);
        fishing.put(MaterialOrEntity.of(Material.INK_SAC), 20.0);
        fishing.put(MaterialOrEntity.of(Material.TRIPWIRE_HOOK), 10.0);

        plants.put(Material.CARROT, 5.0);
        plants.put(Material.CARROTS, 5.0);
        plants.put(Material.POTATOES, 5.0);
        plants.put(Material.WHEAT, 3.0);
        plants.put(Material.BEETROOTS, 5.0);
        plants.put(Material.MELON, 8.0);
        plants.put(Material.COCOA, 5.0);
        plants.put(Material.NETHER_WART, 10.0);
    }

}
