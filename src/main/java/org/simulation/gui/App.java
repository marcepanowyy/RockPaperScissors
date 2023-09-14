package org.simulation.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import org.simulation.gui.screen.StartScreen;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Rock Paper Scissors Simulator");
        StartScreen startScreen = new StartScreen();
        startScreen.start(primaryStage);

    }
}
