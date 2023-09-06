import org.example.WorldMap;
import org.example.WorldMapBuilder;
import org.example.elements.Element;
import org.example.enums.ElementEnum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElementTest {

    private static WorldMap worldMap;
    private static Element rock;
    private static Element paper;

    @BeforeAll
    public static void setUpBeforeAll(){

        worldMap = new WorldMapBuilder()
                .width(10)
                .height(10)
                .movementDistance(1)
                .battleRange(2)
                .build();


        rock = new Element(worldMap, ElementEnum.ROCK, 0, 0);
        paper = new Element(worldMap, ElementEnum.PAPER, 3, 4);
    }

    @Test
    public void testCalculateDistanceToCompanion(){
        double distance = rock.calculateDistanceToOther(paper);
        assertEquals(5.0, distance, 0.001);
    }


}
