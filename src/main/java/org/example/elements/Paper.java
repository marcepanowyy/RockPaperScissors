package org.example.elements;

import org.example.enums.ElementEnum;

public class Paper extends Element {
    public Paper(int x, int y){
        super(ElementEnum.PAPER, x, y);
    }

    @Override
    public void battle(Element opponent) {
        if (opponent instanceof Rock) {
            System.out.println("Paper wins against Rock!");
        } else if (opponent instanceof Scissors) {
            System.out.println("Scissors wins against Paper!");
        } else if (opponent instanceof Paper) {
            throw new IllegalArgumentException("Invalid battle: Paper cannot battle against another Paper!");
        } else {
            throw new IllegalArgumentException("Invalid opponent!");
        }
    }

}
