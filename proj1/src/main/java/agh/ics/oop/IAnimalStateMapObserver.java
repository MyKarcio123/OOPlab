package agh.ics.oop;

public interface IAnimalStateMapObserver {
    void positionChanged(Vector2d oldPosition, Vector2d newPosition, int id);
    void dieEvent(Vector2d position);
}
