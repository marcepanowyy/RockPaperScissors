import org.example.WorldMap;
import org.example.elements.Element;
import org.example.enums.ElementEnum;
import org.example.utils.Vector2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorldMapTest {
    private static WorldMap worldMap;
    private static Element rock1;
    private static Element paper1;
    private static Element paper2;
    @BeforeAll
    public static void setUpBeforeAll(){
        rock1 = new Element(worldMap, ElementEnum.ROCK, 0, 0);
        paper1 = new Element(worldMap, ElementEnum.PAPER, 3, 4);
        paper2 = new Element(worldMap, ElementEnum.PAPER, 5, 12);
    }
    @BeforeEach
    public void setUpBeforeEach(){
        worldMap = new WorldMap(15, 15);
    }
    @Test
    public void testAddElement() {
        worldMap.addElement(rock1);
        assertTrue(worldMap.getElements().contains(rock1));
    }
    @Test
    public void testAddElementOutOfBounds() {
        WorldMap worldMap = new WorldMap(10, 10);
        Element element = new Element(worldMap, ElementEnum.ROCK, 20, 20);
        assertThrows(IllegalArgumentException.class, () -> worldMap.addElement(element));
    }
    @Test
    public void testRemoveElement() {
        worldMap.addElement(rock1);
        worldMap.removeElement(rock1);
        assertFalse(worldMap.getElements().contains(rock1));
    }
    @Test
    public void testFindCompanion(){

        worldMap.addElement(rock1);
        worldMap.addElement(paper1);
        worldMap.addElement(paper2);

        worldMap.findCompanion(rock1);
        worldMap.findCompanion(paper1);
        worldMap.findCompanion(paper2);

        assertEquals(rock1.getCompanion(), paper1);
        assertEquals(paper1.getCompanion(), rock1);
        assertEquals(paper2.getCompanion(), rock1);

    }
    @Test
    public void testUpdateElement() {

        worldMap.addElement(rock1);
        worldMap.addElement(paper1);
        worldMap.addElement(paper2);

        worldMap.findCompanions();

        worldMap.updateElement(rock1, 1); // paper companion is updating also
        worldMap.updateElement(paper2, 1);


        System.out.println(rock1.getPosition());
        System.out.println(paper1.getPosition());
        System.out.println(paper2.getPosition());

        assertEquals(rock1.getPosition(), new Vector2D(0.6, 0.8));
        assertEquals(paper1.getPosition(), new Vector2D(2.4, 3.2));
        assertEquals(paper2.getPosition(), new Vector2D(4.62, 11.08));

    }


}
