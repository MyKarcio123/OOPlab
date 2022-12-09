package agh.ics.oop;

public class AnimalData {
    private final Vector2d position;
    private final int ID;

    public AnimalData(Vector2d position,int ID){
        this.position=position;
        this.ID = ID;
    }

    public Vector2d getPosition() {
        return position;
    }

    public int getID() {
        return ID;
    }
}
