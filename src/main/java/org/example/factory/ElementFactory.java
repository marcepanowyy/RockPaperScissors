package org.example.factory;

import org.example.WorldMap;
import org.example.elements.Element;

public abstract class ElementFactory {

    public abstract Element createElement(WorldMap worldMap, int x, int y);

}
