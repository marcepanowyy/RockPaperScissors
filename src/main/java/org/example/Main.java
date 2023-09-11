package org.example;

//import javafx.application.Application;
import javafx.application.Application;
import org.example.gui.App;
import org.example.simulation.elements.Paper;
import org.example.simulation.elements.Rock;
import org.example.simulation.elements.Scissors;
import org.example.simulation.enums.ElementEnum;
import org.example.simulation.factory.ElementFactory;
import org.example.simulation.map.WorldMap;
import org.example.simulation.map.builder.WorldMapBuilder;
import org.example.simulation.utils.Vector2D;

public class Main {

    public static void main(String[] args) {

//        WorldMap map = new WorldMapBuilder()
//                .movementDistance(1)
//                .battleRange(2)
//                .build();
//
//        ElementFactory elementFactory = new ElementFactory();
//
//        Rock rock1 = (Rock) elementFactory.createElement(ElementEnum.ROCK, new Vector2D(0, 0));
//        Paper paper1 = (Paper) elementFactory.createElement(ElementEnum.PAPER, new Vector2D(8, 0));
//        Scissors scissors1 = (Scissors) elementFactory.createElement(ElementEnum.SCISSORS, new Vector2D(4, 9));
//
//        map.getMapElementsManager().addElement(rock1);
//        map.getMapElementsManager().addElement(paper1);
//        map.getMapElementsManager().addElement(scissors1);
//
//        map.startSimulation();

        Application.launch(App.class, args);

    }
}