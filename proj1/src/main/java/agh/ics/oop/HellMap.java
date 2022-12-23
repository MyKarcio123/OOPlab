package agh.ics.oop;

public class HellMap extends AbstractWorldMap {
    protected HellMap(IMapStateEngineObserver observer, DataParameters currentConfig) {
        super(observer, currentConfig);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (position.getX() > getMapUpperRight().getX()
                || position.getX() < getMapLowerLeft().getX()
                || position.getY() > getMapUpperRight().getY()
                || position.getY() < getMapLowerLeft().getY());
    }

    @Override
    public Vector2d getNewPosition(Vector2d position) {
        return RandomPosition.getRandomPosition(getMapLowerLeft(),getMapUpperRight()); //niemoże być to samo miejsce losowane
    }

}
