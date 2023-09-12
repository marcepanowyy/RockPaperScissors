package org.simulation.logic.map.manager;

import org.simulation.logic.elements.Element;
import org.simulation.logic.map.WorldMap;
import org.simulation.logic.utils.Vector2D;

public class MapElementsManager {

    private final WorldMap worldMap;

    public MapElementsManager(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void addElement(Element element) throws IllegalArgumentException {

        // Check if the element is within bounds before adding it

        if (isWithinBounds(element.getPosition())) worldMap.getElements().add(element);
        else throw new IllegalArgumentException("Element position is out of bounds");
    }

    public void removeElement(Element element) {
        worldMap.getElements().remove(element);
    }

    public Element getElementAtPosition(Vector2D position) {

        return worldMap.getElements()
                .stream()
                .filter(element -> element.getPosition().equals(position))
                .findFirst()
                .orElse(null);

    }

    public boolean isWithinBounds(Vector2D position) {

        return position.getX() >= 0 && position.getX() < worldMap.getWidth() && position.getY() >= 0 && position.getY() < worldMap.getHeight();

    }

}
