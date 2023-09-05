package org.example.elements;

public class Rock extends MapElement implements IElement{

    // abstract class

    public Rock(int x, int y){
        super(x, y);
    }

    // interface

    @Override
    public void interactWith(IElement otherElement){

    }



}
