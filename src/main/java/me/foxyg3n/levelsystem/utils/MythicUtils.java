package me.foxyg3n.levelsystem.utils;

import io.lumine.mythic.api.MythicProvider;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.bukkit.utils.serialize.Position;
import io.lumine.mythic.core.drops.DropTable;
import io.lumine.mythic.core.mobs.ActiveMob;
import me.foxyg3n.foxlib.libs.panda.std.Option;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class MythicUtils {

    public static Option<ActiveMob> getFromEntity(Entity entity) {
        return Option.ofOptional(MythicBukkit.inst().getMobManager().getActiveMob(entity.getUniqueId()));
    }

    public static Option<MythicMob> getMythicMob(String name) {
        return Option.ofOptional(MythicProvider.get().getMobManager().getMythicMob(name));
    }

    public static Option<DropTable> getDropTable(String name) {
        return Option.ofOptional(MythicProvider.get().getDropManager().getDropTable(name));
    }

    public static AbstractLocation toAbstractLocation(Location location) {
        return new AbstractLocation(Position.of(location));
    }

}
