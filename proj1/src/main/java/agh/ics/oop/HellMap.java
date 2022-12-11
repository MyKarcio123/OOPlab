package agh.ics.oop;

import java.util.Random;

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
            Random random = new Random();
            animalPosition = new Vector2d(random.nextInt(getMapLowerLeft().getX(), getMapUpperRight().getX()),
                                            random.nextInt(getMapLowerLeft().getY(), getMapUpperRight().getY()));
        }
        animalMap.put(animalPosition, animal);
        return true;

    }

}
