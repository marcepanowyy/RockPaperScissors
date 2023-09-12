package org.simulation.logic.factory;

import org.simulation.logic.elements.Element;
import org.simulation.logic.elements.Paper;
import org.simulation.logic.elements.Rock;
import org.simulation.logic.elements.Scissors;
import org.simulation.logic.enums.ElementEnum;
import org.simulation.logic.utils.Vector2D;

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
