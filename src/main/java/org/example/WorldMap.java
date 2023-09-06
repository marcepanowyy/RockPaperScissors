package org.example;

import org.example.elements.Element;
import org.example.utils.ElementDistancePair;
import org.example.utils.Vector2D;

import java.util.*;

public class WorldMap {

    private final int width;
    private final int height;
    private final ArrayList<Element> elements = new ArrayList<>();

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void addElement(Element element) throws IllegalArgumentException {

        Vector2D position = element.getPosition();

        if (isWithinBounds(position)) {

            elements.add(element);

        } else {
            throw new IllegalArgumentException("Element position is out of bounds");
        }

    }

    public void removeElement(Element element) {
        elements.remove(element);
    }

    public void draw() {

        String[][] mapArray = new String[height][width];

        for (int i = 0; i < height; i++) {
            Arrays.fill(mapArray[i], " ");
        }

        for (Element element : elements) {
            Vector2D position = element.getPosition();
            int x = (int) position.getX();
            int y = (int) position.getY();

            mapArray[y][x] = element.toString();
        }

        for (int i = height - 1; i >= 0; i--) {
            System.out.println();
            for (int j = 0; j < width; j++) {
                System.out.print("[" + mapArray[i][j] + "] ");
            }
        }

        System.out.println();
    }

    private boolean isWithinBounds(Vector2D position) {
        return position.getX() >= 0 && position.getX() < width && position.getY() >= 0 && position.getY() < height;
    }

    // find the nearest element different from itself

    public ElementDistancePair findCompanion(Element sourceElement) {

        Element closestCompanion = null;
        double shortestDistance = Double.MAX_VALUE;

        for (Element element : elements) {

            if (element.getSymbol() == sourceElement.getSymbol() || element.equals(sourceElement)) {
                continue;
            }

            double distance = sourceElement.calculateDistanceToCompanion(element);

            if (distance < shortestDistance) {
                shortestDistance = distance;
                closestCompanion = element;
            }
        }
        return new ElementDistancePair(closestCompanion, shortestDistance);
    }


}