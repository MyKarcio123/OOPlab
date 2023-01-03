package agh.ics.oop;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    String[] config1 = {"Test1", "10", "10", "50", "20", "5", "5", "100", "70", "25", "5", "15", "8", "0", "0", "0", "0"};
    DataParameters data1;
    SimulationEngine engine1;
    AbstractWorldMap map1;
    Animal animal1E, animal2E, animal3E, animal4E, animal5E;

    String[] config2 = {"Test2", "10", "10", "50", "20", "5", "5", "100", "70", "25", "5", "15", "8", "1", "0", "1", "0"};
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
    void lowerEnergyTest(){
        createConfigs();
        HashMap<Vector2d, Integer> deadAnimals = map1.getDeathAnimals();

        int before1 = animal1E.getEnergy();
        int before2 = animal3E.getEnergy();
        animal1E.lowerEnergy(animal1E.getPosition());
        animal3E.lowerEnergy(animal3E.getPosition());
        assertTrue(before1 != animal1E.getEnergy());
        assertTrue(before2 != animal3E.getEnergy());

        assertTrue(deadAnimals.isEmpty());
        animal2E.subtractEnergy(120);
        animal2E.lowerEnergy(animal2E.getPosition());
        assertEquals(1, deadAnimals.size());
    }

    @Test
    void canCopulateTest(){
        createConfigs();
        assertTrue(animal1E.canCopulate());
        animal1E.subtractEnergy(20);
        assertTrue(animal1E.canCopulate());

        assertTrue(animal2E.canCopulate());
        animal2E.subtractEnergy(40);
        assertFalse(animal2E.canCopulate());

        animal3E.subtractEnergy(35);
        assertFalse(animal3E.canCopulate());
    }

    @Test
    void copulateTest(){
        createConfigs();
        List<Integer> childGenotype1 = new ArrayList<>();
        List<Integer> childGenotype2 = new ArrayList<>();
        List<Integer> childGenotype3 = new ArrayList<>();
        List<Integer> childGenotype4 = new ArrayList<>();


        childGenotype1 = animal1E.copulate(1, 7);
        childGenotype2 = animal1E.copulate(1, 3);
        childGenotype3 = animal2E.copulate(0, 8);
        childGenotype4 = animal3E.copulate(1, 5);

        assertEquals(7, childGenotype1.size());
        assertEquals(3, childGenotype2.size());
        assertEquals(8, childGenotype3.size());
        assertEquals(5, childGenotype4.size());

        assertEquals(2, animal1E.getChildAmount());
        assertEquals(1, animal2E.getChildAmount());
        assertEquals(1, animal3E.getChildAmount());
    }

    @Test
    void isAtTest(){
        createConfigs();
        assertTrue(animal1E.isAt(new Vector2d(2,4)));
        assertTrue(animal2E.isAt(new Vector2d(2,4)));
        assertTrue(animal3E.isAt(new Vector2d(4,5)));
        assertTrue(animal4E.isAt(new Vector2d(7,2)));
        assertTrue(animal5E.isAt(new Vector2d(10,3)));
        System.out.println(animal2E);
    }

    @Test
    void equalsTest(){
        createConfigs();
        assertEquals(animal1E, animal1E);
        assertEquals(animal3E, animal3E);
        assertEquals(animal5E, animal5E);
        assertNotEquals(animal1E, animal2E);
        assertNotEquals(animal1E, animal3E);
        assertNotEquals(animal4E, animal5E);
        assertNotEquals(animal3E, animal2E);
    }

    @Test
    void dayCycleTest(){
        createConfigs();
        int beforeGen1E = animal1E.getActiveGen();
        int beforeGen2E = animal2E.getActiveGen();
        int beforeGen3E = animal3E.getActiveGen();
        int beforeGen4E = animal4E.getActiveGen();
        int beforeGen5E = animal5E.getActiveGen();
        Vector2d beforePos1E = animal1E.getPosition();
        Vector2d beforePos2E = animal2E.getPosition();
        Vector2d beforePos3E = animal3E.getPosition();
        Vector2d beforePos4E = animal4E.getPosition();
        Vector2d beforePos5E = animal5E.getPosition();

        animal1E.dayCycle();
        animal2E.dayCycle();
        animal3E.dayCycle();
        animal4E.dayCycle();
        animal5E.dayCycle();
        assertNotEquals(beforePos1E, animal1E.getPosition());
        assertNotEquals(beforePos2E, animal2E.getPosition());
        assertNotEquals(beforePos3E, animal3E.getPosition());
        assertNotEquals(beforePos4E, animal4E.getPosition());
        assertNotEquals(beforePos5E, animal5E.getPosition());
        assertNotEquals(beforeGen1E, animal1E.getActiveGen());
        assertNotEquals(beforeGen2E, animal2E.getActiveGen());
        assertNotEquals(beforeGen3E, animal3E.getActiveGen());
        assertNotEquals(beforeGen4E, animal4E.getActiveGen());
        assertNotEquals(beforeGen5E, animal5E.getActiveGen());



        int beforeGen1H = animal1H.getActiveGen();
        int beforeGen2H = animal2H.getActiveGen();
        int beforeGen3H = animal3H.getActiveGen();
        int beforeGen4H = animal4H.getActiveGen();
        int beforeGen5H = animal5H.getActiveGen();
        Vector2d beforePos1H = animal1H.getPosition();
        Vector2d beforePos2H = animal2H.getPosition();
        Vector2d beforePos3H = animal3H.getPosition();
        Vector2d beforePos4H = animal4H.getPosition();
        Vector2d beforePos5H = animal5H.getPosition();

        animal1H.dayCycle();
        animal2H.dayCycle();
        animal3H.dayCycle();
        animal4H.dayCycle();
        animal5H.dayCycle();
        // Tutaj właściwie nie rozumiem, dlaczego raz na x testów, któregoś nie przechodzi,
        // codziennie powinien się gdzieś losowo ruszyć (niekoniecznie obrócić)
        // ====
        assertNotEquals(beforePos1H, animal1H.getPosition());
        assertNotEquals(beforePos2H, animal2H.getPosition());
        assertNotEquals(beforePos3H, animal3H.getPosition());
        assertNotEquals(beforePos4H, animal4H.getPosition());
        assertNotEquals(beforePos5H, animal5H.getPosition());
        // ====
        assertNotEquals(beforeGen1H, animal1H.getActiveGen());
        assertNotEquals(beforeGen2H, animal2H.getActiveGen());
        assertNotEquals(beforeGen3H, animal3H.getActiveGen());
        assertNotEquals(beforeGen4H, animal4H.getActiveGen());
        assertNotEquals(beforeGen5H, animal5H.getActiveGen());
    }
}
