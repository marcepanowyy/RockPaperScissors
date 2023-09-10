package org.example.map.manager;

import org.example.elements.Element;
import org.example.enums.ElementEnum;
import org.example.factory.ElementFactory;
import org.example.map.WorldMap;
import org.example.map.builder.WorldMapBuilder;
import org.example.utils.Vector2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateElementsManagerTest {

    static ElementFactory elementFactory;

    static WorldMap worldMap;

    private static Element rock;
    private static Element paper;
    private static Element scissors;

    @BeforeAll
    public static void setUpBeforeAll(){

        elementFactory = new ElementFactory();

        worldMap = new WorldMapBuilder()
                .width(22)
                .height(1)
                .build();

    }

    @BeforeEach
    public void setUpBeforeEach(){

        worldMap.getElements().clear();

        rock = elementFactory.createElement(ElementEnum.ROCK, new Vector2D(0, 0));
        paper = elementFactory.createElement(ElementEnum.PAPER, new Vector2D(4, 0));
        scissors = elementFactory.createElement(ElementEnum.SCISSORS, new Vector2D(20, 0));

        worldMap.getMapElementsManager().addElement(rock);
        worldMap.getMapElementsManager().addElement(paper);
        worldMap.getMapElementsManager().addElement(scissors);

    }

    @Test
    public void updateElementsTest1(){

        rock.setOpponent(paper);
        paper.setOpponent(rock);
        scissors.setOpponent(paper);

        worldMap.getUpdateElementsManager().updateElements();

        assertEquals(rock.getPosition(), new Vector2D(1, 0));
        assertEquals(paper.getPosition(), new Vector2D(3, 0));
        assertEquals(scissors.getPosition(), new Vector2D(19, 0));

    }

    @Test
    public void updateElementsTest2(){

        rock.setOpponent(scissors);
        paper.setOpponent(scissors);
        scissors.setOpponent(paper);

        worldMap.getUpdateElementsManager().updateElements();

        assertEquals(rock.getPosition(), new Vector2D(1, 0));
        assertEquals(paper.getPosition(), new Vector2D(5, 0));
        assertEquals(scissors.getPosition(), new Vector2D(19, 0));

    }

    @Test
    public void updateElementsTest3(){

        rock.setOpponent(scissors);

        worldMap.getUpdateElementsManager().updateElements();

        assertEquals(rock.getPosition(), new Vector2D(1, 0));
        assertEquals(paper.getPosition(), new Vector2D(4, 0));
        assertEquals(scissors.getPosition(), new Vector2D(20, 0));

    }

}
