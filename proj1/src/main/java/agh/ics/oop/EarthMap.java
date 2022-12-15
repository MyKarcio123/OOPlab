package agh.ics.oop;

public class EarthMap extends AbstractWorldMap{
    protected EarthMap(IMapStateEngineObserver observer) {
        super(observer);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        int y = position.getY();
        return y>this.getMapLowerLeft().getY() && y<this.getMapLowerLeft().getY();
    }

    @Override
    public Vector2d getNewPosition(Vector2d position) {
        if (position.getX() > getMapUpperRight().getX()){
            return new Vector2d(getMapLowerLeft().getX(),position.getY());
        }
        if (position.getX()< getMapLowerLeft().getX()){
            return new Vector2d(getMapUpperRight().getX(),position.getY());
        }
        return null;
    }

}
