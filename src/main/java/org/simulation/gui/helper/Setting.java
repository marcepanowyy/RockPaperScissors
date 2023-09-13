package org.simulation.gui.helper;

import javafx.scene.image.ImageView;

public class Setting {

    private final String name;
    private final String title;
    private final String description;
    private final String input;
    private final ImageView imageView;

    public Setting(String name, String title, String description, String input, ImageView imageView) {
        this.name = name;
        this.title = title;
        this.description = description;
        this.input = input;
        this.imageView = imageView;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getInput() {
        return input;
    }


    public ImageView getImageView() {
        return imageView;
    }


}
