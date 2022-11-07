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
    public SimulationEngine(List<MoveDirection> directions,IWorldMap map, Vector2d[] positions,List<Entity> entities){
        this.directions=directions;
        this.map=map;
        this.positions=positions;
        this.entities=entities;
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
                currentEnttity.setScale(0.2f);
            }else{
                currentEnttity.setScale(0.02f);
            }
            currentEnttity.setPositionOnPlane(position);
            currentEnttity.resetOrientation();
            map.place(currentEnttity);
            System.out.println(currentEnttity.getPositionOnPlane());
            index++;
        }
    }
    private int updateAnimalIndex(int animalIndex){
        if(animalIndex+1>=positions.length){
            return 0;
        }else{
            return animalIndex+1;
        }
    }
    private void moveAnimals(){
        int animalIndex=0;
        for(MoveDirection direction : directions){
            Animal currentAnimal = (Animal) map.objectAt(positions[animalIndex]);
            if(currentAnimal != null){
                currentAnimal.move(direction);
                positions[animalIndex]=currentAnimal.getPositionOnPlane();
                animalIndex = updateAnimalIndex(animalIndex);
            }
            System.out.println(map);
        }
    }
    public void run(){
        addAnimalsToMap();
        moveAnimals();
    }
}
