package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GrassField implements IWorldMap{
    private final int grassAmount;
    private Vector2d leftBottomCorner;
    private Vector2d rightTopCorner;
    private final Map<Vector2d,Grass> grassMap = new HashMap<>();
    private final Map<Vector2d,Animal> animalMap = new HashMap<>();
    private final MapVisualizer mapVisualizer;

    public GrassField(int amount){
        grassAmount = amount;
        leftBottomCorner = new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE);
        rightTopCorner = new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE);
        mapVisualizer = new MapVisualizer(this);
        placeGrass(grassAmount);
        updateMapSize();
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(leftBottomCorner, rightTopCorner);
    }

    private void placeGrass(int amount){
    Vector2d grassPosition = new Vector2d(randomPosition(),randomPosition());
        for(int i=0;i<amount;++i){
            while(!place(grassPosition)){
                grassPosition=new Vector2d(randomPosition(),randomPosition());
            }
        }
    }
    private int randomPosition(){
        Random random = new Random();
        int max=(int)Math.sqrt(grassAmount*10);
        int nxt = random.nextInt(max+1);
        return nxt;
    }
    //ISOCCUPIED ROBI PROBLEMY!!
    public boolean isOccupied(Vector2d position){
        return (grassMap.containsKey(position) || animalMap.containsKey(position));
    }
    public boolean isGrass(Vector2d position){
        return grassMap.containsKey(position);
    }
    private boolean isAnimal(Vector2d position){
        return animalMap.containsKey(position);
    }
    public boolean place(Animal animal){
        System.out.println("position = " + animal.getPositionOnPlane() + " 3D " + animal.getPosition());
        if(!isAnimal(animal.getPositionOnPlane())) {
            if (getKey(animal) != null) {
                animalMap.remove(getKey(animal));
            }
            animalMap.put(animal.getPositionOnPlane(), animal);
            updateMapSize();
            return true;
        }
        return false;
    }
    public boolean place(Vector2d grassPosition){
        if(!isOccupied(grassPosition)){
            grassMap.put(grassPosition,new Grass(grassPosition));
            return true;
        }
        return false;
    }
    private Vector2d getKey(Animal animal){
        for (Vector2d key : animalMap.keySet()) {
            if(animalMap.get(key).equals(animal)){
                return key;
            }
        }
        return null;
    }
    public Object objectAt(Vector2d position){
        if(animalMap.get(position)==null){
           return grassMap.get(position);
        }
        return animalMap.get(position);
    }
    public boolean canMoveTo(Vector2d position){
        return !animalMap.containsKey(position);
    }
    private void updateMapSize(){
        for (Vector2d position : grassMap.keySet()) {
            leftBottomCorner = leftBottomCorner.lowerLeft(position);
            rightTopCorner = rightTopCorner.upperRight(position);
        }
        for (Vector2d position : animalMap.keySet()) {
            leftBottomCorner = leftBottomCorner.lowerLeft(position);
            rightTopCorner = rightTopCorner.upperRight(position);
        }
    }
    public Vector2d getLeftBottomCorner() {
        return leftBottomCorner;
    }

    public Vector2d getRightTopCorner() {
        return rightTopCorner;
    }

    public void eatGrass(Vector2d position){
        grassMap.remove(position);
        placeGrass(1);
    }
}
