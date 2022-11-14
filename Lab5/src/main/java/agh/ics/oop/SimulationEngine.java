package agh.ics.oop;

import agh.ics.oop.entities.Entity;
import agh.ics.oop.models.TexturedModel;
import org.joml.Vector3f;

import java.util.Arrays;
import java.util.List;

public class SimulationEngine implements IEngine{

    private final List <MoveDirection> directions;
    private final IWorldMap map;
    private final Vector2d[] positions;
    private final List<Entity> entities;
    private int directionIndex = 0;
    public SimulationEngine(List<MoveDirection> directions,IWorldMap map, Vector2d[] positions,List<Entity> entities){
        this.directions=directions;
        this.map=map;
        this.positions=positions;
        this.entities=entities;
        addAnimalsToMap();
        System.out.println(map);
    }
    private void addAnimalsToMap(){
        int index=0;
        for(Vector2d position : positions){
            Animal currentEnttity = (Animal)entities.get(index);
            currentEnttity.setPosition(new Vector3f(position.getX()+0.5f,1,position.getY()+0.5f));
            currentEnttity.setRotX(0);
            currentEnttity.setRotY(0);
            currentEnttity.setRotZ(0);
            if(index==0) {
                currentEnttity.setScale(0.02f);
            }else{
                currentEnttity.setScale(0.2f);
            }
            currentEnttity.setPositionOnPlane(position);
            currentEnttity.resetOrientation();
            map.place(currentEnttity);
            index++;
        }
    }
    private void moveAnimals(){
        if(directionIndex>=directions.size()){return;}
        MoveDirection direction = directions.get(directionIndex);
        Animal currentAnimal = (Animal) map.objectAt(positions[directionIndex%entities.size()]);
        if(currentAnimal != null){
            currentAnimal.move(direction);
            positions[directionIndex%entities.size()]=currentAnimal.getPositionOnPlane();
        }
        directionIndex++;
        System.out.println(map);
    }
    public void run(){
        moveAnimals();
    }
}
