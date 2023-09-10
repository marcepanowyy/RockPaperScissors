package org.example.map.manager;

import org.example.elements.Element;
import org.example.map.WorldMap;

// find the nearest element different from itself (e.g. Rock's opponent can be only Paper or Scissors)

public class FindOpponentsManager {

    private final WorldMap worldMap;

    public FindOpponentsManager(WorldMap worldMap) {

        this.worldMap = worldMap;

    }

    public void findOpponents() {

        worldMap.getElements().forEach(this::findOpponent);

    }

    private void findOpponent(Element sourceElement) {

        if (sourceElement.getOpponent() == null || worldMap.getRemovedRoundElements().containsKey(sourceElement.getOpponent())) {

            Element closestOpponent = null;
            double shortestDistance = Double.MAX_VALUE;

            for (Element element : worldMap.getElements()) {

                if (element.getSymbol() == sourceElement.getSymbol() || element.equals(sourceElement)) continue;

                double distance = sourceElement.calculateDistanceToOther(element);

                if (distance < shortestDistance) {

                    shortestDistance = distance;
                    closestOpponent = element;

                }
            }

            sourceElement.setOpponent(closestOpponent);

        }
    }
}
