package org.simulation.gui.screen;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SettingsLoader {

    private static final String[] imagePaths = {

            "rock.png",
            "paper.png",
            "scissors.png",
            "measure.png",
            "radar.png",
            "run.png"
    };

    private final int imageSize;

    public SettingsLoader(int imageSize) {
        this.imageSize = imageSize;
    }

    public Map<String, Map<String, Object>> loadSettingProperties() {

        Map<String, Map<String, Object>> settingPropertyMap = new HashMap<>();

        for (String imagePath : imagePaths) {

            String imageName = imagePath.substring(0, imagePath.indexOf('.'));

            Image image = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(imagePath)));
            ImageView imageView = new ImageView(image);

            imageView.setFitWidth(imageSize);
            imageView.setPreserveRatio(true);

            Map<String, Object> setting = new HashMap<>();
            setting.put("image", imageView);
            setting.put("input", "this is a random input");
            setting.put("description", "This is the " + imageName + " image.");

            settingPropertyMap.put(imageName, setting);

        }

        return settingPropertyMap;

    }
}
