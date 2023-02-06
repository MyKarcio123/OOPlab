package agh.ics.oop;

import agh.ics.oop.graphAlgorithms.Edge;
import agh.ics.oop.rooms.RoomMap;

public class Game {
    int currentStage;
    RoomMap currentMap;
    public Game(){

    }
    public int getMapSize(){
        return currentMap.getMapSize();
    }
    public void movePlayer(Direction dir){
        currentMap.movePlayer(dir);
    }
}
