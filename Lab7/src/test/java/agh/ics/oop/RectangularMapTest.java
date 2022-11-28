package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    RectangularMap rectangularMap;
    Animal a1;
    Animal a2;

    @BeforeEach
    void setUpTest() {
        rectangularMap = new RectangularMap(10, 5);
        a1 = new Animal(rectangularMap, new Vector2d(2, 2));
        a2 = new Animal(rectangularMap, new Vector2d(9, 4));
        rectangularMap.place(a1);
        rectangularMap.place(a2);
    }

    @Test
    void canMoveToTest() {
        //then
        assertFalse(rectangularMap.canMoveTo(a1.getPositionOnPlane()));
        assertFalse(rectangularMap.canMoveTo(a2.getPositionOnPlane()));
        assertFalse(rectangularMap.canMoveTo(new Vector2d(-1, -1)));
        assertFalse(rectangularMap.canMoveTo(new Vector2d(10, 10)));
        assertTrue(rectangularMap.canMoveTo(new Vector2d(1, 1)));
    }

    @Test
    void placeTest() {
        //given
        Vector2d pos = new Vector2d(2, 2);

        //then
        assertTrue(!rectangularMap.canMoveTo(pos));
        assertTrue(rectangularMap.isOccupied(pos));
        assertTrue(a1.equals(rectangularMap.objectAt(pos)));
    }

    @Test
    void isOccupiedTest() {
        //then
        assertTrue(rectangularMap.isOccupied(a1.getPositionOnPlane()));
        assertTrue(rectangularMap.isOccupied(a2.getPositionOnPlane()));
        assertFalse(rectangularMap.isOccupied(new Vector2d(5, 5)));
    }

    @Test
    void objectAtTest() {
        //then
        assertEquals(a1, rectangularMap.objectAt(new Vector2d(2, 2)));
        assertEquals(a2, rectangularMap.objectAt(new Vector2d(9, 4)));
        assertNull(rectangularMap.objectAt(new Vector2d(8, 8)));
    }
}