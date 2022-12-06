package agh.ics.oop;

public class Grass implements IMapElement{
    private Vector2d positionOnPlane;

    public Grass(Vector2d position){
        positionOnPlane = position;
    }
    public Vector2d getPositionOnPlane(){
        return positionOnPlane;
    }
    @Override
    public String toString(){
        return "*";
    }

    public String getImagePath(){
        return "grass";
    }
    public String getDesc(){
        return "Trawa";
    }
}
