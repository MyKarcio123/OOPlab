package agh.ics.oop;

import java.util.Map;
import java.util.Random;

public enum MapDirection {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST,
    NONE;

    @Override
    public String toString() {
        return switch (this) {
            case NORTH -> "Północ";
            case NORTHEAST -> "Północny-Wchód";
            case EAST -> "Wschód";
            case SOUTHEAST -> "Południowy-Wschód";
            case SOUTH -> "Południe";
            case SOUTHWEST -> "Południowy-Zachód";
            case WEST -> "Zachód";
            case NORTHWEST -> "Północ-Zachód";
            default -> "Brak kierunku";
        };
    }
    public Vector2d toUnitVector(){
        return switch (this) {
            case NORTH -> new Vector2d(0,1);
            case NORTHEAST -> new Vector2d(1,1);
            case EAST -> new Vector2d(1,0);
            case SOUTHEAST -> new Vector2d(1,-1);
            case SOUTH -> new Vector2d(0,-1);
            case SOUTHWEST -> new Vector2d(-1,-1);
            case WEST -> new Vector2d(-1,0);
            case NORTHWEST -> new Vector2d(-1,1);
            default -> new Vector2d(0,0);
        };
    }
    public static MapDirection getRandom(){
        Random rn = new Random();
        return MapDirection.values()[rn.nextInt(8)];
    }
}
