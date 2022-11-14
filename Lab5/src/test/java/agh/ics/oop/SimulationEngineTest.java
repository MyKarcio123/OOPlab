package agh.ics.oop;

import agh.ics.oop.entities.Entity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {
    @Test
    void runTest() {
        //given
        RectangularMap map = new RectangularMap(10, 5);
        String[][] directions = {{"f", "b"}, {"r", "l"},
                {"r", "l", "f", "f"}, {"r", "r"},
                {"r", "l", "r", "r", "f", "f"},
                {"r", "l", "r", "r", "f", "f"},
                {"r", "l", "r", "r", "f", "f"},
                {"r", "l", "r", "r", "f", "f"}};
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        List<Entity> entities = new ArrayList<Entity>();
        for (int i=0;i< positions.length;++i){
            entities.add(new Animal(map,positions[i]));
        }
        //when
        Vector2d[] sol = {new Vector2d(2, 2), new Vector2d(3, 4),
                new Vector2d(2, 3), new Vector2d(3, 3),
                new Vector2d(2, 3), new Vector2d(3, 3),
                new Vector2d(2, 3), new Vector2d(3, 3),
                new Vector2d(2, 3), new Vector2d(3, 3),
                new Vector2d(2, 2), new Vector2d(3, 4),
                new Vector2d(2, 1), new Vector2d(3, 5),
                new Vector2d(2, 0), new Vector2d(3, 5),
                new Vector2d(2, 0), new Vector2d(3, 5)};

        Vector2d[] prog = new Vector2d[18];
        for (int i = 0; i < directions.length; i += 1) {
            SimulationEngine engine = new SimulationEngine(
                    OptionsParser.parse(directions[i]),
                    map,
                    positions,
                    entities
                    );
            if (i == 0) {
                prog[i] = ((Animal)entities.get(0)).getPositionOnPlane();
                prog[i + 1] = ((Animal)entities.get(1)).getPositionOnPlane();
            }
            for(int j=0;j<directions[i].length;j++) {
                engine.run();
                prog[2 * i + 2+ j%entities.size()] = ((Animal) entities.get(j%entities.size())).getPositionOnPlane();
            }
        }
        System.out.println(Arrays.toString(prog));
        System.out.println(Arrays.toString(sol));
        //then
        assertTrue(Arrays.equals(prog, sol));
    }
}