package org.example.elements;

import org.example.enums.ElementEnum;
import org.example.utils.Vector2D;

public class Paper extends Element {
    public Paper(Vector2D position){
        super(ElementEnum.PAPER, position);
    }

    @Override
    public boolean battle(Element opponent) {
        if (opponent instanceof Rock) {
            return true;
        } else if (opponent instanceof Scissors) {
            return false;
        } else if (opponent instanceof Paper) {
            throw new IllegalArgumentException("Invalid battle: Paper cannot battle against another Paper!");
        } else {
            throw new IllegalArgumentException("Invalid opponent!");
        }
    }

}
