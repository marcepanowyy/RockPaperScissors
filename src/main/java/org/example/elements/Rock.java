package org.example.elements;

import org.example.WorldMap;
import org.example.enums.ElementEnum;

public class Rock extends Element {

    public Rock(WorldMap worldMap, int x, int y){
        super(worldMap, ElementEnum.ROCK, x, y);
    }

}
