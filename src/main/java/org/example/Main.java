package org.example;

import org.example.elements.Element;
import org.example.elements.Paper;
import org.example.elements.Rock;
import org.example.enums.ElementEnum;
import org.example.factory.ElementFactory;
import org.example.utils.UniquePair;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        WorldMap map = new WorldMapBuilder()
                .movementDistance(1)
                .battleRange(2)
                .build();

        ElementFactory elementFactory = new ElementFactory();

        Rock rock1 = (Rock) elementFactory.createElement(ElementEnum.ROCK, 0, 0);
        Paper paper1 = (Paper) elementFactory.createElement(ElementEnum.PAPER, 8, 0);
        Paper paper2 = (Paper) elementFactory.createElement(ElementEnum.PAPER, 8, 0);
        Paper paper3 = (Paper) elementFactory.createElement(ElementEnum.PAPER, 8, 0);

        map.addElement(rock1);
        map.addElement(paper1);

        map.init();

        map.draw();
        map.performRound();
        map.draw();
        map.performRound();

        map.draw();
        map.performRound();

        map.draw();
        map.performRound();


//        HashSet<UniquePair<Element, Element>> uniqueBattleHashSet = new HashSet<>();
//        uniqueBattleHashSet.add(new UniquePair<>(paper1, paper2));
//
//        for (UniquePair battleElements : uniqueBattleHashSet) {
//            System.out.println(battleElements.toString());
//        }

    }
}