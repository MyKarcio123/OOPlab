package agh.ics.oop;

import java.util.Map;

public enum MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST,
    NONE;

    @Override
    public String toString() {
        return switch (this) {
            case NORTH -> "Północ";
            case SOUTH -> "Południe";
            case WEST -> "Zachód";
            case EAST -> "Wschód";
            default -> "Brak kierunku";
        };
    }
    public MapDirection next(){
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            default -> NONE;
        };
    }
    public MapDirection previous(){
        return switch (this) {
            case NORTH -> WEST;
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
            default -> NONE;
        };
    }
    public Vector2d toUnitVector(){
        return switch (this) {
            case NORTH -> new Vector2d(0,1);
            case EAST -> new Vector2d(1,0);
            case SOUTH -> new Vector2d(0,-1);
            case WEST -> new Vector2d(-1,0);
            default -> new Vector2d(0,0);
        };
    }
}
