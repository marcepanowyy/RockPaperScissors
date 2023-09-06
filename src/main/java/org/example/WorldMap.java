package org.example;
import org.example.elements.Element;
import org.example.utils.Vector2D;
import java.util.*;

public class WorldMap {
    private final int width;
    private final int height;
    private final ArrayList<Element> elements = new ArrayList<>();
    private final Map<Element, Vector2D> oldPositions = new HashMap<>();
    private final double movementDistance; // update distance should be smaller than battleRange
    private final double battleRange;
    public WorldMap(int width, int height, double movementDistance, double battleRange) {
        this.width = width;
        this.height = height;
        this.movementDistance = movementDistance;
        this.battleRange = battleRange;
    }
    public void init(){
        findOpponents();
    }

    public void performRound(){
        checkForBattles();
        findOpponents(); // find opponents again, after the battle, the opponents of all elements can change.
        updateElements();
    }

    // adding/removing to map
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

    // end adding/removing to map

    // finding opponents

    public void findOpponents() {
        elements.forEach(this::findOpponent);
    }
    public void findOpponent(Element sourceElement) {

        if (sourceElement.getOpponent() == null) {

            Element closestOpponent = null;
            double shortestDistance = Double.MAX_VALUE;

            for (Element element : elements) {

                if (element.getSymbol() == sourceElement.getSymbol() || element.equals(sourceElement)) {
                    continue;
                }

                double distance = sourceElement.calculateDistanceToOther(element);

                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    closestOpponent = element;
                }
            }
            sourceElement.setOpponent(closestOpponent);
        }
    }

    // end finding opponents

    // find the nearest element different from itself

    private boolean isWithinBounds(Vector2D position) {
        return position.getX() >= 0 && position.getX() < width && position.getY() >= 0 && position.getY() < height;
    }

    // updating elements

    public void updateElements() {

        for (Element element : elements) {
            if (!oldPositions.containsKey(element)) {
                updateElement(element);
            }
        }

        // after updating all the positions we set empty map
        oldPositions.clear();
    }

    public void updateElement(Element element) {

        Element opponent = element.getOpponent();

        if (opponent != null) {

            Vector2D currPosition = element.getPosition();

            Vector2D opponentPosition;

            // if old positions has key 'opponent' that means
            // the opponent moved, so we look for his old position

            if(oldPositions.containsKey(opponent)){
                opponentPosition = oldPositions.get(opponent);
            }
            else {
                opponentPosition = opponent.getPosition();
            }

            double deltaX = opponentPosition.getX() - currPosition.getX();
            double deltaY = opponentPosition.getY() - currPosition.getY();

            double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

            Vector2D oldPosition = element.getPosition();

            double ratio = movementDistance / distance;
            double newX = Math.round((currPosition.getX() + ratio * deltaX) * 100) / 100.0;
            double newY = Math.round((currPosition.getY() + ratio * deltaY) * 100) / 100.0;
            Vector2D updatedPosition = new Vector2D(newX, newY);
            element.setPosition(updatedPosition);
            oldPositions.put(element, oldPosition);

            // if the opponent's opponent is element, update its position with opposite vector
            if (opponent.getOpponent() == element) {

                Vector2D oldOpponentPosition = opponent.getPosition();

                double newOpponentX = Math.round((opponentPosition.getX() - ratio * deltaX) * 100) / 100.0;
                double newOpponentY = Math.round((opponentPosition.getY() - ratio * deltaY) * 100) / 100.0;
                Vector2D updatedOpponentPosition = new Vector2D(newOpponentX, newOpponentY);
                opponent.setPosition(updatedOpponentPosition);
                oldPositions.put(opponent, oldOpponentPosition);

            }
        }
    }

    // end updating elements

    // checking for battles and handling them if they exist

    public void checkForBattles(){

        for (Element element : elements) {

            Element opponent = element.getOpponent();

            if (opponent != null) {

                if(element.calculateDistanceToOther(opponent) <= battleRange && element == opponent.getOpponent()) {

                    handleBattle(element, opponent);

                    // handle pair not single element

                    // todo - remove element from map, create new one after removal

                    // remove their opponents and completely defeated elements, it may happen that a removed element was an opponent for another element, so handle this situation

                }
            }
        }
    }

    private void handleBattle(Element element, Element opponent){
        // two scenarios
        element.battle(opponent);
        opponent.battle(element);
    }

    // end checking for battles

    // getters & setters

    public ArrayList<Element> getElements() {
        return elements;
    }

    public Map<Element, Vector2D> getOldPositions() {
        return oldPositions;
    }

    // end getters & setters

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


}