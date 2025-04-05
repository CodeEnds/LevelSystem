package me.foxyg3n.levelsystem.data.entities;

public class LevelInfo {

    private final int level;
    private final double requiredExperience;

    public LevelInfo(int level, double requiredExperience) {
        this.level = level;
        this.requiredExperience = requiredExperience;
    }

    public int getLevel() {
        return level;
    }

    public double getRequiredExperience() {
        return requiredExperience;
    }

}
