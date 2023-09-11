package org.example.simulation.map.builder;

import org.example.simulation.map.WorldMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorldMapBuilderTest {

    static WorldMap worldMap;

    @Test
    public void WorldMapDefaultBuilderTest(){

        worldMap = new WorldMapBuilder().build();

        assertEquals(10, worldMap.getWidth());
        assertEquals(10, worldMap.getHeight());
        assertEquals(1, worldMap.getMovementDistance());
        assertEquals(2, worldMap.getBattleRange());

    }

    @Test
    public void WorldMapBuilderWithParamsTest1(){

        worldMap = new WorldMapBuilder()
                .height(200)
                .battleRange(0.5)
                .build();

        assertEquals(10, worldMap.getWidth());
        assertEquals(200, worldMap.getHeight());
        assertEquals(1, worldMap.getMovementDistance());
        assertEquals(0.5, worldMap.getBattleRange());

    }

    @Test
    public void WorldMapBuilderWithParamsTest2(){

        worldMap = new WorldMapBuilder()
                .width(15)
                .height(20)
                .movementDistance(11)
                .battleRange(42)
                .build();

        assertEquals(15, worldMap.getWidth());
        assertEquals(20, worldMap.getHeight());
        assertEquals(11, worldMap.getMovementDistance());
        assertEquals(42, worldMap.getBattleRange());

    }

    @Test
    public void WorldMapBuilderWithParamsTest3(){

        worldMap = new WorldMapBuilder()
                .movementDistance(3)
                .build();

        assertEquals(10, worldMap.getWidth());
        assertEquals(10, worldMap.getHeight());
        assertEquals(3, worldMap.getMovementDistance());
        assertEquals(2, worldMap.getBattleRange());

    }

}
