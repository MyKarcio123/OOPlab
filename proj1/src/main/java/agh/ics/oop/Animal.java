package agh.ics.oop;

import java.util.List;
import java.util.Random;

import static agh.ics.oop.Parameters.STARTING_ENERGY;

public class Animal extends AbstractMapElement{
    private MapDirection orientation;
    private final AbstractWorldMap map;
    private List<MapDirection> genotype;
    private Random rd = new Random();
    int activeGen = 0;
    int energy = STARTING_ENERGY;

    public Animal(AbstractWorldMap map,Vector2d position,List<MapDirection> genotype){
        this.orientation = MapDirection.getRandom();
        this.genotype = genotype;
        this.position = position;
        this.map = map;
        getRandomActiveGen();
    }
    public void dayCycle(){
        move();
        newActiveGen();
        lowerEnergy();
    }
    private void move(){
        Vector2d newPosition = position.add(orientation.toUnitVector());
        //miejsce na move
        this.position= newPosition;
    }
    private void newActiveGen(){
        int newGen=activeGen;
        if(true){
            //normalnie
            newGen+=1;
        }else if(false){
            //lekka korekta
            int dir = rd.nextInt(2);
            if(dir==0) newGen-=1;
            else newGen+=1;
        }else if(true){
            //pełna losowość
            newGen = rd.nextInt(genotype.size()-1);
            if(newGen>=activeGen) newGen+=1;
        }
        if(newGen>=genotype.size()) newGen=0;
        if(newGen<0) newGen=genotype.size()-1;
        activeGen=newGen;
    }
    private void getRandomActiveGen(){
        activeGen = rd.nextInt(genotype.size());
    }
    private void lowerEnergy(){
        energy-=1;
    }
    @Override
    public String toString(){return "";}
    public boolean isAt(Vector2d position){return this.position.equals(position);}
    public Vector2d getPosition(){return position;}
}
