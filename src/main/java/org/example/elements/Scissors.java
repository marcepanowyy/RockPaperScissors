package org.example.elements;

import org.example.enums.ElementEnum;

public class Scissors extends Element {

    public Scissors(int x, int y){
        super(ElementEnum.SCISSORS, x, y);
    }

    @Override
    public void battle(Element opponent) {
        if (opponent instanceof Paper) {
            System.out.println("Scissors wins against Paper!");
        } else if (opponent instanceof Rock) {
            System.out.println("Rock wins against Scissors!");
        } else if (opponent instanceof Scissors) {
            throw new IllegalArgumentException("Invalid battle: Scissors cannot battle against another Scissors!");
        } else {
            throw new IllegalArgumentException("Invalid opponent!");
        }
    }
}
