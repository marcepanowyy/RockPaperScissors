package org.example.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsScreen extends Application {

    @Override
    public void start(Stage primaryStage) {

        Button backButton = new Button("Go back");
        backButton.setOnAction(e -> {
            StartScreen startScreen = new StartScreen();
            startScreen.start(primaryStage);
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(backButton);

        Scene scene = new Scene(layout, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
