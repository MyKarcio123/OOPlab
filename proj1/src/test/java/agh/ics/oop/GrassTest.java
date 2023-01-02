package agh.ics.oop;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class GrassTest {
    Grass grass1 = new Grass(new Vector2d(1, 1));
    Grass grass2 = new Grass(new Vector2d(-1, -1));
    Grass grass3 = new Grass(new Vector2d(-1, 1));
    Grass grass4 = new Grass(new Vector2d(1, -1));

    @Test
    void getPositionTest(){
        assertEquals(new Vector2d(1,1), grass1.getPosition());
        assertEquals(new Vector2d(-1,-1), grass2.getPosition());
        assertEquals(new Vector2d(-1,1), grass3.getPosition());
        assertEquals(new Vector2d(1,-1), grass4.getPosition());
    }

    @Test
    void getDescTest(){
        assertEquals("T", grass1.getDesc());
        assertEquals("T", grass2.getDesc());
        assertEquals("T", grass3.getDesc());
        assertEquals("T", grass4.getDesc());
    }

    @Test
    void setClickedTest(){
        assertFalse(grass1.isClicked());
        grass1.setClicked(true);
        assertTrue(grass1.isClicked());
        grass1.setClicked(false);
        assertFalse(grass1.isClicked());
    }

    @Test
    void isClickedTest(){
        grass1.setClicked(true);
        grass3.setClicked(true);

        assertTrue(grass1.isClicked());
        assertFalse(grass2.isClicked());
        assertTrue(grass3.isClicked());
        assertFalse(grass4.isClicked());
    }
}
