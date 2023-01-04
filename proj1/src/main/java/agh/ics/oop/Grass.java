package agh.ics.oop;

public class Grass extends AbstractMapElement{

    public Grass(Vector2d position){
        this.position = position;
    }

    @Override
    public String getDesc(){
        return "T"+this.position.toString();
    }
}
