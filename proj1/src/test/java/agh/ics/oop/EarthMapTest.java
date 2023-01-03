package agh.ics.oop;
import com.google.common.collect.SetMultimap;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class EarthMapTest {
    String[] config1 = {"Test1", "10", "10", "50", "20", "5", "5", "100", "70", "25", "5", "15", "8", "0", "0", "0", "0"};
    DataParameters data1;
    SimulationEngine engine1;
    AbstractWorldMap map1;
    Animal animal1E, animal2E, animal3E, animal4E, animal5E;

    void createConfigs(){
        List<String> test1 = new ArrayList<>();
        Collections.addAll(test1, config1);
        data1 = new DataParameters(test1);
        engine1 = new SimulationEngine(data1);
        map1 = new EarthMap(engine1, data1);
        animal1E = new Animal(map1, new Vector2d(2,4), generateGenotype(), 0, engine1);
        animal2E = new Animal(map1, new Vector2d(2,4), generateGenotype(), 0, engine1);
        animal3E = new Animal(map1, new Vector2d(4,5), generateGenotype(), 0, engine1);
        animal4E = new Animal(map1, new Vector2d(7,1), generateGenotype(), 0, engine1);
        animal5E = new Animal(map1, new Vector2d(10,3), generateGenotype(), 0, engine1);
        map1.place(animal1E);
        map1.place(animal2E);
        map1.place(animal3E);
        map1.place(animal4E);
        map1.place(animal5E);
    }

    List<Integer> generateGenotype() {
        List<Integer> genotype = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 8; ++i) {
            genotype.add(random.nextInt(0, 8));
        }
        return genotype;
    }
    @Test
    void canMoveToTest(){
        createConfigs();
        assertTrue(map1.canMoveTo(new Vector2d(10,2)));
        assertTrue(map1.canMoveTo(new Vector2d(5,6)));
        assertTrue(map1.canMoveTo(new Vector2d(10,10)));
        assertTrue(map1.canMoveTo(new Vector2d(1,1)));
        assertTrue(map1.canMoveTo(new Vector2d(7,8)));

        assertFalse(map1.canMoveTo(new Vector2d(11,2)));
        assertFalse(map1.canMoveTo(new Vector2d(11,11)));
        assertFalse(map1.canMoveTo(new Vector2d(11,-1)));
        assertFalse(map1.canMoveTo(new Vector2d(-12,-1)));
        assertFalse(map1.canMoveTo(new Vector2d(0,2)));
    }

    @Test
    void getNewPositionTest(){
        createConfigs();
        assertEquals(new Vector2d(10,2), map1.getNewPosition(new Vector2d(-1,2)));
        assertEquals(new Vector2d(1,10), map1.getNewPosition(new Vector2d(11,11)));
        assertNull(map1.getNewPosition(new Vector2d(1, -1)));
        assertNull(map1.getNewPosition(new Vector2d(3, 3)));
        assertEquals(new Vector2d(1,7), map1.getNewPosition(new Vector2d(13,7)));
        assertEquals(new Vector2d(10, 5), map1.getNewPosition(new Vector2d(-5, 5)));
        assertNull(map1.getNewPosition(new Vector2d(5, 12)));
        assertNull(map1.getNewPosition(new Vector2d(5, 5)));
    }

    @Test
    void isOccupiedTest(){
        createConfigs();
        assertTrue(map1.isOccupied(new Vector2d(2,4)));
        assertTrue(map1.isOccupied(new Vector2d(10,3)));
        assertFalse(map1.isOccupied(new Vector2d(1,1)));
        assertFalse(map1.isOccupied(new Vector2d(-1,-1)));

    }

    @Test
    void clearDeathAnimalsTest(){
        createConfigs();
        HashMap<Vector2d, Integer> deadAnimals = map1.getDeathAnimals();

        assertEquals(0, deadAnimals.size());
        map1.dieEvent(animal1E.getPosition());

        assertEquals(1, deadAnimals.size());
        map1.dieEvent(animal3E.getPosition());

        assertEquals(2, deadAnimals.size());
        map1.dieEvent(animal2E.getPosition());

        assertTrue(deadAnimals.get(animal1E.getPosition()) == 2 && deadAnimals.get(animal3E.getPosition()) == 1);

        map1.clearDeathAnimals();
        assertTrue(deadAnimals.isEmpty());
    }

    @Test
    void isOccupiedByGrassTest(){
        createConfigs();
        Map<Vector2d, Grass> grassMap = map1.getGrassMap();

        if (grassMap.containsKey(animal1E.getPosition())){
            assertTrue(map1.isOccupiedByGrass(animal1E.getPosition()));
        } else{
            assertFalse(map1.isOccupiedByGrass(animal1E.getPosition()));
        }

        if (grassMap.containsKey(animal3E.getPosition())){
            assertTrue(map1.isOccupiedByGrass(animal3E.getPosition()));
        } else{
            assertFalse(map1.isOccupiedByGrass(animal3E.getPosition()));
        }

        if (grassMap.containsKey(animal4E.getPosition())){
            assertTrue(map1.isOccupiedByGrass(animal4E.getPosition()));
        } else{
            assertFalse(map1.isOccupiedByGrass(animal4E.getPosition()));
        }

        if (grassMap.containsKey(animal5E.getPosition())){
            assertTrue(map1.isOccupiedByGrass(animal5E.getPosition()));
        } else{
            assertFalse(map1.isOccupiedByGrass(animal5E.getPosition()));
        }
    }

    @Test
    void dieEventTest(){
        createConfigs();
        HashMap<Vector2d, Integer> deadAnimals = map1.getDeathAnimals();

        assertEquals(0, deadAnimals.size());
        map1.dieEvent(animal1E.getPosition());

        assertEquals(1, deadAnimals.size());
        map1.dieEvent(animal3E.getPosition());

        assertEquals(2, deadAnimals.size());
        map1.dieEvent(animal2E.getPosition());

        assertTrue(deadAnimals.get(animal1E.getPosition()) == 2 && deadAnimals.get(animal3E.getPosition()) == 1);
    }

    @Test
    void eatGrassTest(){
        createConfigs();
        int grassToEat = 0;
        Map<Vector2d, Grass> grassMap = map1.getGrassMap();

        if (grassMap.containsKey(animal1E.getPosition())) {
            map1.positionChanged(animal1E.getPosition(), animal1E.getPosition(), animal1E.getID());
            grassToEat++;
        }

        if (grassMap.containsKey(animal3E.getPosition())) {
            map1.positionChanged(animal3E.getPosition(), animal3E.getPosition(), animal3E.getID());
            grassToEat++;
        }

        if (grassMap.containsKey(animal4E.getPosition())) {
            map1.positionChanged(animal4E.getPosition(), animal4E.getPosition(), animal4E.getID());
            grassToEat++;
        }

        if (grassMap.containsKey(animal5E.getPosition())) {
            map1.positionChanged(animal5E.getPosition(), animal5E.getPosition(), animal5E.getID());
            grassToEat++;
        }

        assertEquals(grassToEat, map1.eatGrass());
    }

    @Test
    void copulateAnimalsTest(){
        createConfigs();
        SetMultimap<Vector2d, Animal> animals = map1.getAnimalMap();
        Set<Vector2d> copulations = map1.getPlacesOfCopulation();

        assertEquals(5, animals.size());
        assertEquals(0, copulations.size());

        map1.positionChanged(animal1E.getPosition(), animal1E.getPosition(), animal1E.getID());
        map1.positionChanged(animal2E.getPosition(), animal2E.getPosition(), animal2E.getID());
        assertEquals(1, copulations.size());
        map1.copulateAnimals();

        assertEquals(6, animals.size());
        assertEquals(0, copulations.size());
    }

    @Test
    void plantGrassTest(){
        // Może będzie lepszy, jeżeli jeszcze jutro rano albo dzisiaj wieczorem dam radę coś ogarnąć
        createConfigs();
        Map<Vector2d, Grass> grassMap = map1.getGrassMap();
        assertEquals(50, grassMap.size());

    }

    @Test
    void positionChangedTest(){
        createConfigs();
        Map<Vector2d, Grass> grassMap = map1.getGrassMap();
        int grassToEat = 0;

        if (grassMap.containsKey(new Vector2d(1,4))) grassToEat++;
        animal1E.setPosition(map1.positionChanged(animal1E.getPosition(), new Vector2d(1,4), animal1E.getID()));

        if (grassMap.containsKey(new Vector2d(2,3))) grassToEat++;
        animal2E.setPosition(map1.positionChanged(animal2E.getPosition(), new Vector2d(2,3), animal2E.getID()));

        if (grassMap.containsKey(new Vector2d(4,6))) grassToEat++;
        animal3E.setPosition(map1.positionChanged(animal3E.getPosition(), new Vector2d(4,6), animal3E.getID()));

        if (grassMap.containsKey(new Vector2d(7,2))) grassToEat++;
        animal4E.setPosition(map1.positionChanged(animal4E.getPosition(), new Vector2d(7,2), animal4E.getID()));

        if (grassMap.containsKey(new Vector2d(9,4))) grassToEat++;
        animal5E.setPosition(map1.positionChanged(animal5E.getPosition(), new Vector2d(9,4), animal5E.getID()));


        assertEquals(new Vector2d(1,4), animal1E.getPosition());
        assertEquals(new Vector2d(2,3), animal2E.getPosition());
        assertEquals(new Vector2d(4,6), animal3E.getPosition());
        assertEquals(new Vector2d(7,2), animal4E.getPosition());
        assertEquals(new Vector2d(9,4), animal5E.getPosition());
        assertEquals(grassToEat, map1.eatGrass());
    }

    @Test
    void objectAtTest(){
        createConfigs();
        assertEquals(animal3E, map1.objectAt(animal3E.getPosition()).pollLast());
        assertEquals(animal4E, map1.objectAt(animal4E.getPosition()).pollLast());
        assertEquals(animal5E, map1.objectAt(animal5E.getPosition()).pollLast());
    }

    @Test
    void placeTest(){
        List<String> test1 = new ArrayList<>();
        Collections.addAll(test1, config1);
        data1 = new DataParameters(test1);
        engine1 = new SimulationEngine(data1);
        map1 = new EarthMap(engine1, data1);
        animal1E = new Animal(map1, new Vector2d(2,4), generateGenotype(), 0, engine1);
        animal2E = new Animal(map1, new Vector2d(2,4), generateGenotype(), 0, engine1);
        animal3E = new Animal(map1, new Vector2d(4,5), generateGenotype(), 0, engine1);
        animal4E = new Animal(map1, new Vector2d(7,1), generateGenotype(), 0, engine1);
        animal5E = new Animal(map1, new Vector2d(10,3), generateGenotype(), 0, engine1);

        assertEquals(new Vector2d(2,4), animal1E.getPosition());
        assertEquals(new Vector2d(2,4), animal2E.getPosition());
        assertEquals(new Vector2d(4,5), animal3E.getPosition());
        assertEquals(new Vector2d(7,1), animal4E.getPosition());
        assertEquals(new Vector2d(10,3), animal5E.getPosition());
    }
}
