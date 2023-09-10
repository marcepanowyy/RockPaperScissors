package org.example.map;
import org.example.elements.Element;
import org.example.map.manager.*;
import org.example.utils.UniquePair;
import org.example.utils.Vector2D;
import java.util.*;

public class WorldMap {

    private final int width;

    private final int height;

    private final double battleRange;

    private final double movementDistance; // movement distance should be smaller than battleRange

    private boolean running = true;

    private final ArrayList<Element> elements = new ArrayList<>();

    private final Map<Element, Vector2D> oldPositions = new HashMap<>();

    private final Map<Element, Vector2D> removedRoundElements = new HashMap<>();

    private final Set<UniquePair> uniqueBattlePairs = new HashSet<>();

    // Managers

    private final CheckForBattlesManager checkForBattlesManager = new CheckForBattlesManager(this);

    private final FindOpponentsManager findOpponentsManager = new FindOpponentsManager(this);

    private final HandleBattlesManager handleBattlesManager = new HandleBattlesManager(this);

    private final MapElementsManager mapElementsManager = new MapElementsManager(this);

    private final UpdateElementsManager updateElementsManager = new UpdateElementsManager(this);

    // end Managers

    public WorldMap(int width, int height, double movementDistance, double battleRange) {
        this.width = width;
        this.height = height;
        this.movementDistance = movementDistance;
        this.battleRange = battleRange;
    }

    public void startSimulation(){

        init();

        while (running){
            performRound();
        }

        System.out.println("Simulation has ended.");

    }

    private void init(){
        findOpponentsManager.findOpponents();
    }

    private void performRound(){

        checkForBattlesManager.checkForBattles();  // check for battles (add them to special map)
        handleBattlesManager.handleBattles();      // handle battles
        findOpponentsManager.findOpponents();      // update opponents after battles (new element must have opponent)
        updateElementsManager.updateElements();    // move elements
        draw();

    }

    // getters & setters

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getMovementDistance() {
        return movementDistance;
    }

    public double getBattleRange() {
        return battleRange;
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public Map<Element, Vector2D> getOldPositions() {
        return oldPositions;
    }

    public Map<Element, Vector2D> getRemovedRoundElements() {
        return removedRoundElements;
    }

    public Set<UniquePair> getUniqueBattlePairs() {
        return uniqueBattlePairs;
    }

    public MapElementsManager getMapElementsManager() {
        return mapElementsManager;
    }

    public void setRunning(boolean running) {
        this.running = running;
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


        for(Element element: elements){

            System.out.print(element.getSymbol() + " " + element.getPosition() + ", ");

        }


        System.out.println();

    }

    private boolean isWithinBounds(Vector2D position) {

        return position.getX() >= 0 && position.getX() < width && position.getY() >= 0 && position.getY() < height;

    }

}