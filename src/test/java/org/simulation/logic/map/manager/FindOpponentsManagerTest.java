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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindOpponentsManagerTest {

    static ElementFactory elementFactory;

    static WorldMap worldMap;

    private static Element rock1;
    private static Element rock2;

    private static Element paper1;
    private static Element paper2;

    private static Element scissors1;
    private static Element scissors2;

    @BeforeAll
    public static void setUpBeforeAll(){

        elementFactory = new ElementFactory();

        worldMap = new WorldMapBuilder().build();

        rock1 = elementFactory.createElement(ElementEnum.ROCK, new Vector2D(0, 0));
        rock2 = elementFactory.createElement(ElementEnum.ROCK, new Vector2D(1, 1));
        paper1 = elementFactory.createElement(ElementEnum.PAPER, new Vector2D(3, 4));
        paper2 = elementFactory.createElement(ElementEnum.PAPER, new Vector2D(8, 7));
        scissors1 = elementFactory.createElement(ElementEnum.SCISSORS, new Vector2D(4, 9));
        scissors2 = elementFactory.createElement(ElementEnum.SCISSORS, new Vector2D(3, 9));

    }

    @BeforeEach
    public void setUpBeforeEach(){

        worldMap.getElements().clear();
        worldMap.getRemovedRoundElements().clear();

        rock1.setOpponent(null);
        rock2.setOpponent(null);
        paper1.setOpponent(null);
        paper2.setOpponent(null);
        scissors1.setOpponent(null);
        scissors2.setOpponent(null);

    }

    @Test
    public void findOpponentsTest1(){

        worldMap.getMapElementsManager().addElement(rock1);
        worldMap.getMapElementsManager().addElement(rock2);
        worldMap.getMapElementsManager().addElement(paper1);

        worldMap.getFindOpponentsManager().findOpponents();

        assertEquals(paper1, rock1.getOpponent());
        assertEquals(paper1, rock2.getOpponent());
        assertEquals(rock2, paper1.getOpponent());

    }

    @Test
    public void findOpponentsTest2(){

        worldMap.getMapElementsManager().addElement(paper1);
        worldMap.getMapElementsManager().addElement(rock1);
        worldMap.getMapElementsManager().addElement(rock2);

        paper1.setOpponent(rock2);

        worldMap.getRemovedRoundElements().put(rock2, rock2.getPosition());

        worldMap.getFindOpponentsManager().findOpponents();

        assertEquals(paper1, rock1.getOpponent());
        assertEquals(paper1, rock2.getOpponent());
        assertEquals(rock1, paper1.getOpponent());

    }

    @Test
    public void findOpponentsTet3(){


        worldMap.getMapElementsManager().addElement(rock1);
        worldMap.getMapElementsManager().addElement(rock2);
        worldMap.getMapElementsManager().addElement(paper1);
        worldMap.getMapElementsManager().addElement(paper2);
        worldMap.getMapElementsManager().addElement(scissors1);
        worldMap.getMapElementsManager().addElement(scissors2);

        worldMap.getFindOpponentsManager().findOpponents();

        assertEquals(paper1, rock1.getOpponent());
        assertEquals(paper1, rock2.getOpponent());
        assertEquals(rock2, paper1.getOpponent());
        assertEquals(scissors1, paper2.getOpponent());
        assertEquals(paper2, scissors1.getOpponent());
        assertEquals(paper1, scissors2.getOpponent());

    }

}
