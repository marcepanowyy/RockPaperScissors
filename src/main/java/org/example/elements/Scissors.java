package org.example.elements;

import org.example.WorldMap;
import org.example.enums.ElementEnum;

public class Scissors extends Element {
    public Scissors(WorldMap worldMap, int x, int y){
        super(worldMap, ElementEnum.SCISSORS, x, y);
    }

}
