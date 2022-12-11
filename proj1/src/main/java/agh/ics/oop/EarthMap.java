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
                animal.setPosition(new Vector2d(getMapLowerLeft().getX(), animalPosition.getY()));
            }
            if (animalPosition.getX()< getMapLowerLeft().getX()){
                animal.setPosition(new Vector2d(getMapUpperRight().getX(), animalPosition.getY()));
            }
        }
        return super.place(animal);
    }




}
