package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements IWorldMap{

    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    private final Map<Vector2d,Object> map;
    private final MapVisualizer mapVisualizer;

    public RectangularMap(int mapWidth, int mapHeight, int mapStartX, int mapStartY){
        upperRight = new Vector2d(mapWidth,mapHeight);
        lowerLeft = new Vector2d(mapStartX,mapStartY);
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
        return mapVisualizer.draw(lowerLeft,upperRight);
    }

    private boolean validPosition(Vector2d position){
        return ((lowerLeft.equals(lowerLeft.lowerLeft(position))) && (upperRight.equals(upperRight.upperRight(position))));
    }
    public boolean canMoveTo(Vector2d position){
        if(validPosition(position)){return !map.containsKey(position);}
        return false;
    }
    public boolean place(Animal animal){
        if(!isOccupied(animal.getPosition())) {
            if (getKey(animal) != null) {
                map.remove(getKey(animal));
            }
            map.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }
    public boolean isOccupied(Vector2d position){
        if(validPosition(position)){return map.containsKey(position);}
        return false;
    }
    public Object objectAt(Vector2d position){return map.get(position);}

}
