package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class SimulationEngine implements Runnable,IAnimalStateEnigneObserver,IMapStateEngineObserver {
    //lista zwierząt jest relacją porządku więc wystarczy jedno przejście przy założeniu że id są posortowane od najmniejszych do największych
    //żeby pozbyć się wszystkich martwych zwierząt w O(n), trzeba tego pilnować żeby były w kolejności, może treeset będzie lepszy ?
    //jednak fajnie było się uczyć algebry, przydatnych pojęć się nauczyłem
    //A jak to jest po kosmicku?
    // ble + ble ble = ble ble ble
    private List<Animal> animals = new LinkedList<>();
    private TreeSet<Integer> deathAnimalsIndex = new TreeSet<>();
    private IWorldMap map;
    private int dayCounter = 0;

    public SimulationEngine(List<Animal> animalsList,IWorldMap map){
        this.map = map;
        animals.addAll(animalsList);
    }
    private void moveAnimals(){
        for(Animal animal : animals){
            animal.dayCycle();
        }
    }
    private void addAnimalsToMap(){
        for(Animal animal : animals){
            map.place(animal);
        }
    }

    public void run(){
        clearDeathAnimals();
        map.clearDeathAnimals();
        moveAnimals();
        int howManyGrassToAdd = map.eatGrass();
        map.copulateAnimals();
        map.plantGrass(howManyGrassToAdd);
        dayCounter+=1;
    }
    private void clearDeathAnimals(){
        for(Integer id : deathAnimalsIndex){
            animals.removeIf(animal -> id == animal.getID());
        }
        deathAnimalsIndex.clear();
    }
    @Override
    public int dieEvent(int id) {
        deathAnimalsIndex.add(id);
        return dayCounter;
    }

    @Override
    public Animal bornEvent(AbstractWorldMap map, Vector2d position, List<Integer> genotype) {
        Animal newAnimal = new Animal(map,position,genotype,dayCounter,this);
        animals.add(newAnimal);
        return newAnimal;
    }
}
