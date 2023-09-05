package org.example.elements;

import org.example.enums.ElementEnum;

public class Paper extends AbstractMapElement implements IElement {

    // abstract

    public Paper(int x, int y){
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
        return "P";
    }

}
