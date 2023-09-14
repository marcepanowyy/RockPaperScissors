package org.simulation.logic.map.manager;

import org.simulation.logic.element.Element;
import org.simulation.logic.enums.ElementEnum;
import org.simulation.logic.factory.ElementFactory;
import org.simulation.logic.map.WorldMap;
import org.simulation.logic.builder.WorldMapBuilder;
import org.simulation.logic.utils.Vector2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MapElementsManagerTest {

    static ElementFactory elementFactory;

    static WorldMap worldMap;

    private static Element rock;
    private static Element paper;
    private static Element scissors;

    @BeforeAll
    public static void setUpBeforeAll(){

        elementFactory = new ElementFactory();

        worldMap = new WorldMapBuilder().build();

        rock = elementFactory.createElement(ElementEnum.ROCK, new Vector2D(3.12, 5.55));
        paper = elementFactory.createElement(ElementEnum.PAPER, new Vector2D(8.42, 4.21));
        scissors = elementFactory.createElement(ElementEnum.SCISSORS, new Vector2D(13.32, 0.42));

    }

    @BeforeEach
    public void setUpBeforeEach(){

        worldMap.getElements().clear();

    }

    @Test
    public void addElementTest(){

        worldMap.getMapElementsManager().addElement(rock);
        assertTrue(worldMap.getElements().contains(rock));

    }

    @Test
    public void removeElementTest(){

        worldMap.getMapElementsManager().addElement(paper);
        worldMap.getMapElementsManager().removeElement(paper);
        assertFalse(worldMap.getElements().contains(paper));

    }

    @Test
    public void getElementAtPositionTest(){

        worldMap.getMapElementsManager().addElement(paper);

        Element element = worldMap.getMapElementsManager().getElementAtPosition(paper.getPosition());

        assertEquals(paper.getPosition(), element.getPosition());

    }

    @Test
    public void isWithinBoundsTest(){

        boolean result = worldMap.getMapElementsManager().isWithinBounds(scissors.getPosition());
        assertFalse(result);

    }

    @Test
    public void addElementOutOfBoundsTest(){

        assertThrows(IllegalArgumentException.class, () -> worldMap.getMapElementsManager().addElement(scissors));

    }

}
