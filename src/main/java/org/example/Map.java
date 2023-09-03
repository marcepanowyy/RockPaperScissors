package org.example;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private int width;
    private int height;

    private List<IMapElement> elements;


    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.elements = new ArrayList<>();
    }

    public void addElement(IMapElement element) {
        elements.add(element);
    }

    public void removeElement(IMapElement element) {
        elements.remove(element);
    }



}
