package org.example.factory;

import org.example.WorldMap;
import org.example.elements.Paper;

public class PaperFactory extends ElementFactory{

    @Override
    public Paper createElement(WorldMap worldMap, int x, int y){
        return new Paper(worldMap, x, y);
    }

}
