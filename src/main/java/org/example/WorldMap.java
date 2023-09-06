package org.example;

import org.example.elements.Element;
import org.example.utils.Vector2D;

import java.util.*;

public class WorldMap {

    private final int width;
    private final int height;
    private final ArrayList<Element> elements = new ArrayList<>();
    private final Map<Element, Vector2D> oldPositions = new HashMap<>();
//    private final double updateDistance = 0.5;
    private final double battleRange = 0.5;
    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void init(){
        findCompanions();
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

    public void findCompanions() {
        elements.forEach(this::findCompanion);
    }

    // find the nearest element different from itself
    public void findCompanion(Element sourceElement) {

        Element closestCompanion = null;
        double shortestDistance = Double.MAX_VALUE;

        for (Element element : elements) {

            if (element.getSymbol() == sourceElement.getSymbol() || element.equals(sourceElement)) {
                continue;
            }

            double distance = sourceElement.calculateDistanceToOther(element);

            if (distance < shortestDistance) {
                shortestDistance = distance;
                closestCompanion = element;
            }
        }
        sourceElement.setCompanion(closestCompanion);
    }

    private boolean isWithinBounds(Vector2D position) {
        return position.getX() >= 0 && position.getX() < width && position.getY() >= 0 && position.getY() < height;
    }

    public void updateElements(double updateDistance) {

        for (Element element : elements) {
            if (!oldPositions.containsKey(element)) {
                updateElement(element, updateDistance);
            }
        }

        oldPositions.clear();
    }

    public void updateElement(Element element, double updateDistance) {

        Element companion = element.getCompanion();

        if (companion != null) {

            Vector2D currPosition = element.getPosition();

            Vector2D companionPosition;

            // if old positions has key 'companion' that means
            // the companion moved, so we look for his old position

            if(oldPositions.containsKey(companion)){
                companionPosition = oldPositions.get(companion);
            }
            else {
                companionPosition = companion.getPosition();
            }

            double deltaX = companionPosition.getX() - currPosition.getX();
            double deltaY = companionPosition.getY() - currPosition.getY();

            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            if (distance > battleRange) {

                Vector2D oldPosition = element.getPosition();

                double ratio = updateDistance / distance;
                double newX = Math.round((currPosition.getX() + ratio * deltaX) * 100) / 100.0;
                double newY = Math.round((currPosition.getY() + ratio * deltaY) * 100) / 100.0;
                Vector2D updatedPosition = new Vector2D(newX, newY);
                element.setPosition(updatedPosition);
                oldPositions.put(element, oldPosition);

                // if the companion's companion is element, update its position with opposite vector
                if (companion.getCompanion() == element) {

                    Vector2D oldCompanionPosition = companion.getPosition();

                    double newCompanionX = Math.round((companionPosition.getX() - ratio * deltaX) * 100) / 100.0;
                    double newCompanionY = Math.round((companionPosition.getY() - ratio * deltaY) * 100) / 100.0;
                    Vector2D updatedCompanionPosition = new Vector2D(newCompanionX, newCompanionY);
                    companion.setPosition(updatedCompanionPosition);
                    oldPositions.put(companion, oldCompanionPosition);

                }

            } else {

                // add battle logic
                int x = 2;
            }
        }
    }


    // getters & setters

    public ArrayList<Element> getElements() {
        return elements;
    }


}