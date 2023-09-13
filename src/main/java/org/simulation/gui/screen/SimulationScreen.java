package org.simulation.gui.screen;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
            .movementDistance(0.05)
            .width(15)
            .height(10)
            .battleRange(0.5)
            .build();

    private final int mapWidth = map.getWidth();

    private final int mapHeight = map.getHeight();

    private final int cellSize = 4;

    private final int imageSize = 35;

    private GridPane mapGrid = createMapGrid();

    private final ArrayList<Node> oldNodes = new ArrayList<>();

    private final Map<ElementEnum, Image> imageBuffer = new HashMap<>();

    private Timeline timeline;

    @Override
    public void start(Stage primaryStage) {

        preloadImages();

        int scissorsCount = 10;
        int rockCount = 10;
        int paperCount = 10;

        addRandomElementsToMap(rockCount, paperCount, scissorsCount);

        map.init();

        VBox gridContainer = new VBox();
        gridContainer.getChildren().add(mapGrid);
        gridContainer.setAlignment(Pos.CENTER);

        HBox root = new HBox(gridContainer);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: lightgray");

        Scene scene = new Scene(root, 800, 475);

        primaryStage.setScene(scene);
        primaryStage.show();

        Thread simulationThread = new Thread(this::startSimulation);

        // set the thread as a daemon to terminate it along with the main thread
        simulationThread.setDaemon(true);
        simulationThread.start();

    }

    private void startSimulation() {

        timeline = new Timeline(

                new KeyFrame(Duration.seconds(0.3), event -> {

                    updateGrid();
                    map.performRound();

                    if (!map.isRunning()) {

                        Platform.runLater(this::showSimulationCompleteAlert);
                        timeline.stop();

                    }
                })
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateGrid() {

        removeOldElements();
        addNewElements();

    }

    private void removeOldElements() {

        oldNodes.forEach(node -> mapGrid.getChildren().remove(node));
        oldNodes.clear();

    }

    private void addNewElements() {

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

    private GridPane createMapGrid() {

        GridPane mapGrid = new GridPane();

        for (int x = 0; x < mapWidth * 10; x++) {
            for (int y = 0; y < mapHeight * 10; y++) {

                Pane cell = new Pane();
                cell.setPrefSize(cellSize, cellSize);
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

        imageBuffer.put(ElementEnum.ROCK, new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("rock.png"))));
        imageBuffer.put(ElementEnum.PAPER, new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("paper.png"))));
        imageBuffer.put(ElementEnum.SCISSORS, new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("scissors.png"))));

    }

    private void showSimulationCompleteAlert() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Simulation has ended. Winner element: " + map.getElements().get(0).getSymbol());
            alert.showAndWait();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
