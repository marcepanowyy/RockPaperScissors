package org.simulation.logic.factory;

import org.simulation.logic.element.Element;
import org.simulation.logic.enums.ElementEnum;
import org.simulation.logic.utils.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ElementFactoryTest {

    private ElementFactory elementFactory;

    @BeforeEach
    void setUpBeforeEach() {

        elementFactory = new ElementFactory();

    }

    @Test
    void testCreateRock() {

        Element rock = elementFactory.createElement(ElementEnum.ROCK, new Vector2D(1, 2));

        assertNotNull(rock);
        assertEquals(ElementEnum.ROCK, rock.getSymbol());
        assertEquals(new Vector2D(1, 2), rock.getPosition());

    }

    @Test
    void testCreatePaper() {

        Element paper = elementFactory.createElement(ElementEnum.PAPER, new Vector2D(3, 4));

        assertNotNull(paper);
        assertEquals(ElementEnum.PAPER, paper.getSymbol());
        assertEquals(new Vector2D(3, 4), paper.getPosition());

    }

    @Test
    void testCreateScissors() {

        Element scissors = elementFactory.createElement(ElementEnum.SCISSORS, new Vector2D(5, 6));

        assertNotNull(scissors);
        assertEquals(ElementEnum.SCISSORS, scissors.getSymbol());
        assertEquals(new Vector2D(5, 6), scissors.getPosition());

    }

    @Test
    void testInvalidElementType() {

        assertThrows(IllegalArgumentException.class, () -> {
            elementFactory.createElement(null, new Vector2D(1, 1));
        });
    }

}
