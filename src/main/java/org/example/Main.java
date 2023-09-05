package org.example;

import org.example.elements.Paper;
import org.example.factory.PaperFactory;

public class Main {
    public static void main(String[] args) {

        PaperFactory paperFactory = new PaperFactory();
        Paper paper1 = paperFactory.createElement(1, 1);
        System.out.println(paper1.getPosition());

    }
}