package org.simulation.gui.screen;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.simulation.logic.elements.Element;
import org.simulation.logic.elements.Paper;
import org.simulation.logic.elements.Rock;
import org.simulation.logic.elements.Scissors;
import org.simulation.logic.enums.ElementEnum;
import org.simulation.logic.factory.ElementFactory;
import org.simulation.logic.map.WorldMap;
import org.simulation.logic.map.builder.WorldMapBuilder;
import org.simulation.logic.utils.Vector2D;

import java.util.*;

public class SimulationScreen extends Application {

    ElementFactory elementFactory = new ElementFactory();

    WorldMap map = new WorldMapBuilder()
            .movementDistance(0.1)
            .width(10)
            .height(10)
            .battleRange(0.5)
            .build();

    private final int mapWidth = map.getWidth();
    private final int mapHeight = map.getHeight();

    private final int cellSize = 3;
    private final int imageSize = 25;

    private final GridPane mapGrid = createMapGrid();

    private final ArrayList<Node> oldNodes = new ArrayList<>();

    private final Map<ElementEnum, Image> imageBuffer = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {

        preloadImages();

        addRandomElementsToMap(5, 5, 5);

        map.init();

        VBox gridContainer = new VBox();
        gridContainer.getChildren().add(mapGrid);
        gridContainer.setAlignment(Pos.CENTER);

        HBox root = new HBox(gridContainer);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #dbdbdb");

        Scene scene = new Scene(root, 800, 475);

        primaryStage.setScene(scene);
        primaryStage.show();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                    updateGrid();
                    map.performRound();
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    private void updateGrid() {

        removeOldElements();
        addNewElements();

    }

    private void removeOldElements(){

        oldNodes.forEach(node -> {
            mapGrid.getChildren().remove(node);
        });
        oldNodes.clear();

    }

    private void addNewElements(){

        map.getElements().forEach(element -> {

            int x = (int) (element.getPosition().getX() * 10);
            int y = mapHeight * 10 - (int) (element.getPosition().getY() * 10);

            Pane cell = new Pane();
            cell.setPrefSize(cellSize, cellSize);

            ImageView imageView = getImageView(element);

            assert imageView != null;

            imageView.setFitWidth(imageSize);
            imageView.setFitHeight(imageSize);

            double xOffset = (cellSize - imageSize) / 2.0;
            double yOffset = (cellSize - imageSize) / 2.0;

            imageView.setLayoutX(xOffset);
            imageView.setLayoutY(yOffset);

            cell.getChildren().add(imageView);

            mapGrid.add(cell, x, y);
            oldNodes.add(cell);

        });

    }

    private GridPane createMapGrid(){

        GridPane mapGrid = new GridPane();

        for (int x = 0; x < mapWidth * 10; x++) {

            for (int y = 0; y < mapHeight * 10; y++) {

                Pane cell = new Pane();
                cell.setPrefSize(cellSize, cellSize);

                cell.setStyle("-fx-background-color: #9d9d9d;");

                mapGrid.add(cell, x, mapHeight * 10 - y);

            }
        }

        return mapGrid;

    }

    private ImageView getImageView(Element element) {

        Image image = imageBuffer.get(element.getSymbol());

        if (image != null) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(imageSize);
            imageView.setFitHeight(imageSize);

            return imageView;
        }

        return null;
    }

    private void addRandomElementsToMap(int rockCount, int paperCount, int scissorsCount) {

        Random rand = new Random();

        for (int i = 0; i < rockCount; i++) {
            double x = rand.nextDouble() * mapWidth;
            double y = rand.nextDouble() * mapHeight;
            Rock rock = (Rock) elementFactory.createElement(ElementEnum.ROCK, new Vector2D(x, y));
            map.getMapElementsManager().addElement(rock);
        }

        for (int i = 0; i < paperCount; i++) {
            double x = rand.nextDouble() * mapWidth;
            double y = rand.nextDouble() * mapHeight;
            Paper paper = (Paper) elementFactory.createElement(ElementEnum.PAPER, new Vector2D(x, y));
            map.getMapElementsManager().addElement(paper);
        }

        for (int i = 0; i < scissorsCount; i++) {
            double x = rand.nextDouble() * mapWidth;
            double y = rand.nextDouble() * mapHeight;
            Scissors scissors = (Scissors) elementFactory.createElement(ElementEnum.SCISSORS, new Vector2D(x, y));
            map.getMapElementsManager().addElement(scissors);
        }
    }

    private void preloadImages() {

        imageBuffer.put(ElementEnum.ROCK, new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("rock1.png"))));
        imageBuffer.put(ElementEnum.PAPER, new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("paper2.png"))));
        imageBuffer.put(ElementEnum.SCISSORS, new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("scissors1.png"))));

    }

    public static void main(String[] args) {
        launch(args);
    }

}