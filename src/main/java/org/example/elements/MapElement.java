package org.example.elements;

import org.example.Point;

public abstract class MapElement {

    private Point position;
    public MapElement(int x, int y) {
        this.position = new Point(x, y);
    }

    public Point getPosition() {
        return position;
    }

}
