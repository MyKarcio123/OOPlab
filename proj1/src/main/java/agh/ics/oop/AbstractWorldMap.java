package agh.ics.oop;

import com.google.common.collect.Iterables;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets.SetView;


import java.util.*;
import java.util.stream.Stream;

import static agh.ics.oop.Parameters.*;
import static agh.ics.oop.PlaceInitGrass.placeGrass;
import static agh.ics.oop.RandomPosition.*;


public abstract class AbstractWorldMap implements IWorldMap, IAnimalStateMapObserver {
    protected final SetMultimap<Vector2d, Animal> animalMap;
    protected final Map<Vector2d, Grass> grassMap;
    protected final MapVisualizer mapVisualizer;
    private List<Vector2d> placesOfGrassToBeEaten;
    private Set<Vector2d> placesOfCopulation;
    private HashMap<Vector2d, Integer> deathAnimals;
    private HashMap<Vector2d, Integer> historyOfDeathAnimals;
    private Vector2d mapUpperRight;
    private Vector2d mapLowerLeft = new Vector2d(1, 1);

    private IMapStateEngineObserver observer;


    protected AbstractWorldMap(IMapStateEngineObserver observer) {
        animalMap = MultimapBuilder.hashKeys().treeSetValues().build();
        grassMap = new HashMap<>();
        mapVisualizer = new MapVisualizer(this);
        placesOfGrassToBeEaten = new LinkedList<>();
        deathAnimals = new HashMap<>();

        this.observer = observer;
        if (GRASS_GROW_VARIANT == 1) {
            historyOfDeathAnimals = new HashMap<>();
            for (int i = mapLowerLeft.getX(); i <= mapUpperRight.getX(); i++) {
                for (int j = mapLowerLeft.getY(); j <= mapUpperRight.getY(); j++) {
                    historyOfDeathAnimals.put(new Vector2d(i, j), 0);
                }
            }
        }
        placesOfCopulation = new HashSet<>();
        mapUpperRight = new Vector2d(WIDTH_MAP,HEIGHT_MAP);
        placeInitGrass(STARTING_GRASS);
    }


    private void placeInitGrass(int amount) {
        List<Vector2d> placesOfGrass = placeGrass(this, amount);
        for (Vector2d grassPosition : placesOfGrass) {
            grassMap.put(grassPosition, new Grass(grassPosition));
        }
    }

    public HashMap<Vector2d, Integer> getHistoryOfDeathAnimals() {
        return historyOfDeathAnimals;
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
            historyOfDeathAnimals.replace(pos, historyOfDeathAnimals.get(pos) + amt);
            Set<Animal> animalsSet = objectAt(pos);
            TreeSet<Animal> animals = (TreeSet<Animal>) animalsSet;
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
                SetView<Animal> animalsSet = objectAt(pos);
                Animal animal = Iterables.getFirst(animalsSet, null);
                animal.gainEnergy();
                animal.grassCounter();
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
            SetView<Animal> animalsSet = objectAt(pos);
            if (animalsSet.size() > 1) {
                Animal animal1 = Iterables.getFirst(animalsSet, null);
                Animal animal2 = Iterables.get( animalsSet, 2);

                if (animal2.canCopulate()) {

                    //TODO powymyślać stałe, bo poniżej wpisałem losowe liczby- jakie stałe jak to mają być losowo generowane liczby XD?
                    // ty w ogóle czytałeś te wypociny na gitubie XD poprawiłem trochę tą funkcję bo
                    // 1. nowy animal nie może być Tworzony w mapie, bo Engine go potrzebuje
                    // 2. nie masz licznika dni w tym scope
                    // TODO piszę wszystko w todo bo ładny kolorek ma

                    //TODO czytałem, ale zapomniałem pusha zrobić przed kościołem i jakieś starocie były w tym miejscu

                    //krok 1 - losowanie strony
                    int mutationSite = getBinaryDigit();

                    // krok 2 - zbieranie genów
                    List<Integer> genotype1 = new ArrayList<>();
                    List<Integer> genotype2 = new ArrayList<>();
                    int sumOfEnergies = animal1.getEnergy() + animal2.getEnergy();
                    if (mutationSite == 0) {
                        genotype1 = animal2.copulate(1, animal2.getEnergy() / sumOfEnergies);
                        genotype2 = animal1.copulate(0, animal1.getEnergy() / sumOfEnergies);
                    } else {
                        genotype1 = animal1.copulate(1, animal1.getEnergy() / sumOfEnergies);
                        genotype2 = animal2.copulate(0, animal2.getEnergy() / sumOfEnergies);
                    }
                    List<Integer> genotype = Stream.concat(genotype1.stream(), genotype2.stream()).toList();

                    //krok 3- robienie mutacji na genotypie
                    List<Integer> indexesOfGenesToChange = getListOfIndexesOfGenesToChange(genotype.size());
                    if (MUTATION_VARIANT == 0) {
                        for (Integer index : indexesOfGenesToChange) {
                            genotype.set(index, getRandomGene());
                        }
                    } else if (MUTATION_VARIANT == 1) {
                        for (Integer index : indexesOfGenesToChange) {
                            int geneChange;
                            if (getBinaryDigit() == 0) {
                                geneChange = -1;
                            } else {
                                geneChange = 1;
                            }
                            genotype.set(index, genotype.get(index) + geneChange);
                        }
                    }

                    //krok 4 - zrobienie dziecka
                    Animal child = observer.bornEvent(this, animal1.getPosition(), genotype);
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
    public Vector2d positionChanged(Vector2d oldPosition, Vector2d newPosition, int id) {
        Set<Animal> animals = animalMap.get(oldPosition);
        Animal currentAnimal = null;
        for (Animal animal : animals) {
            if (animal.getID() == id) {
                currentAnimal = animal;
                animals.remove(currentAnimal);
            }
        }
        if (currentAnimal != null) {
            if (canMoveTo(newPosition)) {
                animalMap.put(newPosition, currentAnimal);
                if (animalMap.get(newPosition).size() == 2) {placesOfCopulation.add(newPosition);}
                return newPosition;
            } else {
                newPosition = getNewPosition(newPosition);
                if (newPosition == null) {
                    if (animalMap.get(oldPosition).size() >= 2) {placesOfCopulation.add(oldPosition);}
                    return oldPosition;
                }
                if (animalMap.get(newPosition).size() == 2) {placesOfCopulation.add(newPosition);}
                return newPosition;
            }
        }
        if (animalMap.get(oldPosition).size() >= 2) {placesOfCopulation.add(oldPosition);}
        return oldPosition;
    }

    @Override
    public SetView<Animal> objectAt(Vector2d pos) {
        return (SetView<Animal>) animalMap.get(pos);
    }

    @Override
    public boolean place(Animal animal) {
        animalMap.put(animal.getPosition(), animal);
        return true;
    }
}
