package agh.ics.oop;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class NoiseDataTest {
    NoiseData noise1 = new NoiseData(150, 120, 1.5, 0.95, 10, 2.5f);
    NoiseData noise2 = new NoiseData(500, 500, 2.5, 0.55, 8, 1.5f);


    @Test
    void getWidthTest(){
        assertEquals(150, noise1.getWidth());
        assertEquals(500, noise2.getWidth());
    }

    @Test
    void getHeightTest(){
        assertEquals(120, noise1.getHeight());
        assertEquals(500, noise2.getHeight());
    }

    @Test
    void getPersistenceTest(){
        assertEquals(1.5, noise1.getPersistence());
        assertEquals(2.5, noise2.getPersistence());
    }

    @Test
    void getFrequencyTest(){
        assertEquals(0.95, noise1.getFrequency());
        assertEquals(0.55, noise2.getFrequency());
    }

    @Test
    void getOctavesTest(){
        assertEquals(10, noise1.getOctaves());
        assertEquals(8, noise2.getOctaves());
    }

    @Test
    void getScaleTest(){
        assertEquals(2.5f, noise1.getScale());
        assertEquals(1.5f, noise2.getScale());
    }
}
