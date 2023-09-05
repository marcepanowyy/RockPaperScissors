package org.example.elements;

import org.example.enums.ElementEnum;

public class Rock extends AbstractMapElement implements IElement{

    private final ElementEnum symbol = ElementEnum.ROCK;

    // abstract

    public Rock(int x, int y){
        super(x, y);
    }

    // end abstract

    // interface

    @Override
    public void interactWith(IElement otherElement){

    }

    // end interface

    @Override
    public String toString() {
        return "R";
    }


}
