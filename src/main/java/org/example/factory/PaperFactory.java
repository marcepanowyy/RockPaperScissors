package org.example.factory;

import org.example.elements.Paper;

public class PaperFactory extends ElementFactory{

    @Override
    public Paper createElement(int x, int y){
        return new Paper(x, y);
    }

}
