package org.example;

import org.example.elements.AbstractMapElement;
import org.example.elements.IElement;
import org.example.utils.Vector2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldMap {

    private final int width;
    private final int height;
    private final Map<Vector2D, List<IElement>> map = new HashMap<>();

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addElement(IElement element) {

        if (element instanceof AbstractMapElement mapElement) {

            Vector2D position = mapElement.getPosition();

            if (isWithinBounds(position)) {

                List<IElement> elementsAtPosition = map.computeIfAbsent(position, k -> new ArrayList<>());
                elementsAtPosition.add(element);

            } else {
                throw new IllegalArgumentException("Element position is out of bounds");
            }
        }
    }

    public void removeElement(IElement element) {

        if (element instanceof AbstractMapElement mapElement) {

            Vector2D position = mapElement.getPosition();
            List<IElement> elementsAtPosition = map.get(position);

            if (elementsAtPosition != null) {
                elementsAtPosition.remove(element);

                if (elementsAtPosition.isEmpty()) {
                    map.remove(position);
                }
            }
        }
    }


    public void draw() {

        for (int i = height - 1; i >= 0; i--) {

            System.out.println();

            for (int j = 0; j < width; j++) {

                Vector2D position = new Vector2D(j, i);
                List<IElement> elementsAtPosition = map.get(position);
                String symbol;

                if (elementsAtPosition != null && !elementsAtPosition.isEmpty()) {

                    IElement firstElement = elementsAtPosition.get(0);
                    symbol = firstElement.toString();

                } else {
                    symbol = " ";
                }
                System.out.print("[" + symbol + "] ");
            }
        }

        System.out.println();

    }

    private boolean isWithinBounds(Vector2D position) {
        return position.getX() >= 0 && position.getX() < width && position.getY() >= 0 && position.getY() < height;
    }

}

