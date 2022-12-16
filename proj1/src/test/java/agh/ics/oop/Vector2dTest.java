package agh.ics.oop;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    Vector2d vec1 = new Vector2d(-1, -1);
    Vector2d vec2 = new Vector2d(1, 1);
    Vector2d vec3 = new Vector2d(-1, 1);
    Vector2d vec4 = new Vector2d(1, -1);
    Vector2d vec5 = new Vector2d(2, 2);

    @Test
    void toStringTest(){
        assertEquals("(-1,-1)", vec1.toString());
        assertEquals("(1,1)", vec2.toString());
        assertEquals("(-1,1)", vec3.toString());
        assertEquals("(1,-1)", vec4.toString());
    }

    @Test
    void precedesTest(){
        assertTrue(vec4.precedes(vec5));
        assertFalse(vec5.precedes(vec4));
        assertTrue(vec1.precedes(vec1));
        assertFalse(vec3.precedes(vec4));
        assertFalse(vec4.precedes(vec3));
    }

    @Test
    void followsTest(){
        assertFalse(vec4.follows(vec5));
        assertTrue(vec5.follows(vec4));
        assertTrue(vec1.follows(vec1));
        assertFalse(vec3.follows(vec4));
        assertFalse(vec4.follows(vec3));
    }

    @Test
    void addTest(){
        assertEquals(new Vector2d(0,0), vec1.add(vec2));
        assertEquals(new Vector2d(-2,0), vec1.add(vec3));
        assertEquals(new Vector2d(0,-2), vec1.add(vec4));
        assertEquals(new Vector2d(0,2), vec2.add(vec3));
        assertEquals(new Vector2d(2,0), vec2.add(vec4));
        assertEquals(new Vector2d(0,0), vec3.add(vec4));
    }

    @Test
    void substractTest(){
        assertEquals(new Vector2d(-2,-2), vec1.substract(vec2));
        assertEquals(new Vector2d(0,-2), vec1.substract(vec3));
        assertEquals(new Vector2d(-2,0), vec1.substract(vec4));
        assertEquals(new Vector2d(2,0), vec2.substract(vec3));
        assertEquals(new Vector2d(0,2), vec2.substract(vec4));
        assertEquals(new Vector2d(-2,2), vec3.substract(vec4));
    }

    @Test
    void upperRightTest(){
        assertEquals(new Vector2d(-1,-1), vec1.upperRight(vec1));
        assertEquals(new Vector2d(1,1), vec1.upperRight(vec2));
        assertEquals(new Vector2d(-1,1), vec1.upperRight(vec3));
        assertEquals(new Vector2d(1,-1), vec1.upperRight(vec4));
        assertEquals(new Vector2d(1,1), vec2.upperRight(vec3));
        assertEquals(new Vector2d(1,1), vec2.upperRight(vec4));
        assertEquals(new Vector2d(1,1), vec3.upperRight(vec4));
    }

    @Test
    void lowerLeftTest(){
        assertEquals(new Vector2d(-1,-1), vec1.lowerLeft(vec1));
        assertEquals(new Vector2d(-1,-1), vec1.lowerLeft(vec2));
        assertEquals(new Vector2d(-1,-1), vec1.lowerLeft(vec3));
        assertEquals(new Vector2d(-1,-1), vec1.lowerLeft(vec4));
        assertEquals(new Vector2d(-1,1), vec2.lowerLeft(vec3));
        assertEquals(new Vector2d(1,-1), vec2.lowerLeft(vec4));
        assertEquals(new Vector2d(-1,-1), vec3.lowerLeft(vec4));
    }

    @Test
    void oppositeTest(){
        assertEquals(new Vector2d(1,1), vec1.opposite());
        assertEquals(new Vector2d(-1,-1), vec2.opposite());
        assertEquals(new Vector2d(1,-1), vec3.opposite());
        assertEquals(new Vector2d(-1,1), vec4.opposite());
        assertEquals(new Vector2d(-2,-2), vec5.opposite());
    }

    @Test
    void getXTest(){
        assertEquals(-1, vec1.getX());
        assertEquals(1, vec2.getX());
        assertEquals(-1, vec3.getX());
        assertEquals(1, vec4.getX());
        assertEquals(2, vec5.getX());
    }

    @Test
    void getYTest(){
        assertEquals(-1, vec1.getY());
        assertEquals(1, vec2.getY());
        assertEquals(1, vec3.getY());
        assertEquals(-1, vec4.getY());
        assertEquals(2, vec5.getY());
    }
}
