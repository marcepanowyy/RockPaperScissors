package org.simulation.gui.screen;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.simulation.gui.helper.Setting;
import org.simulation.gui.helper.SettingsLoader;
import org.simulation.logic.elements.Element;
import org.simulation.logic.enums.ElementEnum;
import org.simulation.logic.factory.ElementFactory;
import org.simulation.logic.map.WorldMap;
import org.simulation.logic.map.builder.WorldMapBuilder;
import org.simulation.logic.utils.Vector2D;

import java.util.*;

public class SettingsScreen extends Application {

    private Stage primaryStage;

    private final int imageSize = 80;

    private final Random rand = new Random();

    private final Map<String, TextField> inputFields = new HashMap<>();

    private final Map<String, Setting> settings = new SettingsLoader(imageSize).getSettingProperties();

    private final ElementFactory elementFactory = new ElementFactory();

    private static final String[] settingNames = {

            "size",
            "range",
            "speed",
            "rock",
            "paper",
            "scissors",

    };


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

        VBox titleAndDescriptionAndInput = createBoxWithTitleAndDescriptionAndInput(setting);
        HBox imageBox = createBoxWithImage(setting.getImageView());
        HBox settingBox = new HBox(imageBox, titleAndDescriptionAndInput);

        settingBox.setMaxWidth(400);
        settingBox.setAlignment(Pos.CENTER);

        return settingBox;

    }

    private VBox createBoxWithTitleAndDescriptionAndInput(Setting setting) {

        HBox titleBox = createBoxWithTitle(setting.getTitle());
        HBox descriptionBox = createBoxWithDescription(setting.getDescription());
        HBox inputBox = createBoxWithInput(setting.getName(), setting.getInput());

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

    private HBox createBoxWithInput(String name, String input) {

        TextField textField = new TextField();
        textField.setPromptText(input);
        textField.setText(input);

        textField.setAlignment(Pos.CENTER);

        HBox inputBox = new HBox(textField);

        inputBox.setAlignment(Pos.CENTER);

        inputFields.put(name, textField);

        return inputBox;
    }

    private HBox createStartSimulationBox(){

        Button startButton = new Button("Start Simulation!");
        startButton.setMinWidth(150);
        startButton.setPadding(new Insets(5));

        startButton.setOnAction(event -> {

            List<String> invalidParams = getInvalidParams();

            if (invalidParams.isEmpty()) {
                openSimulationScreen();
            } else {
                showAlertWithInvalidParams(invalidParams);
            }

        });

        HBox startButtonBox = new HBox(startButton);

        startButtonBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(startButtonBox, Priority.ALWAYS);

        return new HBox(startButtonBox);

    }

    private void openSimulationScreen() {

        try {

            int width = Integer.parseInt(inputFields.get("size").getText());
            int height = Integer.parseInt(inputFields.get("size").getText());
            double movementDistance = Double.parseDouble(inputFields.get("speed").getText());
            double battleRange = Double.parseDouble(inputFields.get("range").getText());

            WorldMap worldMap = new WorldMapBuilder()
                    .width(width)
                    .height(height)
                    .movementDistance(movementDistance)
                    .battleRange(battleRange)
                    .build();

            int rockCount = Integer.parseInt(inputFields.get("rock").getText());
            int paperCount = Integer.parseInt(inputFields.get("paper").getText());
            int scissorsCount = Integer.parseInt(inputFields.get("scissors").getText());

            addRandomElementsToMap(worldMap, rockCount, paperCount, scissorsCount);

            SimulationScreen simulationScreen = new SimulationScreen(worldMap);
            simulationScreen.start(primaryStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> getInvalidParams() {

        List<String> invalidParams = new ArrayList<>();

        for (Map.Entry<String, TextField> entry : inputFields.entrySet()) {

            String settingName = entry.getKey();
            String inputValue = entry.getValue().getText();

            try {

                double value = Double.parseDouble(inputValue);
                double minValue = getMinValue(settingName);
                double maxValue = getMaxValue(settingName);

                if (value < minValue || value > maxValue) {

                    invalidParams.add(settingName);

                }

            } catch (NumberFormatException e) {

                invalidParams.add(settingName);

            }
        }

        return invalidParams;

    }

    private void showAlertWithInvalidParams(List<String> invalidParams) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Parameters");
        alert.setHeaderText(null);

        StringBuilder contentText = new StringBuilder("Invalid input values for the following parameters:\n");

        for (String param : invalidParams) {

            contentText.append(param).append(": ");
            contentText.append("min: ").append(getMinValue(param)).append(", max: ").append(getMaxValue(param)).append("\n");

        }

        contentText.append("Please check the input values and try again.");
        alert.setContentText(contentText.toString());

        alert.showAndWait();

    }

    private double getMinValue(String paramName) {

        return switch (paramName) {

            case "rock", "paper", "scissors" -> 0;
            case "speed" -> 0.1;
            case "range" -> 0.5;
            case "size" -> 5;
            default -> Double.MIN_VALUE;

        };
    }

    private double getMaxValue(String paramName) {

        return switch (paramName) {

            case "rock", "paper", "scissors" -> 20;
            case "speed" -> 0.5;
            case "range" -> 2;
            case "size" -> 12;
            default -> Double.MAX_VALUE;

        };
    }

    private void addRandomElementsToMap(WorldMap worldMap, int rockCount, int paperCount, int scissorsCount) {

        addElementsToMap(worldMap, ElementEnum.ROCK, rockCount);
        addElementsToMap(worldMap, ElementEnum.PAPER, paperCount);
        addElementsToMap(worldMap, ElementEnum.SCISSORS, scissorsCount);

    }

    private void addElementsToMap(WorldMap worldMap, ElementEnum symbol, int elementCount){

        for (int i = 0; i < elementCount; i ++){

            double x = rand.nextDouble() * worldMap.getWidth();
            double y = rand.nextDouble() * worldMap.getHeight();
            Element element = elementFactory.createElement(symbol, new Vector2D(x, y));
            worldMap.getMapElementsManager().addElement(element);

        }

    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;

        VBox root = createRootBox();

        Scene scene = new Scene(root, 800, 475);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
