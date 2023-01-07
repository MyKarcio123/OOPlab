package agh.ics.oop;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;


import java.util.*;
import java.util.stream.Stream;

import static agh.ics.oop.PlaceInitGrass.placeGrass;
import static agh.ics.oop.RandomPosition.*;


public abstract class AbstractWorldMap implements IWorldMap, IAnimalStateMapObserver {
    protected final SetMultimap<Vector2d, Animal> animalMap;
    protected final Map<Vector2d, Grass> grassMap;
    protected final MapVisualizer mapVisualizer;
    private final Set<Vector2d> placesOfGrassToBeEaten;
    private final Set<Vector2d> placesOfCopulation;
    private final HashMap<Vector2d, Integer> deathAnimals;
    private HashMap<Vector2d, Integer> historyOfDeathAnimals = new HashMap<>();
    private HashMap<List<Integer>, Integer> popularGenotypes = new HashMap<>();
    private final Vector2d mapUpperRight;
    private final Vector2d mapLowerLeft = new Vector2d(1, 1);
    private int howManydied = 0;
    private int sumOfDiedAge = 0;

    private Animal animalToShow = null;
    public Animal getAnimalToShow() {
        return animalToShow;
    }
    @Override
    public void setAnimalToShow(Animal animal) {
        this.animalToShow = animal;
    }

    private final IMapStateEngineObserver observer;
    private final DataParameters dataParameters;

    public Biomes getBiomes() {
        return biomes;
    }

    protected Biomes biomes;


    protected AbstractWorldMap(IMapStateEngineObserver observer, DataParameters currentConfig) {
        animalMap = MultimapBuilder.hashKeys().treeSetValues().build();
        grassMap = new HashMap<>();
        mapVisualizer = new MapVisualizer(this);
        placesOfGrassToBeEaten = new HashSet<>();
        deathAnimals = new HashMap<>();
        dataParameters = currentConfig;
        mapUpperRight = new Vector2d(dataParameters.getWidth(), dataParameters.getHeight());

        this.observer = observer;
        if (dataParameters.getGrassGrowVariant() == 1) {
            historyOfDeathAnimals = new HashMap<>();
            for (int i = mapLowerLeft.getX(); i <= mapUpperRight.getX(); i++) {
                for (int j = mapLowerLeft.getY(); j <= mapUpperRight.getY(); j++) {
                    historyOfDeathAnimals.put(new Vector2d(i, j), 0);
                }
            }
        }else if(dataParameters.getGrassGrowVariant() == 2){
            NoiseData temperature = new NoiseData(dataParameters.getWidth(),dataParameters.getHeight(),0.1,0.09,8,1);
            NoiseData rainfall = new NoiseData(dataParameters.getWidth(),dataParameters.getHeight(),0.3,0.2,8,1);
            biomes = new Biomes(temperature,rainfall,0,4);
        }
        placesOfCopulation = new HashSet<>();

        placeInitGrass(dataParameters.getStartingGrass());
    }

    public DataParameters getDataParameters() {
        return this.dataParameters;
    }

