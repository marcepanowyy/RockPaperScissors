package org.example.utils;

import org.example.elements.Element;

public class ElementDistancePair {

    private final Element element;
    private final double distance;

    public ElementDistancePair(Element element, double distance) {
        this.element = element;
        this.distance = distance;
    }

    public Element getElement() {
        return element;
    }

    public double getDistance() {
        return distance;
    }

}
