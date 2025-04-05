package me.foxyg3n.levelsystem.config.serdes;

import eu.okaeri.configs.schema.GenericsPair;
import eu.okaeri.configs.serdes.BidirectionalTransformer;
import eu.okaeri.configs.serdes.SerdesContext;
import me.foxyg3n.levelsystem.data.entities.MaterialOrEntity;
import org.jetbrains.annotations.NotNull;

public class MaterialOrEntityTransformer extends BidirectionalTransformer<MaterialOrEntity, String> {

    @Override
    public GenericsPair<MaterialOrEntity, String> getPair() {
        return this.genericsPair(MaterialOrEntity.class, String.class);
    }

    @Override
    public String leftToRight(@NotNull MaterialOrEntity materialOrEntity, @NotNull SerdesContext serdesContext) {
        return materialOrEntity.getAsString();
    }

    @Override
    public MaterialOrEntity rightToLeft(@NotNull String data, @NotNull SerdesContext serdesContext) {
        return MaterialOrEntity.of(data);
    }
}
