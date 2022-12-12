package agh.ics.oop;

import java.util.List;

public interface IMapStateEngineObserver {
    Animal bornEvent(AbstractWorldMap map, Vector2d position, List<Integer> genotype);
}
