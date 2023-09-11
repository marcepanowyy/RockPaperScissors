package org.example.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Start Screen");
        StartScreen startScreen = new StartScreen();
        startScreen.start(primaryStage);

    }
}
