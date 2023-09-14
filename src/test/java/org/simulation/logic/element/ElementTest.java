package org.simulation.logic.element;

import org.simulation.logic.map.WorldMap;
import org.simulation.logic.builder.WorldMapBuilder;
import org.simulation.logic.enums.ElementEnum;
import org.simulation.logic.factory.ElementFactory;
import org.simulation.logic.utils.Vector2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElementTest {

    static WorldMap worldMap;

    static ElementFactory elementFactory;

    static Rock rock;

    static Paper paper;

    @BeforeAll
    public static void setUpBeforeAll(){

        elementFactory = new ElementFactory();

        worldMap = new WorldMapBuilder()
                .width(10)
                .height(10)
                .movementDistance(1)
                .battleRange(2)
                .build();

        rock = (Rock) elementFactory.createElement(ElementEnum.ROCK, new Vector2D(0, 0));
        paper = (Paper) elementFactory.createElement(ElementEnum.PAPER, new Vector2D(3, 4));

    }

    @Test
    public void testCalculateDistanceToCompanion(){

        double distance = rock.calculateDistanceToOther(paper);
        assertEquals(5.0, distance, 0.001);

    }

}
