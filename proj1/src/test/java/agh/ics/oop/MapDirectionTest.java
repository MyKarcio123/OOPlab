package agh.ics.oop;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class MapDirectionTest {
    MapDirection n = MapDirection.NORTH;
    MapDirection ne = MapDirection.NORTHEAST;
    MapDirection e = MapDirection.EAST;
    MapDirection se = MapDirection.SOUTHEAST;
    MapDirection s = MapDirection.SOUTH;
    MapDirection sw = MapDirection.SOUTHWEST;
    MapDirection w = MapDirection.WEST;
    MapDirection nw = MapDirection.NORTHWEST;
    MapDirection na = MapDirection.NONE;

    @Test
    void toStringTest(){
        assertEquals("Północ", n.toString());
        assertEquals("Północny-Wchód", ne.toString());
        assertEquals("Wschód", e.toString());
        assertEquals("Południowy-Wschód", se.toString());
        assertEquals("Południe", s.toString());
        assertEquals("Południowy-Zachód", sw.toString());
        assertEquals("Zachód", w.toString());
        assertEquals("Północ-Zachód", nw.toString());
        assertEquals("Brak kierunku", na.toString());
    }

    @Test
    void toUnitVector(){
        assertEquals(new Vector2d(0,1), n.toUnitVector());
        assertEquals(new Vector2d(1,1), ne.toUnitVector());
        assertEquals(new Vector2d(1,0), e.toUnitVector());
        assertEquals(new Vector2d(1,-1), se.toUnitVector());
        assertEquals(new Vector2d(0,-1), s.toUnitVector());
        assertEquals(new Vector2d(-1,-1), sw.toUnitVector());
        assertEquals(new Vector2d(-1,0), w.toUnitVector());
        assertEquals(new Vector2d(-1,1), nw.toUnitVector());
        assertEquals(new Vector2d(0,0), na.toUnitVector());
    }

    @Test
    void getRandomTest(){
        MapDirection dir1 = MapDirection.getRandom();
        MapDirection dir2 = MapDirection.getRandom();
        MapDirection dir3 = MapDirection.getRandom();
        MapDirection dir4 = MapDirection.getRandom();
        MapDirection dir5 = MapDirection.getRandom();
        MapDirection dir6 = MapDirection.getRandom();

        MapDirection[] directions = {n, ne, e, se, s, sw, w, nw, na};

        assertTrue(Arrays.asList(directions).contains(dir1));
        assertTrue(Arrays.asList(directions).contains(dir2));
        assertTrue(Arrays.asList(directions).contains(dir3));
        assertTrue(Arrays.asList(directions).contains(dir4));
        assertTrue(Arrays.asList(directions).contains(dir5));
        assertTrue(Arrays.asList(directions).contains(dir6));
    }
}
