package org.example;
import org.example.elements.Element;
import org.example.factory.ElementFactory;
import org.example.utils.UniquePair;
import org.example.utils.Vector2D;
import java.util.*;

public class WorldMap {
    private final int width;
    private final int height;
    private final ArrayList<Element> elements = new ArrayList<>();
    private final Map<Element, Vector2D> oldPositions = new HashMap<>();
    private final Set<UniquePair<Element, Element>> uniqueBattlePairs = new HashSet<>();
    private final double movementDistance; // movement distance should be smaller than battleRange
    private final double battleRange;
    private final ElementFactory elementFactory = new ElementFactory();
    private final List<Element> removedRoundElements = new ArrayList<>();
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

        checkForBattles(); // check for battles (add them to special map)
        handleBattles();   // handle battles
        findOpponents();   // update opponents after battles (new element must have opponent)
        updateElements();  // move elements

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

        // 2nd condition is when element is removed and for this element, another element is opponent

        if (sourceElement.getOpponent() == null || removedRoundElements.contains(sourceElement.getOpponent())) {

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

    // checking for battles

    public void checkForBattles(){

        for (Element element : elements) {

            Element opponent = element.getOpponent();

            if (opponent != null) {

                if(element.calculateDistanceToOther(opponent) <= battleRange && element == opponent.getOpponent()) {

                    UniquePair<Element, Element> battlePair = new UniquePair<>(element, opponent);
                    uniqueBattlePairs.add(battlePair);

                }
            }
        }
    }

    // end checking for battles

    // handling battles

    private void handleBattles(){

        for (UniquePair battleElements : uniqueBattlePairs) {
            handleBattle(battleElements.getFirstBattleElement(), battleElements.getSecondBattleElement());
        }

        uniqueBattlePairs.clear();

    }
    private void handleBattle(Element element, Element opponent){

        boolean battleWon = element.battle(opponent);

        Element newElement;

        if (battleWon) {
            newElement = elementFactory.createElement(element.getSymbol(), opponent.getPosition());
            element.setOpponent(null);
            removeElement(opponent);
            removedRoundElements.add(opponent);
        }
        else  {
            newElement = elementFactory.createElement(opponent.getSymbol(), element.getPosition());
            opponent.setOpponent(null);
            removeElement(element);
            removedRoundElements.add(element);
        }

        addElement(newElement);

    }

    // end handling battles

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