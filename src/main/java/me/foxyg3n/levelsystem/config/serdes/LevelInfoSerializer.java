package me.foxyg3n.levelsystem.config.serdes;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import me.foxyg3n.levelsystem.data.entities.LevelInfo;
import org.jetbrains.annotations.NotNull;

public class LevelInfoSerializer implements ObjectSerializer<LevelInfo> {

    @Override
    public boolean supports(@NotNull Class<? super LevelInfo> type) {
        return LevelInfo.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NotNull LevelInfo levelInfo, @NotNull SerializationData data, @NotNull GenericsDeclaration generics) {
        data.add("level", levelInfo.getLevel());
        data.add("requiredExp", levelInfo.getRequiredExperience());
    }

    @Override
    public LevelInfo deserialize(@NotNull DeserializationData data, @NotNull GenericsDeclaration generics) {
        int level = data.get("level", Integer.class);
        double requiredExp = data.get("requiredExp", Double.class);

        return new LevelInfo(level, requiredExp);
    }
}
