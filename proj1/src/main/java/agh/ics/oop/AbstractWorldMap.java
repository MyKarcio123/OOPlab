package agh.ics.oop;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap {
    protected final Map<Pair<Vector2d,Integer>, Animal> animalMap;
    protected final Map<Grass, Animal> grassMap;
    protected final MapVisualizer mapVisualizer;
    private List<Animal> animalList;
    private Vector2d mapUpperRight;
    private Vector2d mapLowerLeft;

    protected AbstractWorldMap() {
        animalMap = new HashMap<>();
        grassMap = new HashMap<>();
        mapVisualizer = new MapVisualizer(this);
        animalList = new ArrayList<>();
    }

    protected Pair<Vector2d, Integer> getKey(Animal animal) {
        for (Pair<Vector2d,Integer> key : animalMap.keySet()) {
            if (animalMap.get(key).equals(animal)) {
                return key;
            }
        }
        return null;
    }

    public Integer getAnimalIndex(Animal animal){
        return Integer.valueOf(animalList.indexOf(animal));
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(this.getMapLowerLeft(), this.getMapUpperRight());
    }


    //@Override
    //public IMapElement objectAt(Vector2d position) {
   //     return animalMap.get(position);
    //}

    public Vector2d getMapLowerLeft(){
        return this.mapLowerLeft;
    }

    public Vector2d getMapUpperRight(){
        return this.mapUpperRight;
    }

    public boolean isOccupied(Vector2d position) {
        return (animalMap.containsKey(position));
    }


//    public boolean place(Animal animal) {
//        if (this.canMoveTo(animal.getPosition())) {
//            animalMap.put(animal.getPosition(), animal);
//            animal.addObserver(this);
//            animal.addObserver(this.mapBoundary);
//            mapBoundary.updateMapBoundary(animal.getPosition());
//            return true;
//        }
//        throw new IllegalArgumentException("You cannot place another animal on " + animal.getPosition());
//    }
}
