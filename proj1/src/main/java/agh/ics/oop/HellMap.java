package agh.ics.oop;

public class HellMap extends AbstractWorldMap {
    @Override
    public boolean canMoveTo(Vector2d position) {
        return true;
    }

    private boolean outsideOfMap(Vector2d position) {
        return (position.getX() > getMapUpperRight().getX()
                || position.getX() < getMapLowerLeft().getX()
                || position.getY() > getMapUpperRight().getY()
                || position.getY() < getMapLowerLeft().getY());
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d animalPosition = animal.getPosition();
        if (outsideOfMap(animalPosition)) {
            Vector2d newPosition = RandomPosition.getRandomPosition(getMapLowerLeft(),getMapUpperRight());
            animal.setPosition(newPosition);
        }
        return super.place(animal);

    }

}
