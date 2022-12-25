package agh.ics.oop;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class EarthMap extends AbstractWorldMap{
    public EarthMap(IMapStateEngineObserver observer, DataParameters currentConfig) {
        super(observer, currentConfig);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {

        int y = position.getY();
        int x = position.getX();

        return y>=this.getMapLowerLeft().getY() && y<=this.getMapUpperRight().getY() && x>=this.getMapLowerLeft().getX() && x<=this.getMapUpperRight().getX();
    }

    @Override
    public Vector2d getNewPosition(Vector2d position) {
        int y = position.getY();
        y = min(y, getMapUpperRight().getY());
        y = max(y, getMapLowerLeft().getY());
        if (position.getX() > getMapUpperRight().getX()){
            return new Vector2d(getMapLowerLeft().getX(), y);
        }
        if (position.getX()< getMapLowerLeft().getX()){
            return new Vector2d(getMapUpperRight().getX(),y);
        }
        return null;
    }

}
