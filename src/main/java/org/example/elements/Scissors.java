package org.example.elements;

import org.example.enums.ElementEnum;

public class Scissors extends AbstractMapElement implements IElement{

    private final ElementEnum symbol = ElementEnum.SCISSORS;

    // abstract

    public Scissors(int x, int y){
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
        return "S";
    }


}
