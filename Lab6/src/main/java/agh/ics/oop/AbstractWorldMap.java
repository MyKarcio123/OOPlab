package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap,IPositionChangeObserver{
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
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Animal animal = animalMap.get(oldPosition);
        animalMap.remove(oldPosition);
        animalMap.put(newPosition, animal);
    }

    public boolean isOccupied(Vector2d position){
        return animalMap.containsKey(position);
    }
    public Object objectAt(Vector2d position) { return animalMap.get(position);}
    public abstract boolean canMoveTo(Vector2d position);
    public Vector2d getLeftBottomCorner() {
        return leftBottomCorner;
    }

    public Vector2d getRightTopCorner() {
        return rightTopCorner;
    }
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
