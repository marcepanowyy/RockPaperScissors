//package org.example.core.map;
//
//import org.example.core.map.WorldMap;
//import org.example.core.elements.Element;
//import org.example.core.elements.Rock;
//import org.example.core.enums.ElementEnum;
//import org.example.core.factory.ElementFactory;
//import org.example.core.map.builder.WorldMapBuilder;
//import org.example.core.utils.Vector2D;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class WorldMapTest {
//
//    static WorldMap worldMap;
//
//    static ElementFactory elementFactory;
//
//    private static Element rock1;
//
//    private static Element paper1;
//
//    private static Element paper2;
//
//    @BeforeAll
//    public static void setUpBeforeAll(){
//
//        elementFactory = new ElementFactory();
//
//    }
//
//    @BeforeEach
//    public void setUpBeforeEach(){
//
//        worldMap = new WorldMapBuilder()
//                .height(15)
//                .build();
//
//        rock1 = elementFactory.createElement(ElementEnum.ROCK, new Vector2D(0, 0));
//        paper1 = elementFactory.createElement(ElementEnum.PAPER, new Vector2D(3, 4));
//        paper2 = elementFactory.createElement(ElementEnum.PAPER, new Vector2D(5, 12));
//
//    }
//
//}
