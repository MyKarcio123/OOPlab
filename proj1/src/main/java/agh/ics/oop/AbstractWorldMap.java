package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap {
    protected final Map<Vector2d, Animal> animalMap;

    protected AbstractWorldMap() {
        animalMap = new HashMap<>();
    }

    public AbstactMapElement objectAt(Vector2d position) {
        return animalMap.get(position);
    }

    public abstract Vector2d getMapLowerLeft();

    public abstract Vector2d getMapUpperRight();
}
