package agh.ics.oop;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class SimulationEngine implements Runnable,IAnimalStateEnigneObserver {
    //lista zwierząt jest relacją porządku więc wystarczy jedno przejście przy założeniu że id są posortowane od najmniejszych do największych
    //żeby pozbyć się wszystkich martwych zwierząt w O(n), trzeba tego pilnować żeby były w kolejności, może treeset będzie lepszy ?
    //jednak fajnie było się uczyć algebry, przydatnych pojęć się nauczyłem
    //A jak to jest po kosmicku?
    private List<Animal> animals = new LinkedList<>();
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
        //TODO a czy przypadkiem nie powinniśmy teraz czyścić tych list z miejscami kopulacji i trawą do zjedzenia?
        clearDeathAnimals();
        map.clearDeathAnimals();
        moveAnimals();
        int howManyGrassToAdd = map.eatGrass();
        map.copulateAnimals();
        map.plantGrass(howManyGrassToAdd);
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
