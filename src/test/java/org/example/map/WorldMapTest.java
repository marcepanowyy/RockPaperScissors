package org.example.map;

import org.example.map.WorldMap;
import org.example.elements.Element;
import org.example.elements.Rock;
import org.example.enums.ElementEnum;
import org.example.factory.ElementFactory;
import org.example.map.builder.WorldMapBuilder;
import org.example.utils.Vector2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorldMapTest {

    static WorldMap worldMap;

    static ElementFactory elementFactory;

    private static Element rock1;

    private static Element paper1;

    private static Element paper2;

    @BeforeAll
    public static void setUpBeforeAll(){

        elementFactory = new ElementFactory();

    }

    @BeforeEach
    public void setUpBeforeEach(){

        worldMap = new WorldMapBuilder()
                .height(15)
                .build();

        rock1 = elementFactory.createElement(ElementEnum.ROCK, new Vector2D(0, 0));
        paper1 = elementFactory.createElement(ElementEnum.PAPER, new Vector2D(3, 4));
        paper2 = elementFactory.createElement(ElementEnum.PAPER, new Vector2D(5, 12));

    }

    @Test
    public void testAddElement() {

//        worldMap.addElement(rock1);
//        assertTrue(worldMap.getElements().contains(rock1));

    }

    @Test
    public void testAddElementOutOfBounds() {

//        Rock element = (Rock) elementFactory.createElement(ElementEnum.ROCK, new Vector2D(20, 20));
//        assertThrows(IllegalArgumentException.class, () -> worldMap.addElement(element));

    }

    @Test
    public void testRemoveElement() {

//        worldMap.addElement(rock1);
//        worldMap.removeElement(rock1);
//        assertFalse(worldMap.getElements().contains(rock1));

    }

    @Test
    public void testFindOpponent(){

//        worldMap.addElement(rock1);
//        worldMap.addElement(paper1);
//        worldMap.addElement(paper2);
//
//        worldMap.findOpponent(rock1);
//        worldMap.findOpponent(paper1);
//        worldMap.findOpponent(paper2);
//
//        assertEquals(rock1.getOpponent(), paper1);
//        assertEquals(paper1.getOpponent(), rock1);
//        assertEquals(paper2.getOpponent(), rock1);

    }

    @Test
    public void testUpdateElement() {

//        worldMap.addElement(rock1);
//        worldMap.addElement(paper1);
//        worldMap.addElement(paper2);
//
//        worldMap.findOpponents();
//
//        worldMap.updateElement(rock1);
//        worldMap.updateElement(paper1);
//        worldMap.updateElement(paper2);
//
//        assertEquals(rock1.getPosition(), new Vector2D(0.6, 0.8));
//        assertEquals(paper1.getPosition(), new Vector2D(2.4, 3.2));
//        assertEquals(paper2.getPosition(), new Vector2D(4.62, 11.08));

    }

}
