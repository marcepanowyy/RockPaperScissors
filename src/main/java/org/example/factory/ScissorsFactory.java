package org.example.factory;

import org.example.elements.Scissors;

public class ScissorsFactory extends ElementFactory{

    @Override
    public Scissors createElement(int x, int y){
        return new Scissors(x, y);
    }

}
