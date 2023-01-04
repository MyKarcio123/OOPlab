package agh.ics.oop;

import agh.ics.oop.gui.SimulationApplication;
import agh.ics.oop.gui.StatsApplication;

import java.util.*;


public class SimulationEngine implements Runnable, IAnimalStateEnigneObserver, IMapStateEngineObserver {
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
    private StatsApplication statsApplication = null;
    private DataParameters dataParameters;
    private boolean exit = false;
    private boolean stop = false;
    private List<Integer> dayHistory ;
    private List<Integer> aliveAnimalsHistory;
    private List<Integer> grassHistory;
    private List<Integer> deadAnimalsHistory;

    public SimulationEngine(SimulationApplication app, DataParameters currentConfig) {
        dataParameters = currentConfig;
        dayHistory = app.getDayHistory();
        aliveAnimalsHistory = app.getAliveAnimalsHistory();
        deadAnimalsHistory = app.getDeadAnimalsHistory();
        grassHistory = app.getGrassHistory();
        if (dataParameters.getMapVariant() == 0) this.map = new EarthMap(this, currentConfig);
        else this.map = new HellMap(this, currentConfig);
        this.app = app;
        generateAnimals();
    }

    public void setStatsApplication(StatsApplication statsApplication){
        this.statsApplication = statsApplication;
    }

    public SimulationEngine(DataParameters currentConfig) {
        dataParameters = currentConfig;
        if (dataParameters.getMapVariant() == 0) this.map = new EarthMap(this, currentConfig);
        else this.map = new HellMap(this, currentConfig);
        generateAnimals();
    }

    public void setStop(boolean val){
        this.stop = val;
    }

    public boolean getStop(){
        return this.stop;
    }

    private void moveAnimals() {
        for (Animal animal : animals) {
            animal.dayCycle();
        }
    }

    public void run() {
        while (!exit) {
            while (true) {
                if (!stop && !exit) {
                    clearDeathAnimals();
                    map.clearDeathAnimals();

                    moveAnimals();
                    int howManyGrassToAdd = map.eatGrass();

                    map.copulateAnimals();
                    map.plantGrass(dataParameters.getNewGrassPerDay());

                    app.refreshMap();
                    dayHistory.add(dayCounter);
                    aliveAnimalsHistory.add(map.getNumberOfAnimals());
                    deadAnimalsHistory.add(map.getAmountOfAnimalsDead());
                    grassHistory.add(map.getAmountOfGrass());
                    if (statsApplication != null){
                        statsApplication.refreshStats();
                    }


                    try {
                        Thread.sleep(dataParameters.getMoveDelay());
                    } catch (InterruptedException e) {
                        System.out.println("Koniec symulacji, bo została interrupted");
                    }

                    dayCounter += 1;
                }else{
                    try {
                        System.out.println("zatrzymano nas");
                        Thread.sleep(50);
                        if(exit){
                            break;
                        }
                    } catch (InterruptedException e) {
                        System.out.println("Koniec symulacji, bo została interrupted");
                    }
                }
            }
        }
    }

    private void clearDeathAnimals() {
        for (Integer id : deathAnimalsIndex) {
            animals.removeIf(animal -> id == animal.getID());
        }
        deathAnimalsIndex.clear();
    }

    private void generateAnimals() {
        for (int i = 0; i < dataParameters.getStartingAnimals(); ++i) {
            Animal newAnimal = new Animal(map, RandomPosition.getRandomPosition(map.getMapLowerLeft(), map.getMapUpperRight()), generateGenotype(), dayCounter, this);
            animals.add(newAnimal);
            map.place(newAnimal);
        }
    }

    private List<Integer> generateGenotype() {
        List<Integer> genotype = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < dataParameters.getGenomeLength(); ++i) {
            genotype.add(random.nextInt(0, 8));
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
        Animal newAnimal = new Animal(map, position, genotype, dayCounter, this);
        animals.add(newAnimal);
        return newAnimal;
    }

    public AbstractWorldMap getMap() {
        return this.map;
    }

    public void setExit(boolean b) {
        this.exit = b;
    }
}
