package agh.ics.oop;

public abstract class AbstractMapElement {
    protected Vector2d position;
    public Vector2d getPosition(){return position;}
    public String getDesc(){
        return "Z " + position.toString();
    }
    public String getImagePath(){
        return toString();
    }
}
