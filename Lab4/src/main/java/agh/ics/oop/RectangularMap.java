package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements IWorldMap{

    private final Vector2d leftBottomCorner;
    private final Vector2d rightTopCorner;
    private final Map<Vector2d,Object> map;
    private final MapVisualizer mapVisualizer;

    public RectangularMap(int mapWidth, int mapHeight, int mapStartX, int mapStartY){
        rightTopCorner = new Vector2d(mapStartX+mapWidth,mapStartY+mapHeight);
        leftBottomCorner = new Vector2d(mapStartX,mapStartY);
        map = new HashMap<>();
        mapVisualizer = new MapVisualizer(this);
    }
    public RectangularMap(int mapWidth, int mapHeight){
        this(mapWidth,mapHeight,0,0);
    }
    private Vector2d getKey(Animal animal){
        for (Vector2d key : map.keySet()) {
            if(map.get(key).equals(animal)){
                return key;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(leftBottomCorner, rightTopCorner);
    }

    private boolean validPosition(Vector2d position){
        return ((leftBottomCorner.equals(leftBottomCorner.lowerLeft(position))) && (rightTopCorner.equals(rightTopCorner.upperRight(position))));
    }
    public boolean canMoveTo(Vector2d position){
        if(validPosition(position)){return !map.containsKey(position);}
        return false;
    }
    public boolean place(Animal animal){
        if(!isOccupied(animal.getPositionOnPlane())) {
            if (getKey(animal) != null) {
                map.remove(getKey(animal));
            }
            map.put(animal.getPositionOnPlane(), animal);
            return true;
        }
        return false;
    }
    public boolean isOccupied(Vector2d position){
        if(validPosition(position)){return map.containsKey(position);}
        return false;
    }
    public Object objectAt(Vector2d position){return map.get(position);}

    public Vector2d getLeftBottomCorner() {
        return leftBottomCorner;
    }

    public Vector2d getRightTopCorner() {
        return rightTopCorner;
    }


}
