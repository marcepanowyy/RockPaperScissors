package org.simulation.logic.elements;

import org.simulation.logic.enums.ElementEnum;
import org.simulation.logic.utils.Vector2D;

public class Scissors extends Element {

    public Scissors(Vector2D position){
        super(ElementEnum.SCISSORS, position);
    }

    @Override
    public boolean battle(Element opponent) {
        if (opponent instanceof Paper) {
            return true;
        } else if (opponent instanceof Rock) {
            return false;
        } else if (opponent instanceof Scissors) {
            throw new IllegalArgumentException("Invalid battle: Scissors cannot battle against another Scissors!");
        } else {
            throw new IllegalArgumentException("Invalid opponent!");
        }
    }
}
