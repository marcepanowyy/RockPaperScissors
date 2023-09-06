package org.example;

public class WorldMapBuilder {
    private int width;
    private int height;
    private double movementDistance;
    private double battleRange;

    public WorldMapBuilder width(int width) {
        this.width = width;
        return this;
    }

    public WorldMapBuilder height(int height) {
        this.height = height;
        return this;
    }

    public WorldMapBuilder movementDistance(double movementDistance) {
        this.movementDistance = movementDistance;
        return this;
    }

    public WorldMapBuilder battleRange(double battleRange) {
        this.battleRange = battleRange;
        return this;
    }

    public WorldMap build() {
        return new WorldMap(width, height, movementDistance, battleRange);
    }

}
