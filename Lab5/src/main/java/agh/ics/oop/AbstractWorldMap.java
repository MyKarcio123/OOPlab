package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap{
    protected Vector2d leftBottomCorner;
    protected Vector2d rightTopCorner;
    protected Map<Vector2d,Animal> animalMap;
    protected final MapVisualizer mapVisualizer;

    public AbstractWorldMap(Vector2d rightTop,Vector2d leftBottom){
        rightTopCorner = rightTop;
        leftBottomCorner = leftBottom;
        animalMap = new HashMap<>();
        mapVisualizer = new MapVisualizer(this);
    }
    protected Vector2d getKey(Animal animal){
        for (Vector2d key : animalMap.keySet()) {
            if(animalMap.get(key).equals(animal)){
                return key;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        return mapVisualizer.draw(leftBottomCorner, rightTopCorner);
    }

    public abstract boolean isOccupied(Vector2d position);
    public abstract Object objectAt(Vector2d position);
    public abstract boolean canMoveTo(Vector2d position);
    public Vector2d getLeftBottomCorner() {
        return leftBottomCorner;
    }

    public Vector2d getRightTopCorner() {
        return rightTopCorner;
    }

}
