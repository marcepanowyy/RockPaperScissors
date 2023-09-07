package org.example.elements;

import org.example.enums.ElementEnum;
import org.example.utils.Vector2D;

public class Rock extends Element {

    public Rock(Vector2D position){
        super(ElementEnum.ROCK, position);
    }

    @Override
    public boolean battle(Element opponent) {
        if (opponent instanceof Scissors) {
            return true;
        } else if (opponent instanceof Paper) {
            return false;
        } else if (opponent instanceof Rock) {
            throw new IllegalArgumentException("Invalid battle: Rock cannot battle against another Rock!");
        } else {
            throw new IllegalArgumentException("Invalid opponent!");
        }
    }
}
