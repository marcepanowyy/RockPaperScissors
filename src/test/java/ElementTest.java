import org.example.WorldMap;
import org.example.WorldMapBuilder;
import org.example.elements.Paper;
import org.example.elements.Rock;
import org.example.enums.ElementEnum;
import org.example.factory.ElementFactory;
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

        rock = (Rock) elementFactory.createElement(ElementEnum.ROCK, 0, 0);
        paper = (Paper) elementFactory.createElement(ElementEnum.PAPER, 3, 4);

    }

    @Test
    public void testCalculateDistanceToCompanion(){
        double distance = rock.calculateDistanceToOther(paper);
        assertEquals(5.0, distance, 0.001);
    }


}
