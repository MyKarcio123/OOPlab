package agh.ics.oop;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST,
    NONE;

    @Override
    public String toString() {
        return switch (this) {
            case EAST -> "E";
            case SOUTH -> "S";
            case WEST -> "W";
            default -> "N";
        };
    }

    public Vector2d toUnitVector(){
        return switch (this) {
            case NORTH -> new Vector2d(0,1);
            case EAST -> new Vector2d(-1,0);
            case SOUTH -> new Vector2d(0,-1);
            case WEST -> new Vector2d(1,0);
            default -> new Vector2d(0,0);
        };
    }

}
