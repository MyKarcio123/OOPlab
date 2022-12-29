package agh.ics.oop;

public abstract class AbstractMapElement {
    protected Vector2d position;
    private boolean clicked = false;
    public Vector2d getPosition(){return position;}
    public String getDesc(){
        return "Z " + position.toString();
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
}
