package agh.ics.oop;

import java.util.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlaceInitGrassTest {
    String[] config1 = {"Test1", "40", "40", "100", "20", "5", "5", "100", "70", "25", "5", "15", "8", "0", "0", "0", "0"};
    DataParameters data1;
    SimulationEngine engine1;
    AbstractWorldMap map1;
    Animal animal1E, animal2E, animal3E, animal4E, animal5E;

    String[] config2 = {"Test2", "30", "30", "75", "20", "5", "5", "100", "70", "25", "5", "15", "8", "1", "0", "1", "0"};
    DataParameters data2;
    SimulationEngine engine2;
    AbstractWorldMap map2;
    Animal animal1H, animal2H, animal3H, animal4H, animal5H;

    void createConfigs(){
        List<String> test1 = new ArrayList<>();
        Collections.addAll(test1, config1);
        data1 = new DataParameters(test1);
        engine1 = new SimulationEngine(data1);
        map1 = new EarthMap(engine1, data1);
        animal1E = new Animal(map1, new Vector2d(2,4), generateGenotype(), 0, engine1);
        animal2E = new Animal(map1, new Vector2d(2,4), generateGenotype(), 0, engine1);
        animal3E = new Animal(map1, new Vector2d(4,5), generateGenotype(), 0, engine1);
        animal4E = new Animal(map1, new Vector2d(7,2), generateGenotype(), 0, engine1);
        animal5E = new Animal(map1, new Vector2d(10,3), generateGenotype(), 0, engine1);
        map1.place(animal1E);
        map1.place(animal2E);
        map1.place(animal3E);
        map1.place(animal4E);
        map1.place(animal5E);



        List<String> test2 = new ArrayList<>();
        Collections.addAll(test2, config2);
        data2 = new DataParameters(test2);
        engine2 = new SimulationEngine(data2);
        map2 = new HellMap(engine2, data2);
        animal1H = new Animal(map2, new Vector2d(2,4), generateGenotype(), 0, engine2);
        animal2H = new Animal(map2, new Vector2d(2,4), generateGenotype(), 0, engine2);
        animal3H = new Animal(map2, new Vector2d(4,5), generateGenotype(), 0, engine2);
        animal4H = new Animal(map2, new Vector2d(7,2), generateGenotype(), 0, engine2);
        animal5H = new Animal(map2, new Vector2d(10,9), generateGenotype(), 0, engine2);
        map2.place(animal1H);
        map2.place(animal2H);
        map2.place(animal3H);
        map2.place(animal4H);
        map2.place(animal5H);
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
    void EquatorGrasGrowTest() {
        createConfigs();
        Map<Vector2d, Grass> grassMap1 = map1.getGrassMap();
        int noEqGrass1 = 0, noOutGrass1 = 0;
        Map<Vector2d, Grass> grassMap2 = map2.getGrassMap();
        int noEqGrass2 = 0, noOutGrass2 = 0;

        for (Map.Entry<Vector2d, Grass> grass : grassMap1.entrySet()){
            if (grass.getKey().getY() >= 17 && grass.getKey().getY() <= 23){
                noEqGrass1++;
            } else {
                noOutGrass1++;
            }
        }
        assertEquals(80, noEqGrass1);
        assertEquals(20, noOutGrass1);

        for (Map.Entry<Vector2d, Grass> grass : grassMap2.entrySet()){
            if (grass.getKey().getY() >= 13 && grass.getKey().getY() <= 17){
                noEqGrass2++;
            } else {
                noOutGrass2++;
            }
        }
        assertEquals(60, noEqGrass2);
        assertEquals(15, noOutGrass2);
    }
}
