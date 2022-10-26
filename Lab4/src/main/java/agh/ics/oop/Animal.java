package agh.ics.oop;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    static IWorldMap worldMap;

    public Animal(IWorldMap map,Vector2d initialPosition) throws IllegalAccessException {
        if(!map.isOccupied(initialPosition)) {
            orientation = MapDirection.NORTH;
            position = initialPosition;
            worldMap = map;
        }else{
            throw new IllegalAccessException("In this position there is an animal"); // nie robić w konstruktorze tylko w obiekcie tworzącym animala
        }
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
                if (worldMap.canMoveTo(newPosition)){
                    position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = position.subtract(orientation.toUnitVector());
                if (worldMap.canMoveTo(newPosition)) {
                    position = newPosition;
                }
            }
            case LEFT -> orientation = orientation.previous();
            case RIGHT -> orientation = orientation.next();
            case NONE -> {}
        }
    }
}
