package org.example;

import org.example.elements.Element;
import org.example.elements.Paper;
import org.example.elements.Rock;
import org.example.elements.Scissors;
import org.example.factory.PaperFactory;
import org.example.factory.RockFactory;
import org.example.factory.ScissorsFactory;

public class Main {
    public static void main(String[] args) {

        RockFactory rockFactory = new RockFactory();
        PaperFactory paperFactory = new PaperFactory();
        ScissorsFactory scissorsFactory = new ScissorsFactory();

        WorldMap map = new WorldMapBuilder()
                .width(10)
                .height(10)
                .movementDistance(0.2)
                .battleRange(0.5)
                .build();

        Rock rock1 =  rockFactory.createElement(map, 1, 3);
        Paper paper1 = paperFactory.createElement(map, 4, 3);
//        Scissors scissors1 = scissorsFactory.createElement(map, 7, 2);

        map.addElement(rock1);
        map.addElement(paper1);
//        map.addElement(scissors1);

        map.init();

        map.draw();
        map.updateElements();
        map.draw();
        map.updateElements();
        map.draw();
        map.updateElements();
        map.draw();
        map.updateElements();
        map.draw();
        map.updateElements();
        map.draw();
        map.updateElements();
        map.draw();

    }
}