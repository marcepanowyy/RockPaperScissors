package org.example.elements;

import org.example.WorldMap;
import org.example.enums.ElementEnum;
import org.example.utils.Vector2D;

public class Element {

    private Vector2D position;
    private final WorldMap worldMap;
    private final ElementEnum symbol;
    private Element companion = null;

    public Element(WorldMap worldMap, ElementEnum symbol, int x, int y) {
        this.position = new Vector2D(x, y);
        this.symbol = symbol;
        this.worldMap = worldMap;
    }

    @Override
    public String toString() {
        return this.symbol.toString().substring(0, 1);
    }

    public Vector2D getPosition() {
        return position;
    }

    public double calculateDistanceToCompanion(Element mapElement) {

        Vector2D position1 = this.getPosition();
        Vector2D position2 = mapElement.getPosition();

        double deltaX = position2.getX() - position1.getX();
        double deltaY = position2.getY() - position1.getY();

        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }


}
