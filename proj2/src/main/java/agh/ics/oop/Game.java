package agh.ics.oop;

import agh.ics.oop.graphAlgorithms.Edge;
import agh.ics.oop.rooms.RoomMap;

public class Game {
    int currentStage;
    RoomMap currentMap;
    public Game(){
        currentStage = 1;
        currentMap = new RoomMap(currentStage);
    }
    public int getMapSize(){
        return currentMap.getMapSize();
    }
    public void movePlayer()
}
