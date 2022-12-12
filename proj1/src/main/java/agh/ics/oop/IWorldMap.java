package agh.ics.oop;

import java.util.TreeSet;

public interface IWorldMap {

    boolean canMoveTo(Vector2d position);

    boolean place(Animal animal);

    boolean isOccupied(Vector2d position);

    TreeSet objectAt(Vector2d position);

    void clearDeathAnimals();

    int eatGrass();

    void copulateAnimals();

    void plantGrass(int howManyGrassToAdd);

    Vector2d getNewPosition(Vector2d position);
}
