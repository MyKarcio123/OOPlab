package agh.ics.oop;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class HellMapTest {
    // Zawiera tylko nadpisanych metod HellMap, ponieważ inne metody AbstractWorldMap
    // testowane są w EarthMapTest
    String[] config1 = {"Test1", "10", "10", "50", "20", "5", "5", "100", "70", "25", "5", "15", "8", "1", "0", "0", "0"};
    DataParameters data1;
    SimulationEngine engine1;
    AbstractWorldMap map1;
    Animal animal1E, animal2E, animal3E, animal4E, animal5E;

    void createConfigs(){
        List<String> test1 = new ArrayList<>();
        Collections.addAll(test1, config1);
        data1 = new DataParameters(test1);
        engine1 = new SimulationEngine(data1);
        map1 = new HellMap(engine1, data1);
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
        System.out.println(map1.canMoveTo(new Vector2d(-1,3)));
        assertTrue(map1.canMoveTo(new Vector2d(-1,3)));
        assertTrue(map1.canMoveTo(new Vector2d(1,-1)));
        assertTrue(map1.canMoveTo(new Vector2d(11,10)));
        assertTrue(map1.canMoveTo(new Vector2d(11,11)));
        assertFalse(map1.canMoveTo(new Vector2d(1,2)));
        assertFalse(map1.canMoveTo(new Vector2d(5,5)));
        assertFalse(map1.canMoveTo(new Vector2d(6,9)));
        assertFalse(map1.canMoveTo(new Vector2d(4,2)));

    }

    @Test
    void getNewPositionTest(){
        createConfigs();
        Vector2d newPos1 = map1.getNewPosition(new Vector2d(11,1));
        Vector2d newPos2 = map1.getNewPosition(new Vector2d(-1,1));
        Vector2d newPos3 = map1.getNewPosition(new Vector2d(1,-1));
        Vector2d newPos4 = map1.getNewPosition(new Vector2d(1,11));
        Vector2d newPos5 = map1.getNewPosition(new Vector2d(11,11));

        Vector2d lowerLeft = map1.getMapLowerLeft();
        Vector2d upperRight = map1.getMapUpperRight();

        assertTrue(newPos1.follows(lowerLeft) && newPos1.precedes(upperRight));
        assertTrue(newPos2.follows(lowerLeft) && newPos2.precedes(upperRight));
        assertTrue(newPos3.follows(lowerLeft) && newPos3.precedes(upperRight));
        assertTrue(newPos4.follows(lowerLeft) && newPos4.precedes(upperRight));
        assertTrue(newPos5.follows(lowerLeft) && newPos5.precedes(upperRight));
    }
}
