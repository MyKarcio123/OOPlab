package agh.ics.oop;

import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{
    TreeSet<Vector2d> X = new TreeSet<>(Comparator.comparing(Vector2d::getX));
    TreeSet<Vector2d> Y = new TreeSet<>(Comparator.comparing(Vector2d::getY));

    public void updateMapBoundary(Vector2d v){
        X.add(v);
        Y.add(v);
    }
    public void removeMapBoundary(Vector2d v){
        X.remove(v);
        Y.remove(v);
    }
    public Vector2d getLowerLeft(){
        return new Vector2d(X.first().getX(),Y.first().getY());
    }
    public Vector2d getUpperRight(){
        return new Vector2d(X.last().getX(),Y.last().getY());
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        X.remove(oldPosition);
        Y.remove(oldPosition);
        X.add(newPosition);
        Y.add(newPosition);
    }
}
