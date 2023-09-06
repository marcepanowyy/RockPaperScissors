package org.example;

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
                .movementDistance(1)
                .battleRange(2)
                .build();

        Rock rock1 =  rockFactory.createElement(0, 0);
        Paper paper1 = paperFactory.createElement(8, 0);
//        Scissors scissors1 = scissorsFactory.createElement(7, 2);

        map.addElement(rock1);
        map.addElement(paper1);
//        map.addElement(scissors1);

        map.init();

        map.draw();
        map.performRound();
        map.draw();
        map.performRound();

        map.draw();
        map.performRound();

        map.draw();
        map.performRound();
    }
}