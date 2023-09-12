package org.simulation.gui.screen;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.InputStream;

public class SettingsScreen extends Application {

    private ImageView selectedImageView = null;
    private final int imageSize = 80;
    private final int cellSize = 30;
    private boolean isCopying = false;

    @Override
    public void start(Stage primaryStage) {

        InputStream img1Stream = getClass().getClassLoader().getResourceAsStream("rock1.png");
        InputStream img2Stream = getClass().getClassLoader().getResourceAsStream("paper1.png");
        InputStream img3Stream = getClass().getClassLoader().getResourceAsStream("scissors1.png");

        assert img1Stream != null;
        assert img2Stream != null;
        assert img3Stream != null;

        Image img1 = new Image(img1Stream);
        Image img2 = new Image(img2Stream);
        Image img3 = new Image(img3Stream);

        GridPane mapGrid = new GridPane();
        mapGrid.setHgap(2);
        mapGrid.setVgap(2);
        mapGrid.setPadding(new Insets(10));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Pane cell = new Pane();
                cell.setMinSize(cellSize, cellSize);
                cell.setStyle("-fx-background-color: lightgray;");
                mapGrid.add(cell, j, i);
            }
        }

        VBox gridContainer = new VBox();
        gridContainer.getChildren().add(mapGrid);
        gridContainer.setAlignment(Pos.CENTER);
        gridContainer.setSpacing(20);
        gridContainer.setFillWidth(true);

        VBox imageContainer = new VBox();
        imageContainer.setAlignment(Pos.CENTER);
        imageContainer.setSpacing(20);

        ImageView imageView1 = new ImageView(img1);
        ImageView imageView2 = new ImageView(img2);
        ImageView imageView3 = new ImageView(img3);

        imageView1.setFitWidth(imageSize);
        imageView1.setPreserveRatio(true);
        imageView2.setFitWidth(imageSize);
        imageView2.setPreserveRatio(true);
        imageView3.setFitWidth(imageSize);
        imageView3.setPreserveRatio(true);

        imageContainer.getChildren().addAll(imageView1, imageView2, imageView3);

        HBox root = new HBox(gridContainer, imageContainer);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(50);

        Scene scene = new Scene(root, 800, 475);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
