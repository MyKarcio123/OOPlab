package agh.ics.oop;

import agh.ics.oop.gui.SimulationApplication;

import java.util.*;

import static agh.ics.oop.Parameters.MOVE_DELAY;

public class SimulationEngine implements Runnable,IAnimalStateEnigneObserver,IMapStateEngineObserver {
    //lista zwierząt jest relacją porządku więc wystarczy jedno przejście przy założeniu że id są posortowane od najmniejszych do największych
    //żeby pozbyć się wszystkich martwych zwierząt w O(n), trzeba tego pilnować żeby były w kolejności, może treeset będzie lepszy ?
    //jednak fajnie było się uczyć algebry, przydatnych pojęć się nauczyłem
    //A jak to jest po kosmicku?
    // ble + ble ble = ble ble ble
    private List<Animal> animals = new LinkedList<>();
    private TreeSet<Integer> deathAnimalsIndex = new TreeSet<>();
    private AbstractWorldMap map;
    private int dayCounter = 0;
    private SimulationApplication app;

    public SimulationEngine(SimulationApplication app){
        if(Parameters.MAP_VARIANT==0) this.map = new EarthMap(this);
        else this.map = new HellMap(this);
        this.app = app;
        generateAnimals();
    }

    public SimulationEngine(){
        if(Parameters.MAP_VARIANT==0) this.map = new EarthMap(this);
        else this.map = new HellMap(this);
        generateAnimals();
    }
    private void moveAnimals(){
        for(Animal animal : animals){
            animal.dayCycle();
        }
    }
    public void run(){
        clearDeathAnimals();
        map.clearDeathAnimals();
        moveAnimals();
        int howManyGrassToAdd = map.eatGrass();
        map.copulateAnimals();
        map.plantGrass(howManyGrassToAdd);

        app.refreshMap();
        System.out.println(map);
        try{
            Thread.sleep(MOVE_DELAY);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        dayCounter+=1;
    }
    private void clearDeathAnimals(){
        for(Integer id : deathAnimalsIndex){
            animals.removeIf(animal -> id == animal.getID());
        }
        deathAnimalsIndex.clear();
    }
    private void generateAnimals(){
        for(int i=0;i<Parameters.STARTING_ANIMALS;++i){
            Animal newAnimal = new Animal(map,RandomPosition.getRandomPosition(map.getMapLowerLeft(),map.getMapUpperRight()),generateGenotype(),dayCounter,this);
            animals.add(newAnimal);
            map.place(newAnimal);
        }
    }
    private List<Integer> generateGenotype(){
        List<Integer> genotype = new ArrayList<>();
        Random random = new Random();
        for(int i=0;i<Parameters.GEN_LENGTH;++i){
            genotype.add(random.nextInt(0,8));
        }
        return genotype;
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

    public AbstractWorldMap getMap() {
        return this.map;
    }
}
