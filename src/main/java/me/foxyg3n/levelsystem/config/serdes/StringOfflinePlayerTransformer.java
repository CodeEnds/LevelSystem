package me.foxyg3n.levelsystem.config.serdes;

import eu.okaeri.configs.schema.GenericsPair;
import eu.okaeri.configs.serdes.BidirectionalTransformer;
import eu.okaeri.configs.serdes.SerdesContext;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class StringOfflinePlayerTransformer extends BidirectionalTransformer<String, OfflinePlayer> {

    @Override
    public GenericsPair<String, OfflinePlayer> getPair() {
        return this.genericsPair(String.class, OfflinePlayer.class);
    }

    @Override
    public OfflinePlayer leftToRight(@NotNull String uuidString, @NotNull SerdesContext serdesContext) {
        UUID uuid = UUID.fromString(uuidString);
        return Bukkit.getOfflinePlayer(uuid);
    }

    @Override
    public String rightToLeft(@NotNull OfflinePlayer offlinePlayer, @NotNull SerdesContext serdesContext) {
        return offlinePlayer.getUniqueId().toString();
    }
}
