import org.example.WorldMap;
import org.example.WorldMapBuilder;
import org.example.elements.Paper;
import org.example.elements.Rock;
import org.example.elements.Scissors;
import org.example.factory.PaperFactory;
import org.example.factory.RockFactory;
import org.example.factory.ScissorsFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElementTest {

    static WorldMap worldMap;
    static RockFactory rockFactory;
    static PaperFactory paperFactory;
    static ScissorsFactory scissorsFactory;
    private static Rock rock;
    private static Paper paper;
    private static Scissors scissors;

    @BeforeAll
    public static void setUpBeforeAll(){

        worldMap = new WorldMapBuilder()
                .width(10)
                .height(10)
                .movementDistance(1)
                .battleRange(2)
                .build();


        rockFactory = new RockFactory();
        paperFactory = new PaperFactory();
        scissorsFactory = new ScissorsFactory();

        rock = rockFactory.createElement(worldMap, 0, 0);
        paper = paperFactory.createElement(worldMap, 3, 4);

    }

    @Test
    public void testCalculateDistanceToCompanion(){
        double distance = rock.calculateDistanceToOther(paper);
        assertEquals(5.0, distance, 0.001);
    }


}
