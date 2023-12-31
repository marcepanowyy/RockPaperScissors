package org.simulation.logic.element;

import org.simulation.logic.enums.ElementEnum;
import org.simulation.logic.utils.Vector2D;

public abstract class Element {

    private Vector2D position;

    private final ElementEnum symbol;

    private Element opponent;

    public Element(ElementEnum symbol, Vector2D position) {
        this.position = position;
        this.symbol = symbol;
    }

    public double calculateDistanceToOther(Element opponent) {

        Vector2D position1 = this.getPosition();
        Vector2D position2 = opponent.getPosition();

        double deltaX = position2.getX() - position1.getX();
        double deltaY = position2.getY() - position1.getY();

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public abstract boolean battle(Element opponent);

    @Override
    public String toString() {
        return this.symbol.toString().substring(0, 1);
    }

    // getters & setters

    public Vector2D getPosition() {
        return position;
    }

    public ElementEnum getSymbol(){
        return this.symbol;
    }

    public Element getOpponent() {
        return opponent;
    }

    public void setOpponent(Element opponent) {
        this.opponent = opponent;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    // end getters & setters

}
