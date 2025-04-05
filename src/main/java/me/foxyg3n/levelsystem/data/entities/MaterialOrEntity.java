package me.foxyg3n.levelsystem.data.entities;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

public class MaterialOrEntity {

    private final String name;

    public MaterialOrEntity(String name) {
        this.name = name;
    }

    public boolean isEntity() {
        try {
            EntityType.valueOf(this.name);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isMaterial() {
        try {
            Material.valueOf(this.name);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public String getAsString() {
        return this.name;
    }

    public EntityType getAsEntity() {
        return EntityType.valueOf(this.name);
    }

    public Material getAsMaterial() {
        return Material.valueOf(this.name);
    }

    public static MaterialOrEntity of(String name) {
        return new MaterialOrEntity(name.toUpperCase());
    }

    public static MaterialOrEntity of(EntityType entityType) {
        return new MaterialOrEntity(entityType.name());
    }

    public static MaterialOrEntity of(Material material) {
        return new MaterialOrEntity(material.name());
    }

}
