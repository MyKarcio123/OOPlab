package agh.ics.oop;

import agh.ics.oop.entities.Entity;
import agh.ics.oop.models.TexturedModel;
import org.joml.Vector3f;

public class Animal extends Entity {
    private MapDirection orientation;
    private Vector2d positionOnPlane;
    static IWorldMap map;

    public Animal(IWorldMap worldMap, TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale){
        super(model, position, rotX, rotY, rotZ, scale);
        orientation = MapDirection.NORTH;
        positionOnPlane = new Vector2d((int) position.x, (int) position.z);
        map = worldMap;
    }
    public Animal(IWorldMap worldMap, TexturedModel model){
        super(model);
        map=worldMap;
        orientation = MapDirection.NORTH;
    }
    public Animal(IWorldMap worldMap, Vector2d positionOnPlane){
        super(positionOnPlane);
        this.positionOnPlane=positionOnPlane;
        map=worldMap;
        orientation = MapDirection.NORTH;
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
        switch (direction) {
            case FORWARD -> {
                Vector2d newPosition = positionOnPlane.add(orientation.toUnitVector());
                if (map.canMoveTo(newPosition)){
                    positionOnPlane = newPosition;
                    setPosition(new Vector3f(newPosition.getX()+0.5f,1,newPosition.getY()+0.5f));
                    map.place(this);
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = positionOnPlane.subtract(orientation.toUnitVector());
                if (map.canMoveTo(newPosition)) {
                    positionOnPlane = newPosition;
                    setPosition(new Vector3f(newPosition.getX()+0.5f,1,newPosition.getY()+0.5f));
                    map.place(this);
                }
            }
            case LEFT -> {
                orientation = orientation.previous();
                setRotY(getRotY()-90);
            }
            case RIGHT -> {
                orientation = orientation.next();
                setRotY(getRotY()+90);
            }
            case NONE -> {}
        }
    }
}
