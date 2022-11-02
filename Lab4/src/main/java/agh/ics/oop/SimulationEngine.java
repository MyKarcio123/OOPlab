package agh.ics.oop;

import java.util.List;

public class SimulationEngine implements IEngine{

    private final List <MoveDirection> directions;
    private final IWorldMap map;
    private final Vector2d[] positions;

    public SimulationEngine(List<MoveDirection> directions,IWorldMap map, Vector2d[] positions){
        this.directions=directions;
        this.map=map;
        this.positions=positions;
    }
    private void addAnimalsToMap(){
        for(Vector2d position : positions){
            map.place(new Animal(map,position));
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
                positions[animalIndex]=currentAnimal.getPosition();
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
