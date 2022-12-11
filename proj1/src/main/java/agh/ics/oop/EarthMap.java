package agh.ics.oop;

public class EarthMap extends AbstractWorldMap{
    @Override
    public boolean canMoveTo(Vector2d position) {
        int y = position.getY();
        return y>this.getMapLowerLeft().getY() && y<this.getMapLowerLeft().getY();
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d animalPosition = animal.getPosition();
        if (canMoveTo(animalPosition)){
            if (animalPosition.getX()> getMapUpperRight().getX()){
                animalPosition = new Vector2d(getMapLowerLeft().getX(), animalPosition.getY());
            }
            if (animalPosition.getX()< getMapLowerLeft().getX()){
                animalPosition = new Vector2d(getMapUpperRight().getX(), animalPosition.getY());
            }
            animalMap.put(animalPosition, animal);
            return true;
        }
        return false;
    }




}
