package agh.ics.oop;

public abstract class AbstractMapElement {
    protected Vector2d position;
    protected MapDirection orientation;
    private boolean clicked = false;
    public Vector2d getPosition(){return position;}
    public String getDesc(){
        return "Z " + position.toString() + " " + orientation.toString();
    }
    public String getImagePath(){
        return toString();
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public void setAnimalToFollow(boolean bool){

    }
}
