package org.example.map.builder;

import org.example.map.WorldMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorldMapBuilderTest {

    static WorldMap worldMap;

    @Test
    public void WorldMapDefaultBuilderTest(){

        worldMap = new WorldMapBuilder().build();

        assertEquals(worldMap.getWidth(), 10);
        assertEquals(worldMap.getHeight(), 10);
        assertEquals(worldMap.getMovementDistance(), 1);
        assertEquals(worldMap.getBattleRange(), 2);

    }

    @Test
    public void WorldMapBuilderWithParamsTest1(){

        worldMap = new WorldMapBuilder()
                .height(200)
                .battleRange(0.5)
                .build();

        assertEquals(worldMap.getWidth(), 10);
        assertEquals(worldMap.getHeight(), 200);
        assertEquals(worldMap.getMovementDistance(), 1);
        assertEquals(worldMap.getBattleRange(), 0.5);

    }

    @Test
    public void WorldMapBuilderWithParamsTest2(){

        worldMap = new WorldMapBuilder()
                .width(15)
                .height(20)
                .movementDistance(11)
                .battleRange(42)
                .build();

        assertEquals(worldMap.getWidth(), 15);
        assertEquals(worldMap.getHeight(), 20);
        assertEquals(worldMap.getMovementDistance(), 11);
        assertEquals(worldMap.getBattleRange(), 42);

    }

    @Test
    public void WorldMapBuilderWithParamsTest3(){

        worldMap = new WorldMapBuilder()
                .movementDistance(3)
                .build();

        assertEquals(worldMap.getWidth(), 10);
        assertEquals(worldMap.getHeight(), 10);
        assertEquals(worldMap.getMovementDistance(), 3);
        assertEquals(worldMap.getBattleRange(), 2);

    }

}
