package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap{

    public RectangularMap(int mapWidth, int mapHeight, int mapStartX, int mapStartY){
        super(new Vector2d(mapStartX+mapWidth,mapStartY+mapHeight),new Vector2d(mapStartX,mapStartY));
        updateMapSize(new Vector2d(mapStartX,mapStartY));
        updateMapSize(new Vector2d(mapStartX+mapWidth,mapStartY+mapHeight));
    }
    public RectangularMap(int mapWidth, int mapHeight){
        this(mapWidth,mapHeight,0,0);
    }

    private boolean validPosition(Vector2d position){
        return ((leftBottomCorner.equals(leftBottomCorner.lowerLeft(position))) && (rightTopCorner.equals(rightTopCorner.upperRight(position))));
    }
    public boolean canMoveTo(Vector2d position){
        if(validPosition(position)){return !super.isOccupied(position);}
        return false;
    }
    public boolean isOccupied(Vector2d position){
        if(validPosition(position)){return super.isOccupied(position);}
        return false;
    }

}
