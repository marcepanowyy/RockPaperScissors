package org.simulation.gui.screen;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

public class StartScreen extends Application {

    private Stage primaryStage;

    final int startImageSize = 300;

    private ImageView createImageView(){

        Image startImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("start-image.png")));
        ImageView startImageView = new ImageView(startImage);
        startImageView.setFitWidth(startImageSize);
        startImageView.setPreserveRatio(true);

        return startImageView;

    }

    private Label createTitleLabel(){

        Label titleLabel = new Label("ROCK PAPER SCISSORS");
        titleLabel.setFont(Font.font("Stencil", 38));

        return titleLabel;

    }

    private Button createActionButton(){

        Button startButton = new Button("Let's get started");

        startButton.setMinWidth(150);
        startButton.setPadding(new Insets(5));

        startButton.setOnAction(e -> {
            SettingsScreen settingsScreen = new SettingsScreen();
            settingsScreen.start(primaryStage);
        });

        return startButton;

    }

    private VBox createRootBox(){

        ImageView startImageView = createImageView();
        Label titleLabel = createTitleLabel();
        Button startButton = createActionButton();

        VBox root = new VBox(titleLabel, startImageView, startButton);

        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        return root;

    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;

        VBox root = createRootBox();

        Scene scene = new Scene(root, 800, 475);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
