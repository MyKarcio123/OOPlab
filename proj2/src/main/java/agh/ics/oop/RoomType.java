package agh.ics.oop;

import java.util.List;

public enum RoomType {
    BOSS,
    CORRIDOR,
    SHOP,
    ROOM,
    PLAYER,
    UNWALKABLE;
    public static List<RoomType> roomTypesList = List.of(new RoomType[]{BOSS, SHOP, ROOM});
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
    public Boolean isRoom(){
        return roomTypesList.contains(this);
    }
}
