package org.simulation.gui.screen;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


public class SettingsScreen extends Application {

    private final int imageSize = 80;

    private static final String[] settingNames = {

            "measure",
            "radar",
            "run",
            "rock",
            "paper",
            "scissors",
    };


    private final Map<String, Map<String, Object>> settings = new SettingsLoader(imageSize).loadSettingProperties();

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

        return new VBox(titleLabelBox, settingsBox);

    }

    private HBox createTitleLabelBox(){

        Label label = new Label("GLOWNY OPIS");
        return new HBox(label);

    }

    private HBox createSettingsBox(){

        VBox column0 = createSettingsColumnBox(0);
        VBox column1 = createSettingsColumnBox(1);
        return new HBox(column0, column1);

    }

    private VBox createSettingsColumnBox(int column){

        int startIndex = (column == 0) ? 0 : 3;
        int endIndex = (column == 0) ? 2 : 5;

        VBox settingColumn = new VBox();

        for (int i = startIndex; i <= endIndex; i++) {

            String settingName = settingNames[i];
            HBox settingBox = createSettingBox(settingName);
            settingColumn.getChildren().add(settingBox);
        }

        return settingColumn;

    }

    private HBox createSettingBox(String settingName){

        VBox descriptionAndInputBox = createBoxWithDescriptionAndInput(settingName);
        HBox imageBox = createBoxWithImage(settingName);
        return new HBox(imageBox, descriptionAndInputBox);

    }

    private VBox createBoxWithDescriptionAndInput(String settingName){

        HBox imageBox = createBoxWithDescription(settingName);
        HBox inputBox = createBoxWithInput(settingName);

        return new VBox(imageBox, inputBox);

    }

    private HBox createBoxWithImage(String settingName){

        ImageView imageView = (ImageView) settings.get(settingName).get("image");
        return new HBox(imageView);

    }

    private HBox createBoxWithDescription(String settingName){

        String description = (String) settings.get(settingName).get("description");
        Label label = new Label(description);
        return new HBox(label);

    }

    // change to input

    private HBox createBoxWithInput(String settingName){

        String description = (String) settings.get(settingName).get("input");
        Label label = new Label(description);
        return new HBox(label);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
