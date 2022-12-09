package agh.ics.oop;
import javafx.util.Pair;

public class EarthMap extends AbstractWorldMap{
    @Override
    public boolean canMoveTo(Vector2d position) {
        int y = position.getY();
        return y>this.getMapLowerLeft().getY() && y<this.getMapLowerLeft().getY();
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            //if na animal.getPosition() jest inny wierzak to wywołujemy na nim reproduct(animal, otherAnimal)
            //if trawa to jedzonko
            animalMap.put(new Pair<>(animal.getPosition(),getAnimalIndex(animal)), animal);
            return true;
        }
        return false;
    }

    private void reproduct(Animal animal1, Animal animal2){

    }
    @Override
    public Object objectAt(Vector2d position) {
        return null;
    }
}
