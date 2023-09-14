package org.simulation.gui.screen;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.simulation.logic.element.Element;
import org.simulation.logic.enuM.ElementEnum;
import org.simulation.logic.map.WorldMap;

import java.util.*;

public class SimulationScreen extends Application {

    private Stage primaryStage;

    final double keyFrameTimestamp = 0.25;

    private final int cellSize = 4;

    private final int imageSize = 35;

    private final WorldMap map;

    private GridPane mapGrid;

    private Timeline timeline;

    private final ArrayList<Node> oldNodes = new ArrayList<>();

    private final Map<ElementEnum, Image> imageBuffer = new HashMap<>();

    public SimulationScreen(WorldMap worldMap) {

        this.map = worldMap;
        createMapGrid();
        preloadImages();

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
            int y = map.getHeight() * 10 - (int) (element.getPosition().getY() * 10);

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

    private void createMapGrid() {

        mapGrid = new GridPane();

        for (int x = 0; x < map.getWidth() * 10; x++) {
            for (int y = 0; y < map.getHeight() * 10; y++) {

                Pane cell = new Pane();
                cell.setPrefSize(cellSize, cellSize);
                mapGrid.add(cell, x, map.getHeight() * 10 - y);

            }
        }

        mapGrid.setStyle("-fx-border-color: black");

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

    private void preloadImages() {

        imageBuffer.put(ElementEnum.ROCK, new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("rock.png"))));
        imageBuffer.put(ElementEnum.PAPER, new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("paper.png"))));
        imageBuffer.put(ElementEnum.SCISSORS, new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("scissors.png"))));

    }

    private void showSimulationCompleteAlert() {

        Platform.runLater(() -> {

            Alert alert = new Alert(Alert.AlertType.NONE);

            Label label = new Label("Simulation has ended. Winner element: " + map.getElements().get(0).getSymbol());


            label.setStyle("-fx-padding: 5 0 0 -10");


            VBox vbox = new VBox(label);
            vbox.setAlignment(Pos.CENTER);

            ButtonType returnToStartScreenButton = new ButtonType("Return to Start Screen", ButtonBar.ButtonData.OK_DONE);
            alert.getButtonTypes().add(returnToStartScreenButton);

            alert.getDialogPane().setContent(vbox);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == returnToStartScreenButton) {
                StartScreen startScreen = new StartScreen();
                startScreen.start(primaryStage);

            }
        });
    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;

        VBox gridBox = new VBox();
        gridBox.getChildren().add(mapGrid);
        gridBox.setAlignment(Pos.CENTER);

        HBox root = new HBox(gridBox);
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

                new KeyFrame(Duration.seconds(keyFrameTimestamp), event -> {

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
}
