package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static agh.ics.oop.DataParameters.getDataID;
import static java.lang.Math.min;

public class Animal extends AbstractMapElement implements Comparable<Animal> {
    private MapDirection orientation;
    private final List<Integer> genotype;
    private final Random rd = new Random();
    private final int id;
    int activeGen = 0;
    private int childAmount = 0;
    private final int day;
    private int dayOfDeath;
    private int grassEaten;
    private int energy;
    private final IAnimalStateMapObserver stateMapObserver;
    private final IAnimalStateEnigneObserver stateEnigneObserver;
    private DataParameters dataParameters;

    public Animal(AbstractWorldMap map, Vector2d position, List<Integer> genotype, int day, SimulationEngine engine) {
        this.stateMapObserver = map;
        this.dataParameters = map.getDataParameters();
        this.orientation = MapDirection.getRandom();
        this.genotype = genotype;
        this.position = position;
        this.energy = dataParameters.getStratingAnimalsEnergy();
        this.id = getDataID();
        this.day = day;
        this.stateEnigneObserver = engine;
        getRandomActiveGen();
    }

    public void dayCycle() {
        updateDirection();
        move();
        newActiveGen();
        lowerEnergy();
    }
    public void setPosition(Vector2d position){
        this.position = position;
    }
    private void updateDirection() {
        int currentOrientIndex = this.orientation.ordinal();
        currentOrientIndex += genotype.get(activeGen);
        currentOrientIndex %= 8;
        this.orientation = MapDirection.values()[currentOrientIndex];
    }

    private void move() {
        Vector2d newPosition = position.add(orientation.toUnitVector());
        Vector2d futurePosition = stateMapObserver.positionChanged(position, newPosition, id);
        if(futurePosition!=newPosition){
            newPosition=futurePosition;
            if(dataParameters.getMapVariant()==1){
                energy -= dataParameters.getCopulateEnergyDecrease();
            }
        }
        this.position = newPosition;
    }

    private void newActiveGen() {
        if (dataParameters.getNextGenType() == 0) {                       //pełna predystynacja
            activeGen += 1;
        } else {                                      //nieco szaleństwa
            int chance = rd.nextInt(10) + 1;
            if (chance < 3) {
                int dir = rd.nextInt(genotype.size() - 1);
                if (dir >= activeGen) activeGen = dir + 1;
            } else activeGen += 1;
        }
        activeGen %= genotype.size();
    }

    private void getRandomActiveGen() {
        activeGen = rd.nextInt(genotype.size());
    }

    private void lowerEnergy() {
        energy -= 1;
        if (energy <= 0) {
            stateMapObserver.dieEvent(position);
            dayOfDeath = stateEnigneObserver.dieEvent(id);
        }
    }

    public boolean canCopulate() {
        return energy >= dataParameters.getMinimumCopulateEnergy();
    }

    //Przeładowuję funkcję gainEnergy jeżeli w przyszłości hipotetycznie chcielibyśmy dodać inną wartość za każdą trawę
    public void gainEnergy(int energyValue) {
        energy += energyValue;
    }
    public void grassCounter(){grassEaten+=1;}

    public void gainEnergy() {
        energy += dataParameters.getEnergyFromGrass();
    }

    public int getID() {
        return id;
    }

    //side - lewo = 0, prawo = 0, ratio liczba z przedziału <0,1> która wskazuje jaki procent genów leci do bobusia
    public List<Integer> copulate(int side, float ratio) {
        int currentIndex;
        energy -= dataParameters.getCopulateEnergyDecrease();
        int genesToChild = (int) (genotype.size() * ratio);
        List<Integer> childGenotype = new ArrayList<>();
        if (side == 0) currentIndex = genotype.size() - genesToChild;
        else currentIndex = 0;
        for (int i = 0; i < genesToChild; i += 1) {
            childGenotype.add(genotype.get(currentIndex));
            currentIndex += 1;
        }
        childAmount+=1;
        return childGenotype;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public int getEnergy() {
        return energy;
    }

    public int getDay() {
        return day;
    }

    public int getChildAmount() {
        return childAmount;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Animal otherAnimal) {
            return (this.id == otherAnimal.id);
        }
        return false;
    }

    @Override
    public String toString() {
        return position + " " + id + "E:" + energy;
    }

    @Override
    public int compareTo(Animal animal) {
        if(animal.getID()==this.getID()){return 0;}
        int v;
        if ((v = Integer.compare(getEnergy(), animal.getEnergy())) != 0) {
            return -v;
        }
        if ((v = Integer.compare(getDay(), animal.getDay())) != 0) {
            return v;
        }
        if ((v = Integer.compare(getChildAmount(), animal.getChildAmount())) != 0) {
            return -v;
        }
        v = rd.nextInt(2);
        if (v == 0) return -1;
        return 1;
    }

}
