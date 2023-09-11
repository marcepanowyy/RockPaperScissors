package org.example.simulation.factory;

import org.example.simulation.elements.Element;
import org.example.simulation.elements.Paper;
import org.example.simulation.elements.Rock;
import org.example.simulation.elements.Scissors;
import org.example.simulation.enums.ElementEnum;
import org.example.simulation.utils.Vector2D;

public class ElementFactory {

    public Element createElement(ElementEnum type, Vector2D position){

        if(type == ElementEnum.ROCK){
            return new Rock(position);
        } else if (type == ElementEnum.PAPER) {
            return new Paper(position);
        } else if (type == ElementEnum.SCISSORS) {
            return new Scissors(position);
        } else throw new IllegalArgumentException("Invalid element type.");

    }

}
