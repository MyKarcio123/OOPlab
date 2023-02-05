package agh.ics.oop;

public enum RoomType {
    BOSS,
    CORRIDOR,
    SHOP,
    ROOM,
    UNWALKABLE;

    @Override
    public String toString() {
        return switch (this) {
            case BOSS -> "#FF0000";
            case CORRIDOR -> "#808080";
            case SHOP -> "#FFD700";
            case ROOM -> "#000000";
            case UNWALKABLE -> "xd";
        };
    }
}
