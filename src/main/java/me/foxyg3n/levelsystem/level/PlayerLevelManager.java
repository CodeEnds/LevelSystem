package me.foxyg3n.levelsystem.level;

import eu.okaeri.configs.OkaeriConfig;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerLevelManager extends OkaeriConfig {

    @SuppressWarnings("FieldMayBeFinal")
    private Map<UUID, PlayerLevelInfo> playerLevels = new HashMap<>();

    public PlayerLevelInfo getPlayerLevelInfo(OfflinePlayer player) {
        return playerLevels.computeIfAbsent(player.getUniqueId(), uuid -> new PlayerLevelInfo(player));
    }

    public void removeDefaultEntries() {
        playerLevels.entrySet().removeIf(entry -> entry.getValue().getExperience() == 0 && entry.getValue().getLevel() == 1);
    }

}
