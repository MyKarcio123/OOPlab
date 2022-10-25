package agh.ics.oop;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    public Animal(){
        orientation=MapDirection.NORTH;
        position=new Vector2d(2,2);
    }

    @Override
    public String toString() {
        return "Orientacja: " + orientation.toString() + " pozycja: " + position.toString();
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }
    public MapDirection getOrientation(){ return orientation; }
    public Vector2d getPosition(){ return position; }

    private boolean canMoveForward() {
        return ((orientation == MapDirection.NORTH) && (position.getY() < 4)) ||
                ((orientation == MapDirection.EAST) && (position.getX() < 4)) ||
                ((orientation == MapDirection.SOUTH) && (position.getY() > 0)) ||
                ((orientation == MapDirection.WEST) && (position.getX() > 0));
    }

    private boolean canMoveBackward() {
        return ((orientation == MapDirection.NORTH) && (position.getY() > 0)) ||
                ((orientation == MapDirection.EAST) && (position.getX() > 0)) ||
                ((orientation == MapDirection.SOUTH) && (position.getY() < 4)) ||
                ((orientation == MapDirection.WEST) && (position.getX() < 4));
    }

    public void move(MoveDirection direction){
        switch (direction) {
            case FORWARD -> {
                if (canMoveForward()) {
                    position = position.add(orientation.toUnitVector());
                }
            }
            case BACKWARD -> {
                if (canMoveBackward()) {
                    position = position.subtract(orientation.toUnitVector());
                }
            }
            case LEFT -> orientation = orientation.previous();
            case RIGHT -> orientation = orientation.next();
            case NONE -> {}
        }
    }
}
