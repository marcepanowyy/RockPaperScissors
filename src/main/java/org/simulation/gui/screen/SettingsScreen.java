package org.simulation.gui.screen;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.simulation.gui.helper.Setting;
import org.simulation.gui.helper.SettingsLoader;

import java.util.Map;

public class SettingsScreen extends Application {

    private final int imageSize = 80;

    private static final String[] settingNames = {

            "size",
            "range",
            "speed",
            "rock",
            "paper",
            "scissors",

    };


    private final Map<String, Setting> settings = new SettingsLoader(imageSize).getSettingProperties();

    @Override
    public void start(Stage primaryStage) {


        VBox root = createRootBox();
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 800, 475);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private VBox createRootBox(){

        HBox titleLabelBox = createTitleLabelBox();
        HBox settingsBox = createSettingsBox();
        HBox proceedButtonBox = createStartSimulationBox();

        VBox root = new VBox(titleLabelBox, settingsBox, proceedButtonBox);

        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        root.setPadding(new Insets(15));

        return root;

    }

    private HBox createTitleLabelBox(){

        Label label = new Label("Establish Your Ideal Map Preferences!");
        label.setFont(Font.font("Comic Sans MS", 24));

        HBox titleLabelBox = new HBox(label);

        titleLabelBox.setAlignment(Pos.CENTER);

        return titleLabelBox;

    }

    private HBox createSettingsBox(){

        VBox column0 = createSettingsColumnBox(0);
        VBox column1 = createSettingsColumnBox(1);

        HBox settingsBox = new HBox(column0, column1);

        settingsBox.setAlignment(Pos.CENTER);

        return settingsBox;

    }

    private VBox createSettingsColumnBox(int column){

        int startIndex = (column == 0) ? 0 : 3;
        int endIndex = (column == 0) ? 2 : 5;

        VBox settingColumn = new VBox();

        for (int i = startIndex; i <= endIndex; i++) {

            String settingName = settingNames[i];
            HBox settingBox = createSettingBox(settings.get(settingName));
            settingColumn.getChildren().add(settingBox);
        }

        return settingColumn;

    }

    private HBox createSettingBox(Setting setting) {

        VBox titleAndDescriptionAndInput = createBoxWithTitleAndDescriptionAndInput(setting.getTitle(), setting.getDescription(), setting.getInput());
        HBox imageBox = createBoxWithImage(setting.getImageView());
        HBox settingBox = new HBox(imageBox, titleAndDescriptionAndInput);

        settingBox.setMaxWidth(400);
        settingBox.setAlignment(Pos.CENTER);

        return settingBox;

    }

    private VBox createBoxWithTitleAndDescriptionAndInput(String title, String description, String input) {

        HBox titleBox = createBoxWithTitle(title);
        HBox descriptionBox = createBoxWithDescription(description);
        HBox inputBox = createBoxWithInput(input);

        VBox titleAndDescriptionAndInput = new VBox(titleBox, descriptionBox, inputBox);

        titleAndDescriptionAndInput.setAlignment(Pos.CENTER);
        return titleAndDescriptionAndInput;

    }

    private HBox createBoxWithImage(ImageView imageView) {

        HBox imageBox = new HBox(imageView);

        imageBox.setAlignment(Pos.CENTER);
        imageBox.setPadding(new Insets(15));

        return imageBox;
    }

    private HBox createBoxWithTitle(String title) {

        Label label = new Label(title);

        label.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-size: 14;");

        HBox descriptionBox = new HBox(label);

        descriptionBox.setAlignment(Pos.CENTER);

        return descriptionBox;


    }

    private HBox createBoxWithDescription(String description) {

        Label label = new Label(description);

        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);

        HBox descriptionBox = new HBox(label);

        descriptionBox.setAlignment(Pos.CENTER);
        descriptionBox.setPadding(new Insets(7, 0, 10, 0));

        return descriptionBox;

    }

    private HBox createBoxWithInput(String input) {

        TextField textField = new TextField();
        textField.setPromptText(input);
        textField.setText(input);

        textField.setAlignment(Pos.CENTER);

        HBox inputBox = new HBox(textField);

        inputBox.setAlignment(Pos.CENTER);

        return inputBox;
    }

    private HBox createStartSimulationBox(){

        Button startButton = new Button("Start Simulation!");
        startButton.setMinWidth(150);
        startButton.setPadding(new Insets(5));

        HBox startButtonBox = new HBox(startButton);

        startButtonBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(startButtonBox, Priority.ALWAYS);

        return new HBox(startButtonBox);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
