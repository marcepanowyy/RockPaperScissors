package org.simulation.gui.helper;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.*;

public class SettingsLoader {

    List<Setting> settingList = new ArrayList<>();

    private final int imageSize;

    public SettingsLoader(int imageSize) {
        this.imageSize = imageSize;
        loadSettings();
    }

    public Map<String, Setting> getSettingProperties() {

        Map<String, Setting> settingPropertiesMap = new HashMap<>();

        for (Setting setting : settingList) {

            settingPropertiesMap.put(setting.getName(), setting);

        }

        return settingPropertiesMap;

    }

    public List<Setting> loadSettings() {

        Setting mapSizeSetting = new Setting(
                "size",
                "Map Size",
                "Determine the dimensions of the map",
                "10",
                loadImage("size.png")
        );


        Setting battleRangeSetting = new Setting(
                "range",
                "Battle Range",
                "Define the combat distance between elements",
                "0.8",
                loadImage("range.png")
        );

        Setting movementSpeedSetting = new Setting(
                "speed",
                "Movement Speed",
                "Set the speed at which elements move",
                "0.15",
                loadImage("speed.png")

        );

        Setting rockElementsSetting = new Setting(
                "rock",
                "Rock Elements",
                "Specify the quantity of ROCK items on the map",
                "10",
                loadImage("rock.png")

        );

        Setting paperElementsSetting = new Setting(
                "paper",
                "Paper Elements",
                "Indicate the quantity of PAPER items on the map",
                "10",
                loadImage("paper.png")

        );

        Setting scissorsElementsSetting = new Setting(
                "scissors",
                "Scissors Elements",
                "Decide how many SCISSORS items are on the map",
                "10",
                loadImage("scissors.png")

        );

        settingList.add(mapSizeSetting);
        settingList.add(battleRangeSetting);
        settingList.add(movementSpeedSetting);
        settingList.add(rockElementsSetting);
        settingList.add(paperElementsSetting);
        settingList.add(scissorsElementsSetting);

        return settingList;

    }

    private ImageView loadImage(String imagePath){

        Image image = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(imagePath)));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(imageSize);
        imageView.setPreserveRatio(true);

        return imageView;

    }

}
