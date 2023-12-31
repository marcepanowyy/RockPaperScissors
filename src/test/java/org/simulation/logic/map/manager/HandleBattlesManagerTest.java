package org.simulation.logic.map.manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.simulation.logic.builder.WorldMapBuilder;
import org.simulation.logic.element.Element;
import org.simulation.logic.enums.ElementEnum;
import org.simulation.logic.factory.ElementFactory;
import org.simulation.logic.map.WorldMap;
import org.simulation.logic.utils.UniquePair;
import org.simulation.logic.utils.Vector2D;

import static org.junit.jupiter.api.Assertions.*;

public class HandleBattlesManagerTest {

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

        worldMap.getMapElementsManager().addElement(rock1);
        worldMap.getMapElementsManager().addElement(rock2);
        worldMap.getMapElementsManager().addElement(paper1);
        worldMap.getMapElementsManager().addElement(paper2);
        worldMap.getMapElementsManager().addElement(scissors1);
        worldMap.getMapElementsManager().addElement(scissors2);

    }

    @Test
    public void handleBattlesTest(){

        UniquePair battlePair1 = new UniquePair(rock1, scissors2);
        UniquePair battlePair2 = new UniquePair(paper1, scissors1);
        UniquePair battlePair3 = new UniquePair(rock2, paper2);

        worldMap.getUniqueBattlePairs().add(battlePair1);
        worldMap.getUniqueBattlePairs().add(battlePair2);
        worldMap.getUniqueBattlePairs().add(battlePair3);

        worldMap.getHandleBattlesManager().handleBattles();

        // check if 'elements' [has / does not have] [winning / losing ] elements

        assertTrue(worldMap.getElements().contains(scissors1));
        assertTrue(worldMap.getElements().contains(rock1));
        assertTrue(worldMap.getElements().contains(paper2));

        assertFalse(worldMap.getElements().contains(paper1));
        assertFalse(worldMap.getElements().contains(scissors2));
        assertFalse(worldMap.getElements().contains(rock2));

        // check if cleared 'uniqueBattlePairs' after calling 'handleBattles()'

        assertTrue(worldMap.getUniqueBattlePairs().isEmpty());

        // check if added to 'removedRoundElements' losing elements

        assertEquals(paper1.getPosition(), worldMap.getRemovedRoundElements().get(paper1));
        assertEquals(scissors2.getPosition(), worldMap.getRemovedRoundElements().get(scissors2));
        assertEquals(rock2.getPosition(), worldMap.getRemovedRoundElements().get(rock2));

        // check if set winning elements' opponent to 'null'

        assertNull(scissors1.getOpponent());
        assertNull(rock1.getOpponent());
        assertNull(paper2.getOpponent());

        // check if created new elements out of losing elements

        Element newElement1 = worldMap.getMapElementsManager().getElementAtPosition(scissors2.getPosition());
        Element newElement2 = worldMap.getMapElementsManager().getElementAtPosition(paper1.getPosition());
        Element newElement3 = worldMap.getMapElementsManager().getElementAtPosition(rock2.getPosition());

        assertNotNull(newElement1);
        assertNotNull(newElement2);
        assertNotNull(newElement3);

        assertEquals(ElementEnum.ROCK, newElement1.getSymbol());
        assertEquals(scissors2.getPosition(), newElement1.getPosition());

        assertEquals(ElementEnum.SCISSORS, newElement2.getSymbol());
        assertEquals(paper1.getPosition(), newElement2.getPosition());

        assertEquals(ElementEnum.PAPER, newElement3.getSymbol());
        assertEquals(rock2.getPosition(), newElement3.getPosition());

    }

}
