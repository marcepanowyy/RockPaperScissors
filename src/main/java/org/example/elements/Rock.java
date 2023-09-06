package org.example.elements;

import org.example.enums.ElementEnum;

public class Rock extends Element {

    public Rock(int x, int y){
        super(ElementEnum.ROCK, x, y);
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
