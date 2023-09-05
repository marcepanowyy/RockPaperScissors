package org.example;

import org.example.elements.Paper;
import org.example.factory.PaperFactory;
import org.example.factory.RockFactory;
import org.example.factory.ScissorsFactory;

public class Main {
    public static void main(String[] args) {

        RockFactory rockFactory = new RockFactory();
        PaperFactory paperFactory = new PaperFactory();
        ScissorsFactory scissorsFactory = new ScissorsFactory();

        WorldMap map = new WorldMap(10, 10);


        map.addElement(rockFactory.createElement(1, 1));
        map.addElement(scissorsFactory.createElement(5, 3));
        map.addElement(scissorsFactory.createElement(9, 2));

        map.draw();

    }
}