package org.example.factory;

import org.example.WorldMap;
import org.example.elements.Scissors;

public class ScissorsFactory extends ElementFactory{

    @Override
    public Scissors createElement(WorldMap worldMap, int x, int y){
        return new Scissors(worldMap, x, y);
    }

}
