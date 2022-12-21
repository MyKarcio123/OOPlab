package agh.ics.oop;

import java.util.Set;
import java.util.TreeSet;

public interface IWorldMap {

    boolean canMoveTo(Vector2d position);

    boolean place(Animal animal);

    boolean isOccupied(Vector2d position);

    Set<Animal> objectAt(Vector2d position);

    void clearDeathAnimals();

    int eatGrass();

    void copulateAnimals();

    void plantGrass(int howManyGrassToAdd);

    Vector2d getNewPosition(Vector2d position);
    public Vector2d getMapLowerLeft();
    public Vector2d getMapUpperRight();
}
