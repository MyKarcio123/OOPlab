package agh.ics.oop;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class SimulationEngine implements Runnable,IAnimalStateEnigneObserver {
    //lista zwierząt jest relacją porządku więc wystarczy jedno przejście przy założeniu że id są posortowane od najmniejszych do największych
    //żeby pozbyć się wszystkich martwych zwierząt w O(n), trzeba tego pilnować żeby były w kolejności, może treeset będzie lepszy ?
    //jednak fajnie było się uczyć algebry, przydatnych pojęć się nauczyłem
    private TreeSet<Animal> animals = new TreeSet<>(Comparator.comparing(Animal::getID));
    private TreeSet<Integer> deathAnimalsIndex = new TreeSet<>();
    private IWorldMap map;

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

    }
    public void run(){
        clearDeathAnimals();
        map.clearDeathAnimals();
        moveAnimals();
        map.eatGrass();
        map.copulateAnimals();
        map.plantGrass();
    }
    private void clearDeathAnimals(){
        for(Integer id : deathAnimalsIndex){
            animals.removeIf(animal -> id == animal.getID());
        }
        deathAnimalsIndex.clear();
    }
    @Override
    public void dieEvent(int id) {
        deathAnimalsIndex.add(id);
    }
}
