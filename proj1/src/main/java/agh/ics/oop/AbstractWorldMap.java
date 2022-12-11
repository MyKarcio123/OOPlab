package agh.ics.oop;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;

import java.util.*;
import java.util.stream.Stream;




public abstract class AbstractWorldMap implements IWorldMap, IAnimalStateMapObserver {
    protected final SetMultimap<Vector2d, Animal> animalMap;
    protected final Map<Vector2d, Grass> grassMap;
    protected final MapVisualizer mapVisualizer;
    private List<Vector2d> placesOfGrassToBeEaten;
    private List<Vector2d> placesOfCopulation;
    private HashMap<Vector2d, Integer> deathAnimals;
    private Vector2d mapUpperRight;
    private Vector2d mapLowerLeft;


    protected AbstractWorldMap() {
        animalMap = MultimapBuilder.hashKeys().treeSetValues().build();
        grassMap = new HashMap<>();
        mapVisualizer = new MapVisualizer(this);
        placesOfGrassToBeEaten = new LinkedList<>();
        placeInitGrass(1); ///nwm ile mamy kłaść
    }


    //TODO Ogólnie to nie uwzglęniam tuta gdzie jest jaki teren, ale no na razie niech będzie
    private void placeInitGrass(int amount) {
        ArrayList<Vector2d> grassPositions = new ArrayList<>();
        int bound = (int) Math.sqrt(10 * amount);
        for (int i = 0; i < bound; i++) {
            for (int j = 0; j < bound; j++) {
                grassPositions.add(new Vector2d(i, j));
            }
        }

        Random random = new Random();
        for (int i = 0; i < amount; ++i) {
            int randomIndex = random.nextInt(grassPositions.size() - 0) + 0;
            Vector2d grassPosition = grassPositions.get(randomIndex);
            grassMap.put(grassPosition, new Grass(grassPosition));
            grassPositions.remove(grassPosition);
        }

    }

    protected Vector2d getKey(Animal animal) {
        for (Vector2d key : animalMap.keySet()) {
            if (animalMap.get(key).equals(animal)) {
                return key;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return mapVisualizer.draw(this.getMapLowerLeft(), this.getMapUpperRight());
    }



    public Vector2d getMapLowerLeft() {
        return this.mapLowerLeft;
    }

    public Vector2d getMapUpperRight() {
        return this.mapUpperRight;
    }

    public boolean isOccupied(Vector2d position) {
        return (animalMap.containsKey(position));
    }

    public void clearDeathAnimals() {
        for (Map.Entry<Vector2d, Integer> set : deathAnimals.entrySet()) {
            Vector2d pos = set.getKey();
            Integer amt = set.getValue();
            TreeSet<Animal> animals = objectAt(pos);
            for (int i = 0; i < amt; i++) {
                animals.pollLast();
            }
        }
        deathAnimals.clear();
    }

    //Logiczne uzasadnienie czeemu do dieEvent przekazuję tylko współrzędne mimo że pod jedną współrzędną w mapie może być n obiektów.
    //Jest to spowodwane tym, że w mapie zwierząt jest coś ala TreeSet którego pierwszym kryterium jest energia malejąco.
    //Z tego faktu wynika że nie muszę wiedzieć dokładnie które zwierzę umarło ale ile, bo będę od końca usuwał konieczną ilość zwierząt.
    @Override
    public void dieEvent(Vector2d position) {
        if (deathAnimals.containsKey(position)) {
            deathAnimals.replace(position, deathAnimals.get(position) + 1);

        }
        deathAnimals.put(position, 1);
    }

    @Override
    public int eatGrass() {
        int howManyGrassRemoved = 0;
        for (Vector2d pos : placesOfGrassToBeEaten) {
            if (grassMap.containsKey(pos)) {
                TreeSet<Animal> animals = objectAt(pos);
                Animal animal = animals.first();
                animal.gainEnergy();
                grassMap.remove(pos);
                howManyGrassRemoved += 1;
            }
        }
        placesOfGrassToBeEaten.clear();
        return howManyGrassRemoved;
    }

    @Override

    public void copulateAnimals() {
        for (Vector2d pos : placesOfCopulation) {
            TreeSet<Animal> animals = objectAt(pos);
            if (animals.size() > 1) {
                Animal animal1 = animals.first();
                Animal animal2 = null;
                int i = 0;
                for (Animal animal : animals) {
                    if (i == 1) {
                        animal2 = animal;
                        break;
                    }
                    i++;
                }

                if (animal2.canCopulate()) {

                    //TODO powymyślać stałe, bo poniżej wpisałem losowe liczby
                    List<Integer> genotype1 = animal1.copulate(1, 1.0F);
                    List<Integer> genotype2 = animal2.copulate(1,1.0F);
                    List<Integer> genotype = Stream.concat(genotype1.stream(), genotype2.stream()).toList();
                    Animal child = new Animal(this, animal1.getPosition(), genotype, 0, animal1.getStateEngineObserver());
                    this.place(child);
                }
            }
        }
        placesOfCopulation.clear();
    }

    @Override
    public void plantGrass(int howManyGrassToAdd) {
        placeInitGrass(howManyGrassToAdd);
    }


    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, int id) {
        Set<Animal> animals = animalMap.get(oldPosition);
        Animal currentAnimal = null;
        for (Animal animal : animals) {
            if (animal.getID() == id) {
                currentAnimal = animal;
                animals.remove(currentAnimal);
            }
        }
        if (currentAnimal != null) {
            animalMap.put(newPosition, currentAnimal);
        }

    }

    @Override
    public TreeSet objectAt(Vector2d pos){
        return  (TreeSet) animalMap.get(pos);
    }

    @Override
    public boolean place(Animal animal){
        animalMap.put(animal.getPosition(), animal);
        return true;
    }
}
