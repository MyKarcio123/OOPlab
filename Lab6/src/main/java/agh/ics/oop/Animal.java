package agh.ics.oop;

import agh.ics.oop.entities.Entity;
import agh.ics.oop.models.TexturedModel;
import agh.ics.oop.renderEngine.AnimationController;
import agh.ics.oop.renderEngine.Game;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Animal extends Entity{
    private MapDirection orientation;
    private Vector2d positionOnPlane;
    private IWorldMap map;
    private List<IPositionChangeObserver> observers = new ArrayList<>();


    public Animal(IWorldMap worldMap, TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale){
        super(model, position, rotX, rotY, rotZ, scale);
        orientation = MapDirection.NORTH;
        positionOnPlane = new Vector2d((int) position.x, (int) position.z);
        map = worldMap;
        addObserver((IPositionChangeObserver) map);
    }
    public Animal(IWorldMap worldMap, TexturedModel model){
        super(model);
        map=worldMap;
        orientation = MapDirection.NORTH;
        addObserver((IPositionChangeObserver) map);
    }
    public Animal(IWorldMap worldMap, Vector2d positionOnPlane){
        super(positionOnPlane);
        this.positionOnPlane=positionOnPlane;
        map=worldMap;
        addObserver((IPositionChangeObserver) map);
    }
    @Override
    public String toString() {
        return Character.toString(orientation.name().charAt(0));
    }

    public boolean isAt(Vector2d position){
        return this.positionOnPlane.equals(position);
    }
    public MapDirection getOrientation(){ return orientation; }
    public void setPositionOnPlane(Vector2d positionOnPlane){
        this.positionOnPlane = positionOnPlane;
    }
    public void resetOrientation(){
        orientation = MapDirection.NORTH;
    }
    public Vector2d getPositionOnPlane(){ return positionOnPlane; }

    public void move(MoveDirection direction){

        Vector2d newPosition = positionOnPlane;
        Vector3f dest = getPosition();
        Vector3f rot = getRotation();

        switch (direction) {
            case FORWARD -> {
                newPosition = positionOnPlane.add(orientation.toUnitVector());
                dest = new Vector3f(newPosition.getX()+0.5f,1,newPosition.getY()+0.5f);
            }
            case BACKWARD -> {
                newPosition = positionOnPlane.subtract(orientation.toUnitVector());
                dest = new Vector3f(newPosition.getX()+0.5f,1,newPosition.getY()+0.5f);
            }
            case LEFT -> {
                orientation = orientation.previous();
                rot = new Vector3f(getRotX(),getRotY()-90,getRotX());
                Game.animationController.makeAnimation(this,dest,rot,1);
            }
            case RIGHT -> {
                orientation = orientation.next();
                rot = new Vector3f(getRotX(),getRotY()+90,getRotX());
                Game.animationController.makeAnimation(this,dest,rot,1);
            }
            case NONE -> {}
        }

        if (map.canMoveTo(newPosition)){
            positionChanged(positionOnPlane, newPosition);
            Game.animationController.makeAnimation(this,dest,rot,1);
            this.positionOnPlane = newPosition;
        }
    }

    void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }
    void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }
    void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for (IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition, newPosition);
        }
    }
}
