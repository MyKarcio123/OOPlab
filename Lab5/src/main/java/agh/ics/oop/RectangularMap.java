package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap{

    public RectangularMap(int mapWidth, int mapHeight, int mapStartX, int mapStartY){
        super(new Vector2d(mapStartX+mapWidth,mapStartY+mapHeight),new Vector2d(mapStartX,mapStartY));
    }
    public RectangularMap(int mapWidth, int mapHeight){
        this(mapWidth,mapHeight,0,0);
    }

    private boolean validPosition(Vector2d position){
        return ((leftBottomCorner.equals(leftBottomCorner.lowerLeft(position))) && (rightTopCorner.equals(rightTopCorner.upperRight(position))));
    }
    public boolean canMoveTo(Vector2d position){
        if(validPosition(position)){return !animalMap.containsKey(position);}
        return false;
    }
    public boolean isOccupied(Vector2d position){
        if(validPosition(position)){return animalMap.containsKey(position);}
        return false;
    }
    public Object objectAt(Vector2d position){return animalMap.get(position);}

    public boolean place(Animal animal){
        if(!isOccupied(animal.getPositionOnPlane())) {
            if (getKey(animal) != null) {
                animalMap.remove(getKey(animal));
            }
            animalMap.put(animal.getPositionOnPlane(), animal);
            return true;
        }
        return false;
    }

}
