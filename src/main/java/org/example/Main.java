package org.example;

import org.example.elements.Element;
import org.example.elements.Paper;
import org.example.elements.Rock;
import org.example.elements.Scissors;
import org.example.enums.ElementEnum;
import org.example.factory.ElementFactory;
import org.example.utils.UniquePair;
import org.example.utils.Vector2D;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        WorldMap map = new WorldMapBuilder()
                .movementDistance(1)
                .battleRange(2)
                .build();

        ElementFactory elementFactory = new ElementFactory();

        Rock rock1 = (Rock) elementFactory.createElement(ElementEnum.ROCK, new Vector2D(0, 0));
        Paper paper1 = (Paper) elementFactory.createElement(ElementEnum.PAPER, new Vector2D(8, 0));
        Scissors scissors1 = (Scissors) elementFactory.createElement(ElementEnum.SCISSORS, new Vector2D(4, 9));

        map.addElement(rock1);
        map.addElement(paper1);
        map.addElement(scissors1);

        map.init();

        map.draw();

        map.performRound();
        map.draw();

        map.performRound();
        map.draw();

        map.performRound();
        map.draw();

        map.performRound();
        map.draw();

        map.performRound();
        map.draw();

        map.performRound();
        map.draw();

        map.performRound();
        map.draw();

        map.performRound();
        map.draw();


//        HashSet<UniquePair<Element, Element>> uniqueBattleHashSet = new HashSet<>();
//        uniqueBattleHashSet.add(new UniquePair<>(paper1, paper2));
//
//        for (UniquePair battleElements : uniqueBattleHashSet) {
//            System.out.println(battleElements.toString());
//        }

    }
}