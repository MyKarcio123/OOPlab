package agh.ics.oop;

public class EarthMap extends AbstractWorldMap{
    @Override
    public boolean canMoveTo(Vector2d position) {
        int y = position.getY();
        return y>this.getMapLowerLeft().getY() && y<this.getMapLowerLeft().getY();
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animalMap.put(animal.getPosition(), animal);
            return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return null;
    }


}
