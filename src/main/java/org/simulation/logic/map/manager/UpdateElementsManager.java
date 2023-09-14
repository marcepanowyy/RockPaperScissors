package org.simulation.logic.map.manager;

import org.simulation.logic.element.Element;
import org.simulation.logic.map.WorldMap;
import org.simulation.logic.utils.Vector2D;


public class UpdateElementsManager {

    private final WorldMap worldMap;

    public UpdateElementsManager(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void updateElements() {

        worldMap.getElements().forEach(this::updateElement);
        int countMovedElements = worldMap.getOldPositions().size();

        if (countMovedElements == 0) worldMap.setRunning(false);

        worldMap.getOldPositions().clear();

    }

    private void updateElement(Element element) {

        Element opponent = element.getOpponent();

        if (opponent != null) { // will never happen, but in case...

            Vector2D currPosition = element.getPosition();
            Vector2D opponentPosition;

            // if old positions has key 'opponent' that means
            // the opponent has moved, so we look for his old position

            if (worldMap.getOldPositions().containsKey(opponent)) opponentPosition = worldMap.getOldPositions().get(opponent);
            else opponentPosition = opponent.getPosition();

            double deltaX = opponentPosition.getX() - currPosition.getX();
            double deltaY = opponentPosition.getY() - currPosition.getY();
            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            Vector2D oldPosition = element.getPosition();

            if (distance != 0) {

                double ratio = worldMap.getMovementDistance() / distance;
                double newX = Math.round((currPosition.getX() + ratio * deltaX) * 100) / 100.0;
                double newY = Math.round((currPosition.getY() + ratio * deltaY) * 100) / 100.0;

                Vector2D updatedPosition = new Vector2D(newX, newY);
                element.setPosition(updatedPosition);

            }

            // else, there is no need to move element, so we do nothing

            worldMap.getOldPositions().put(element, oldPosition);

        }
    }

}
