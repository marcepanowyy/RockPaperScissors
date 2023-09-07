package org.example.factory;

import org.example.elements.Element;
import org.example.elements.Paper;
import org.example.elements.Rock;
import org.example.elements.Scissors;
import org.example.enums.ElementEnum;
import org.example.utils.Vector2D;

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
