package org.simulation.gui.screen;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.InputStream;

public class StartScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        InputStream imageStream = getClass().getClassLoader().getResourceAsStream("start-screen-img.png");
        assert imageStream != null;
        Image image = new Image(imageStream);

        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        Label titleLabel = new Label("ROCK PAPER SCISSORS");
        titleLabel.setFont(Font.font("Stencil", 38));

        Button startButton = new Button("Let's get started");
        startButton.setStyle("-fx-font-size: 14px; -fx-padding: 6px 12px");
        startButton.setOnAction(e -> {
            SettingsScreen settingsScreen = new SettingsScreen();
            settingsScreen.start(primaryStage);
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLabel, imageView, startButton);
        layout.setAlignment(Pos.CENTER);

        StackPane stackPane = new StackPane(layout);

        Scene scene = new Scene(stackPane, 800, 475);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
