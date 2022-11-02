package agh.ics.oop;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    static IWorldMap map;

    public Animal(IWorldMap worldMap,Vector2d initialPosition){
        orientation = MapDirection.NORTH;
        position = initialPosition;
        map = worldMap;
    }

    @Override
    public String toString() {
        return Character.toString(orientation.name().charAt(0));
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }
    public MapDirection getOrientation(){ return orientation; }
    public Vector2d getPosition(){ return position; }

    public void move(MoveDirection direction){
        switch (direction) {
            case FORWARD -> {
                Vector2d newPosition = position.add(orientation.toUnitVector());
                if (map.canMoveTo(newPosition)){
                    position = newPosition;
                    map.place(this);
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = position.subtract(orientation.toUnitVector());
                if (map.canMoveTo(newPosition)) {
                    position = newPosition;
                    map.place(this);
                }
            }
            case LEFT -> {
                orientation = orientation.previous();
            }
            case RIGHT -> {
                orientation = orientation.next();
            }
            case NONE -> {}
        }
    }
}
