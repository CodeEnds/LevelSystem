package me.foxyg3n.levelsystem.config.serdes;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import me.foxyg3n.foxlib.common.utils.MathUtils;
import me.foxyg3n.levelsystem.level.PlayerLevelInfo;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class PlayerLevelInfoSerializer implements ObjectSerializer<PlayerLevelInfo> {

    @Override
    public boolean supports(@NotNull Class<? super PlayerLevelInfo> type) {
        return PlayerLevelInfo.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NotNull PlayerLevelInfo playerLevelInfo, @NotNull SerializationData data, @NotNull GenericsDeclaration generics) {
        data.add("player", playerLevelInfo.getOfflinePlayer(), OfflinePlayer.class);
        data.add("level", playerLevelInfo.getLevel());
        data.add("experience", MathUtils.round(playerLevelInfo.getExperience(), 2));
    }

    @Override
    public PlayerLevelInfo deserialize(@NotNull DeserializationData data, @NotNull GenericsDeclaration generics) {
        OfflinePlayer player = data.get("player", OfflinePlayer.class);
        int level = data.get("level", Integer.class);
        double experience = data.get("experience", Double.class);

        PlayerLevelInfo playerLevelInfo = new PlayerLevelInfo(player);
        playerLevelInfo.setLevel(level);
        playerLevelInfo.setExperience(experience);

        return playerLevelInfo;
    }
}
