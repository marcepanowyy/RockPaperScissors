package org.simulation.gui.screen;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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

import java.util.Objects;

public class SimulationScreen extends Application {

    ElementFactory elementFactory = new ElementFactory();

    Paper paper1 = (Paper) elementFactory.createElement(ElementEnum.PAPER, new Vector2D(0, 0));
    Paper paper2 = (Paper) elementFactory.createElement(ElementEnum.PAPER, new Vector2D(0, 9));
    Paper paper3 = (Paper) elementFactory.createElement(ElementEnum.PAPER, new Vector2D(9, 9));
    Paper paper4 = (Paper) elementFactory.createElement(ElementEnum.PAPER, new Vector2D(9, 0));

    WorldMap map = new WorldMapBuilder()
            .movementDistance(0.5)
            .width(10)
            .height(10)
            .battleRange(1)
            .build();

    private final int mapWidth = map.getWidth();
    private final int mapHeight = map.getHeight();

    private final int cellSize = 3;
    private final int imageSize = 40;

    @Override
    public void start(Stage primaryStage) {

        map.getMapElementsManager().addElement(paper1);
        map.getMapElementsManager().addElement(paper2);
        map.getMapElementsManager().addElement(paper3);
        map.getMapElementsManager().addElement(paper4);
        map.init();

        GridPane mapGrid = createMapGrid();

        VBox gridContainer = new VBox();
        gridContainer.getChildren().add(mapGrid);
        gridContainer.setAlignment(Pos.CENTER);

        HBox root = new HBox(gridContainer);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #dbdbdb");

        updateGrid(mapGrid);

        Scene scene = new Scene(root, 800, 475);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private GridPane createMapGrid(){

        GridPane mapGrid = new GridPane();

        for (int x = 0; x < mapWidth * 10; x++) {

            for (int y = 0; y < mapHeight * 10; y++) {

                Pane cell = new Pane();
                cell.setPrefSize(cellSize, cellSize);


                cell.setStyle("-fx-background-color: #9d9d9d;");

                if(x == 0 && y == 0 ) {
                    cell.setStyle("-fx-background-color: red;");
                }

                if(x == 2 && y == 2 ) {
                    cell.setStyle("-fx-background-color: yellow;");
                }


                mapGrid.add(cell, x, mapHeight * 10 - y);

            }
        }

        return mapGrid;

    }

    private void updateGrid(GridPane mapGrid) {

        for (Element element : map.getElements()) {

            int x = (int) (element.getPosition().getX() * 10);
            int y = mapHeight * 10 - (int) (element.getPosition().getY() * 10);

            Pane cell = new Pane();
            cell.setPrefSize(cellSize, cellSize);

            ImageView imageView = createImageView(element);

            assert imageView != null;

            imageView.setFitWidth(imageSize);
            imageView.setFitHeight(imageSize);

            double xOffset = (cellSize - imageSize) / 2.0;
            double yOffset = (cellSize - imageSize) / 2.0;

            imageView.setLayoutX(xOffset);
            imageView.setLayoutY(yOffset);

            cell.getChildren().add(imageView);

            mapGrid.add(cell, x, y);

        }
    }


    private ImageView createImageView(Element element) {

        if (element instanceof Rock) {
            return new ImageView(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("rock1.png"))));

        } else if (element instanceof Paper) {
            return new ImageView(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("paper1.png"))));

        } else if (element instanceof Scissors) {
            return new ImageView(new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("scissors1.png"))));

        }
        return null;

    }

    public static void main(String[] args) {
        launch(args);
    }
}
