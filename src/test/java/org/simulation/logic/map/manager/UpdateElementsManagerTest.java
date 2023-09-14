package org.simulation.logic.map.manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.simulation.logic.builder.WorldMapBuilder;
import org.simulation.logic.element.Element;
import org.simulation.logic.enums.ElementEnum;
import org.simulation.logic.factory.ElementFactory;
import org.simulation.logic.map.WorldMap;
import org.simulation.logic.utils.Vector2D;

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

        assertEquals(new Vector2D(1, 0), rock.getPosition());
        assertEquals(new Vector2D(3, 0), paper.getPosition());
        assertEquals(new Vector2D(19, 0), scissors.getPosition());

    }

    @Test
    public void updateElementsTest2(){

        rock.setOpponent(scissors);
        paper.setOpponent(scissors);
        scissors.setOpponent(paper);

        worldMap.getUpdateElementsManager().updateElements();

        assertEquals(new Vector2D(1, 0), rock.getPosition());
        assertEquals(new Vector2D(5, 0), paper.getPosition());
        assertEquals(new Vector2D(19, 0), scissors.getPosition());

    }

    @Test
    public void updateElementsTest3(){

        rock.setOpponent(scissors);

        worldMap.getUpdateElementsManager().updateElements();

        assertEquals(new Vector2D(1, 0), rock.getPosition());
        assertEquals( new Vector2D(4, 0), paper.getPosition());
        assertEquals( new Vector2D(20, 0), scissors.getPosition());

    }

}
