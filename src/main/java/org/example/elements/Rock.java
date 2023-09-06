package org.example.elements;

import org.example.enums.ElementEnum;

public class Rock extends Element {

    public Rock(int x, int y){
        super(ElementEnum.ROCK, x, y);
    }

    @Override
    public void battle(Element opponent) {
        if (opponent instanceof Scissors) {
            System.out.println("Rock wins against Scissors!");
        } else if (opponent instanceof Paper) {
            System.out.println("Paper wins against Rock!");
        } else if (opponent instanceof Rock) {
            throw new IllegalArgumentException("Invalid battle: Rock cannot battle against another Rock!");
        } else {
            throw new IllegalArgumentException("Invalid opponent!");
        }
    }
}
