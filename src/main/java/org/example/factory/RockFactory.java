package org.example.factory;

import org.example.WorldMap;
import org.example.elements.Rock;

public class RockFactory extends ElementFactory{

    @Override
    public Rock createElement(WorldMap worldMap, int x, int y){
        return new Rock(worldMap, x, y);
    }

}
