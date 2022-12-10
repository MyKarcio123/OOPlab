package agh.ics.oop;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IAnimalStateMapObserver {
    protected final SetMultimap<Vector2d,Animal> animalMap;
    protected final Map<Grass, Animal> grassMap;
    protected final MapVisualizer mapVisualizer;
    private List<Vector2d> animalsToRemove;
    private HashMap<Vector2d,Integer> deathAnimals;
    private Vector2d mapUpperRight;
    private Vector2d mapLowerLeft;


    protected AbstractWorldMap() {
        animalMap = MultimapBuilder.hashKeys().treeSetValues().build();
        grassMap = new HashMap<>();
        mapVisualizer = new MapVisualizer(this);
        animalsToRemove = new LinkedList<>();
    }

    protected Vector2d getKey(Animal animal) {
        for (Vector2d key : animalMap.keySet()) {
            if (animalMap.get(key).equals(animal)) {
                return key;
            }
        }
        return null;
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
    public void clearDeathAnimals(){
        for (Vector2d key: animalMap.keySet()){

        }
    }
    //Logiczne uzasadnienie czeemu do dieEvent przekazuję tylko współrzędne mimo że pod jedną współrzędną w mapie może być n obiektów.
    //Jest to spowodwane tym, że w mapie zwierząt jest coś ala TreeSet którego pierwszym kryterium jest energia malejąco.
    //Z tego faktu wynika że nie muszę wiedzieć dokładnie które zwierzę umarło ale ile, bo będę od końca usuwał konieczną ilość zwierząt.
    @Override
    public void dieEvent(Vector2d position){
        if(deathAnimals.containsKey(position)) {
            deathAnimals.replace(position,deathAnimals.get(position)+1);
        }
        deathAnimals.put(position,1);
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, int id) {
        Set<Animal> animals = animalMap.get(oldPosition);
        Animal currentAnimal=null;
        for(Animal animal : animals){
            if(animal.getID()==id){
                currentAnimal=animal;
                animals.remove(currentAnimal);
            }
        }
        if (currentAnimal != null){animalMap.put(newPosition,currentAnimal);}

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
