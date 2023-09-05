package org.example;

import org.example.elements.IElement;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private int width;
    private int height;

    private List<IElement> elements;


    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.elements = new ArrayList<>();
    }

    public void addElement(IElement element) {
        elements.add(element);
    }

    public void removeElement(IElement element) {
        elements.remove(element);
    }



}
