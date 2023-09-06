package org.example.factory;

import org.example.elements.Element;
import org.example.elements.Paper;
import org.example.elements.Rock;
import org.example.elements.Scissors;
import org.example.enums.ElementEnum;

public class ElementFactory {

    public Element createElement(ElementEnum type, int x, int y){

        if(type == ElementEnum.ROCK){
            return new Rock(x, y);
        } else if (type == ElementEnum.PAPER) {
            return new Paper(x, y);
        } else if (type == ElementEnum.SCISSORS) {
            return new Scissors(x, y);
        } else throw new IllegalArgumentException("Invalid element type.");

    }

}
