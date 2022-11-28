package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GrassField extends AbstractWorldMap{
    private final int grassAmount;
    private final Map<Vector2d,Grass> grassMap = new HashMap<>();

    public GrassField(int amount){
        super(new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE),new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE));
        grassAmount = amount;
        placeGrass(grassAmount);
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
    public boolean isOccupied(Vector2d position){
        return (grassMap.containsKey(position) || super.isOccupied(position));
    }
    public boolean isGrass(Vector2d position){
        return grassMap.containsKey(position);
    }

    public boolean place(Animal animal){
        eatGrass(animal.getPositionOnPlane());
        if(super.place(animal)){
            updateMapSize(animal.getPositionOnPlane());
            return true;
        }
        return false;
    }

    public boolean place(Vector2d grassPosition){
        if(!isOccupied(grassPosition)){
            grassMap.put(grassPosition,new Grass(grassPosition));
            updateMapSize(grassPosition);
            return true;
        }
        return false;
    }
    public Object objectAt(Vector2d position){
        if(super.objectAt(position)==null){
            return grassMap.get(position);
        }
        return super.objectAt(position);
    }
    public boolean canMoveTo(Vector2d position){
        return !super.isOccupied(position);
    }
    public void eatGrass(Vector2d position){
        if(isGrass(position)) {
            grassMap.remove(position);
            removeFromMap(position);
            placeGrass(1);
        }
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        super.positionChanged(oldPosition,newPosition);
        leftBottomCorner = mapBoundary.getLowerLeft();
        rightTopCorner = mapBoundary.getUpperRight();
        eatGrass(newPosition);
        //pamaiętać o grassie
    }
}
