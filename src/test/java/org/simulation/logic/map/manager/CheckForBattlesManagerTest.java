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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckForBattlesManagerTest {

    static ElementFactory elementFactory;

    static WorldMap worldMap;

    private static Element rock;

    private static Element paper;

    private static Element scissors;

    @BeforeAll
    public static void setUpBeforeAll(){

        elementFactory = new ElementFactory();

        worldMap = new WorldMapBuilder().build();

        rock = elementFactory.createElement(ElementEnum.ROCK, new Vector2D(0, 0));
        paper = elementFactory.createElement(ElementEnum.PAPER, new Vector2D(0, 2.01));
        scissors = elementFactory.createElement(ElementEnum.SCISSORS, new Vector2D(0, 2));

    }

    @BeforeEach
    public void setUpBeforeEach(){

        worldMap.getUniqueBattlePairs().clear();
        worldMap.getElements().clear();

    }

    // improper range + opponents for each other

    @Test
    public void checkForBattlesTest1(){

        worldMap.getMapElementsManager().addElement(rock);
        worldMap.getMapElementsManager().addElement(paper);

        rock.setOpponent(paper);
        paper.setOpponent(rock);

        worldMap.getCheckForBattlesManager().checkForBattles();

        assertTrue(worldMap.getUniqueBattlePairs().isEmpty());

    }


    // proper range + opponents for each other

    @Test
    public void checkForBattlesTest2(){

        worldMap.getMapElementsManager().addElement(rock);
        worldMap.getMapElementsManager().addElement(scissors);

        rock.setOpponent(scissors);
        scissors.setOpponent(rock);

        worldMap.getCheckForBattlesManager().checkForBattles();

        assertEquals(1, worldMap.getUniqueBattlePairs().size());

    }


    // proper range + having different opponents

    @Test
    public void checkForBattlesTest3(){

        worldMap.getMapElementsManager().addElement(rock);
        worldMap.getMapElementsManager().addElement(paper);
        worldMap.getMapElementsManager().addElement(scissors);

        scissors.setOpponent(paper);

        worldMap.getCheckForBattlesManager().checkForBattles();

        assertTrue(worldMap.getUniqueBattlePairs().isEmpty());

    }

}
