package org.example.elements;

import org.example.WorldMap;
import org.example.enums.ElementEnum;

public class Paper extends Element {

    public Paper(WorldMap worldMap, int x, int y){
        super(worldMap, ElementEnum.PAPER, x, y);
    }

}
