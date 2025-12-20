package me.foxyg3n.levelsystem;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.json.gson.JsonGsonConfigurer;
import eu.okaeri.configs.serdes.commons.SerdesCommons;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import me.foxyg3n.foxlib.bukkit.services.mining.MiningService;
import me.foxyg3n.foxlib.bukkit.services.mining.MiningServiceImpl;
import me.foxyg3n.foxlib.common.FoxLogger;
import me.foxyg3n.levelsystem.commands.Command;
import me.foxyg3n.levelsystem.commands.LevelCommand;
import me.foxyg3n.levelsystem.commands.TestCommand;
import me.foxyg3n.levelsystem.config.Config;
import me.foxyg3n.levelsystem.config.serdes.LevelInfoSerializer;
import me.foxyg3n.levelsystem.config.serdes.MaterialOrEntityTransformer;
import me.foxyg3n.levelsystem.config.serdes.PlayerLevelInfoSerializer;
import me.foxyg3n.levelsystem.config.serdes.StringOfflinePlayerTransformer;
import me.foxyg3n.levelsystem.data.PersistentDataHandler;
import me.foxyg3n.levelsystem.hooks.papi.LevelSystemPlaceholders;
import me.foxyg3n.levelsystem.level.PlayerLevelInfo;
import me.foxyg3n.levelsystem.level.PlayerLevelManager;
import me.foxyg3n.levelsystem.listeners.*;
import me.foxyg3n.levelsystem.utils.ActionBarHelper;
import me.foxyg3n.levelsystem.utils.MiningServiceUtils;
import me.foxyg3n.levelsystem.utils.RunesUtils;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class LevelSystem extends JavaPlugin {

    private static LevelSystem instance;
    private static FoxLogger logger;

    private final File CONFIG_FILE = new File(getDataFolder(), "config.yml");
    private final File PLAYER_INFO_FILE = new File(getDataFolder(), "players.json");

    private Config pluginConfig;
    private PlayerLevelManager playerLevelManager;
    private PersistentDataHandler persistentDataHandler;
    private MiningService miningService;

    @Override
    public void onLoad() {
        instance = this;
        logger = new FoxLogger(super.getLogger());

        CommandAPI.onLoad(new CommandAPIBukkitConfig(this).silentLogs(true));
    }

    @Override
    public void onEnable() {
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            logger.info("PlaceholderAPI found, enabling placeholders");
            new LevelSystemPlaceholders().register();
        }

        pluginConfig = ConfigManager.create(Config.class, it -> {
            it.withConfigurer(new YamlBukkitConfigurer(), new SerdesCommons());
            it.withSerdesPack(registry -> {
                registry.register(new MaterialOrEntityTransformer());
                registry.register(new LevelInfoSerializer());
            });
            it.withBindFile(CONFIG_FILE);
            it.saveDefaults();
            it.load(true);
        });

        playerLevelManager = ConfigManager.create(PlayerLevelManager.class, it -> {
            it.withConfigurer(new JsonGsonConfigurer());
            it.withSerdesPack(registry -> {
                registry.register(new StringOfflinePlayerTransformer());
                registry.register(new PlayerLevelInfoSerializer());
            });
            it.withBindFile(PLAYER_INFO_FILE);
            it.saveDefaults();
            it.load(true);
        });

        Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, () -> {
            for(Player player : Bukkit.getOnlinePlayers()) {
                if(Bukkit.getPluginManager().isPluginEnabled("Runy") && RunesUtils.isRunesBarActive(player)) continue;
                PlayerLevelInfo playerLevelInfo = playerLevelManager.getPlayerLevelInfo(player);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, ActionBarHelper.getActionBarMessage(playerLevelInfo));
            }
        }, 0, 20);

        persistentDataHandler = new PersistentDataHandler();
        persistentDataHandler.startHandler();

        miningService = MiningServiceImpl.initialize(this);
        miningService.registerModifier(1, MiningServiceUtils::handleBlockBreakExpGain);

        CommandAPI.onEnable();

        try {
            ImmutableCollection.Builder<Class<? extends Command>> commands = ImmutableSet.builder();

            if(System.getenv("FOX_DEBUG") != null) commands.add(TestCommand.class);

            commands.add(LevelCommand.class);

            for(Class<? extends Command> commandClass : commands.build()) {
                Command command = commandClass.getConstructor().newInstance();
                command.getCommand().register(this);
            }
        } catch(Exception error) {
            logger.error("Error while registering commands", error);
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        try {
            ImmutableCollection.Builder<Class<? extends Listener>> listeners = ImmutableSet.builder();

            listeners
                    .add(ExpGainListener.class)
                    .add(PlayerFishListener.class)
                    .add(MobDeathListener.class)
                    .add(PlayerBreakPlantListener.class)
                    .add(MobSpawnListener.class);

            if(Bukkit.getPluginManager().isPluginEnabled("MythicMobs")) {
                listeners.add(MythicMobDeathListener.class);
            }

            for(Class<? extends Listener> listenerClass : listeners.build()) {
                Listener listener = listenerClass.getConstructor().newInstance();
                Bukkit.getPluginManager().registerEvents(listener, this);
            }
        } catch(Exception error) {
            logger.error("Error while registering listeners", error);
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        logger.info("Enabling LevelSystem v" + getDescription().getVersion());

    }

    @Override
    public void onDisable() {
        if(playerLevelManager != null) {
            playerLevelManager.removeDefaultEntries();
            playerLevelManager.save();
        }

        if(persistentDataHandler != null) persistentDataHandler.stopHandler();
    }

    public Config getPluginConfig() {
        return pluginConfig;
    }

    public PlayerLevelManager getPlayerLevelManager() {
        return playerLevelManager;
    }

    public MiningService getMiningService() {
        return miningService;
    }

    public static LevelSystem getInstance() {
        return instance;
    }

    public static FoxLogger getPluginLogger() {
        return logger;
    }
}
