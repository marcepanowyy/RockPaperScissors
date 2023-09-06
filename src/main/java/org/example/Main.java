package org.example;

import org.example.elements.Element;
import org.example.elements.Paper;
import org.example.elements.Rock;
import org.example.elements.Scissors;
import org.example.factory.PaperFactory;
import org.example.factory.RockFactory;
import org.example.factory.ScissorsFactory;
import org.example.utils.ElementDistancePair;

public class Main {
    public static void main(String[] args) {

        RockFactory rockFactory = new RockFactory();
        PaperFactory paperFactory = new PaperFactory();
        ScissorsFactory scissorsFactory = new ScissorsFactory();

        WorldMap map = new WorldMap(10, 10);

        Rock rock1 =  rockFactory.createElement(map, 1, 3);
        Paper paper1 = paperFactory.createElement(map, 4, 3);
        Scissors scissors1 = scissorsFactory.createElement(map, 4, 8);
        Scissors scissors2 = scissorsFactory.createElement(map, 5, 9);
//        Scissors scissors3 = scissorsFactory.createElement(map, 6, 3);
        Scissors scissors4 = scissorsFactory.createElement(map, 7, 2);

        map.addElement(rock1);
//        map.addElement(paper1);
        map.addElement(scissors1);
        map.addElement(scissors2);
//        map.addElement(scissors3);
        map.addElement(scissors4);

        map.draw();

        ElementDistancePair companion = map.findCompanion(rock1);
        System.out.println("companion of " + rock1.getSymbol() + " at position " + rock1.getPosition() +" is " + companion.getElement().getSymbol() + ", at position " + companion.getElement().getPosition() + " with distance " + companion.getDistance());
    }
}