package org.simulation.logic.element.factory;

import org.simulation.logic.element.Element;
import org.simulation.logic.element.Paper;
import org.simulation.logic.element.Rock;
import org.simulation.logic.element.Scissors;
import org.simulation.logic.enuM.ElementEnum;
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
