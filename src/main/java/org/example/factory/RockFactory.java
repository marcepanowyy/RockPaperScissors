package org.example.factory;

import org.example.elements.Rock;

public class RockFactory extends ElementFactory{

    @Override
    public Rock createElement(int x, int y){
        return new Rock(x, y);
    }

}