//package org.example.enums;
//
//import org.example.utils.Vector2D;
//
//public enum MapDirectionEnum {
//
//    NORTH,
//    NORTHEAST,
//    EAST,
//    SOUTHEAST,
//    SOUTH,
//    SOUTHWEST,
//    WEST,
//    NORTHWEST;
//
//    public Vector2D toUnitVector() {
//        return switch (this) {
//            case NORTH -> new Vector2D(0, 1);
//            case NORTHEAST -> new Vector2D(1, 1);
//            case EAST -> new Vector2D(1, 0);
//            case SOUTHEAST -> new Vector2D(1, -1);
//            case SOUTH -> new Vector2D(0, -1);
//            case SOUTHWEST -> new Vector2D(-1, -1);
//            case WEST -> new Vector2D(-1, 0);
//            case NORTHWEST -> new Vector2D(-1, 1);
//        };
//    }
//
//}
