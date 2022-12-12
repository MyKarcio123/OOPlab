package agh.ics.oop;

import java.util.List;

public interface IAnimalStateMapObserver {
    Vector2d positionChanged(Vector2d oldPosition, Vector2d newPosition, int id);
    void dieEvent(Vector2d position);
}
