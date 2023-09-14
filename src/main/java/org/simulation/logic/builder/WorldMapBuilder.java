package org.simulation.logic.builder;

import org.simulation.logic.map.WorldMap;

public class WorldMapBuilder {

    private int width = 10;

    private int height = 10;

    private double movementDistance = 1;

    private double battleRange = 2;

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
