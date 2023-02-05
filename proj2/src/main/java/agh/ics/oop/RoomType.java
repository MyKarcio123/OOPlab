package agh.ics.oop;

public enum RoomType {
    BOSS,
    CORRIDOR,
    SHOP,
    ROOM,
    PLAYER,
    UNWALKABLE;

    @Override
    public String toString() {
        return switch (this) {
            case BOSS -> "#FF0000";
            case CORRIDOR -> "#808080";
            case SHOP -> "#FFD700";
            case ROOM -> "#000000";
            case PLAYER -> "#0000FF";
            case UNWALKABLE -> "xd";
        };
    }
}
