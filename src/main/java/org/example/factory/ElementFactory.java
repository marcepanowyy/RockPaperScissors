package org.example.factory;

import org.example.elements.IElement;

public abstract class ElementFactory {

    public abstract IElement createElement(int x, int y);

}
