package agh.ics.oop;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalDataTest {
    AnimalData data1 = new AnimalData(new Vector2d(1,1), 0);
    AnimalData data2 = new AnimalData(new Vector2d(6,9), 4);
    AnimalData data3 = new AnimalData(new Vector2d(-4,-2), 5);

    @Test
    void getPositionTest(){
        assertEquals(new Vector2d(1,1), data1.getPosition());
        assertEquals(new Vector2d(6,9), data2.getPosition());
        assertEquals(new Vector2d(-4,-2), data3.getPosition());

        assertNotEquals(new Vector2d(-1,1), data1.getPosition());
        assertNotEquals(new Vector2d(-6,-9), data2.getPosition());
        assertNotEquals(new Vector2d(4,2), data3.getPosition());
    }

    @Test
    void getIDTest(){
        assertEquals(0, data1.getID());
        assertEquals(4, data2.getID());
        assertEquals(5, data3.getID());

        assertNotEquals(1, data1.getID());
        assertNotEquals(-6, data1.getID());
        assertNotEquals(-5, data1.getID());
    }
}
