package org.example.elements;

import org.example.utils.Vector2D;

public abstract class AbstractMapElement {

    private Vector2D position;
    public AbstractMapElement(int x, int y) {
        this.position = new Vector2D(x, y);
    }

    public Vector2D getPosition() {
        return position;
    }

    public boolean isAt(Vector2D position){
        return this.position.equals(position);
    }

}