    private void placeInitGrass(int amount) {
        List<Vector2d> placesOfGrass = placeGrass(this, amount, dataParameters.getGrassGrowVariant());
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
            Integer histAmt = historyOfDeathAnimals.get(pos);
            if (histAmt == null) {
                historyOfDeathAnimals.put(pos, amt);
            } else {
                historyOfDeathAnimals.replace(pos, histAmt + amt);
            }
            NavigableSet<Animal> animals = objectAt(pos);
            for (int i = 0; i < amt; i++) {
                animals.pollLast();
            }
        }
        deathAnimals.clear();
    }

    public boolean isOccupiedByGrass(Vector2d position) {
        return (grassMap.containsKey(position));
    }

    //Logiczne uzasadnienie czeemu do dieEvent przekazuję tylko współrzędne mimo że pod jedną współrzędną w mapie może być n obiektów.
    //Jest to spowodwane tym, że w mapie zwierząt jest coś ala TreeSet którego pierwszym kryterium jest energia malejąco.
    //Z tego faktu wynika że nie muszę wiedzieć dokładnie które zwierzę umarło ale ile, bo będę od końca usuwał konieczną ilość zwierząt.
    @Override
    public void dieEvent(Vector2d position, int day) {
        howManydied+=1;
        sumOfDiedAge += day;
        if (deathAnimals.containsKey(position)) {
            deathAnimals.merge(position,1,Integer::sum);
        }else {
            deathAnimals.put(position, 1);
        }
    }


    public int getNumberOfAnimals(){
        return animalMap.size();
    }

    public int getAmountOfGrass(){
        return grassMap.size();
    }

    public Integer getAmountOfAnimalsDead(){
        return  historyOfDeathAnimals.values().stream().reduce(0, Integer::sum);
    }

    public Integer getFreePlaces(){
        int width = mapUpperRight.getX() - mapLowerLeft.getX();
        int height = mapUpperRight.getY() - mapLowerLeft.getY();
        return width*height - animalMap.size() - grassMap.size();
    }

    public List<Integer> getPopularGenotype(){
        popularGenotypes.clear();
        for(Animal animal : animalMap.values()){
            List<Integer> genotype = animal.getGenotype();
            if (popularGenotypes.containsKey(genotype)) {
                popularGenotypes.merge(genotype,1,Integer::sum);
            }else {
                popularGenotypes.put(genotype, 1);
            }
        }
        Integer maxx = 0;
        List<Integer> output = new ArrayList<>();
        for (List<Integer> genotype : popularGenotypes.keySet()){
            if (popularGenotypes.get(genotype)>maxx){
                maxx = popularGenotypes.get(genotype);
                output = genotype;
            }
        }
        return output;
    }

    public Float getAverageEnergy(){
        int sumOfEnergy = 0;
        for (Animal animal : animalMap.values()){
            sumOfEnergy += animal.getEnergy();
        }
        return sumOfEnergy/ (float) animalMap.size();
    }

    public Float getAverageLifeTime(){
        return sumOfDiedAge/(float) howManydied;
    }

    @Override
    public int eatGrass() {
        int howManyGrassRemoved = 0;
        for (Vector2d pos : placesOfGrassToBeEaten) {
            if (grassMap.containsKey(pos)) {
                NavigableSet<Animal> animalsSet = objectAt(pos);
                Animal animal = animalsSet.first();
                animal.gainEnergy();
                animal.grassCounter();
                grassMap.remove(pos);
                howManyGrassRemoved += 1;
            }
        }
        placesOfGrassToBeEaten.clear();
        return howManyGrassRemoved;
    }

    public Map<Vector2d, Grass> getGrassMap() {
        return grassMap;
    }

    @Override
    public void copulateAnimals() {
        for (Vector2d pos : placesOfCopulation) {
            NavigableSet<Animal> animalsSet = objectAt(pos);
            if (animalsSet.size() > 1) {
                Animal animal1 = animalsSet.pollFirst();
                Animal animal2 = animalsSet.pollFirst();
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
                    List<Integer> genotype1;
                    List<Integer> genotype2;
                    int sumOfEnergies = animal1.getEnergy() + animal2.getEnergy();
                    int genotypeSize = animal1.getGenotypeSize();
                    int infiriorGenotypeSize = 0;
                    if (animal1.getEnergy() > animal2.getEnergy()) {
                        infiriorGenotypeSize =  animal2.getEnergy() / sumOfEnergies;
                    } else {
                        infiriorGenotypeSize =  animal1.getEnergy() / sumOfEnergies;
                    }
                    int superiorGenotypeSize = genotypeSize - infiriorGenotypeSize;
                    if (mutationSite == 0) {
                        if (animal1.getEnergy() > animal2.getEnergy()) {
                            genotype1 = animal2.copulate(1, superiorGenotypeSize);
                            genotype2 = animal1.copulate(0, infiriorGenotypeSize);
                        } else {
                            genotype1 = animal2.copulate(1, infiriorGenotypeSize);
                            genotype2 = animal1.copulate(0, superiorGenotypeSize);
                        }
                    } else {
                        if (animal1.getEnergy() > animal2.getEnergy()) {
                            genotype1 = animal1.copulate(1, superiorGenotypeSize);
                            genotype2 = animal2.copulate(0, infiriorGenotypeSize);
                        } else {
                            genotype1 = animal1.copulate(1, infiriorGenotypeSize);
                            genotype2 = animal2.copulate(0, superiorGenotypeSize);
                        }

                    }
                    List<Integer> genotype = new ArrayList<>(Stream.concat(genotype1.stream(), genotype2.stream()).toList());

                    //krok 3- robienie mutacji na genotypie

                    List<Integer> indexesOfGenesToChange = getListOfIndexesOfGenesToChange(genotype.size());
                    if (dataParameters.getMutationVariant() == 0) {
                        for (Integer index : indexesOfGenesToChange) {
                            genotype.set(index, getRandomGene());
                        }
                    } else if (dataParameters.getMutationVariant() == 1) {
                        for (Integer index : indexesOfGenesToChange) {
                            int geneChange;
                            if (getBinaryDigit() == 0) {
                                geneChange = -1;
                            } else {
                                geneChange = 1;
                            }
                            int newGene = genotype.get(index) + geneChange;
                            if (newGene < 0) newGene = 7;
                            if (newGene > 7) newGene = 0;
                            genotype.set(index, newGene);
                        }
                    }


                    //krok 4 - zrobienie dziecka
                    Animal child = observer.bornEvent(this, pos, genotype);
                    animalMap.put(pos, child);
                }
                animalMap.put(pos, animal1);
                animalMap.put(pos, animal2);
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
        NavigableSet<Animal> animals = objectAt(oldPosition);
        Animal currentAnimal = null;
        for (Animal animal : animals) {
            if (animal.getID() == id) {
                currentAnimal = animal;
                break;
            }
        }

        //do końca życia już tego nie zapomne
        Animal finalCurrentAnimal = currentAnimal;
        animals.removeIf(animal -> animal.getID() == finalCurrentAnimal.getID());

        if (canMoveTo(newPosition)) {
            currentAnimal.lowerEnergy(newPosition);
            animalMap.put(newPosition, currentAnimal);
        } else {
            newPosition = getNewPosition(newPosition);
            if (newPosition == null) {
                if (animalMap.get(oldPosition).size() >= 2) {
                    placesOfCopulation.add(oldPosition);
                }
                currentAnimal.lowerEnergy(oldPosition);
                animalMap.put(oldPosition, currentAnimal);
                return oldPosition;
            }
            currentAnimal.lowerEnergy(newPosition);
            animalMap.put(newPosition, currentAnimal);
        }
        if (animalMap.get(newPosition).size() == 2) {
            placesOfCopulation.add(newPosition);
        }
        if (grassMap.containsKey(newPosition)) {
            placesOfGrassToBeEaten.add(newPosition);
        }
        return newPosition;
    }

    @Override
    public NavigableSet<Animal> objectAt(Vector2d pos) {
        return (NavigableSet<Animal>) animalMap.get(pos);
    }

    @Override
    public boolean place(Animal animal) {
        animalMap.put(animal.getPosition(), animal);
        return true;
    }
    public BiomeType getBiomeFromMap(Vector2d pos){
        return biomes.getBiomeTypeAt(pos);
    }

    public HashMap<Vector2d, Integer> getDeathAnimals(){
        return deathAnimals;
    }

    public Set<Vector2d> getPlacesOfCopulation(){
        return placesOfCopulation;
    }

    public SetMultimap<Vector2d, Animal> getAnimalMap(){
        return animalMap;
    }
}
