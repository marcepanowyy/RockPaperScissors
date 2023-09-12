package org.simulation;

import javafx.application.Application;
import org.simulation.gui.App;
import org.simulation.logic.elements.Paper;
import org.simulation.logic.elements.Rock;
import org.simulation.logic.elements.Scissors;
import org.simulation.logic.enums.ElementEnum;
import org.simulation.logic.factory.ElementFactory;
import org.simulation.logic.map.WorldMap;
import org.simulation.logic.map.builder.WorldMapBuilder;
import org.simulation.logic.utils.Vector2D;

public class Main {

    public static void main(String[] args) {

        WorldMap map = new WorldMapBuilder()
                .movementDistance(1)
                .battleRange(2)
                .build();

        ElementFactory elementFactory = new ElementFactory();

        Rock rock1 = (Rock) elementFactory.createElement(ElementEnum.ROCK, new Vector2D(0, 0));
        Paper paper1 = (Paper) elementFactory.createElement(ElementEnum.PAPER, new Vector2D(8, 0));
        Scissors scissors1 = (Scissors) elementFactory.createElement(ElementEnum.SCISSORS, new Vector2D(4, 9));

        map.getMapElementsManager().addElement(rock1);
        map.getMapElementsManager().addElement(paper1);
        map.getMapElementsManager().addElement(scissors1);

        map.startSimulation();

//        Application.launch(App.class, args);

    }
}