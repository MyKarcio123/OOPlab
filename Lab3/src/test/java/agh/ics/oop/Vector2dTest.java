package agh.ics.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    Vector2d main;
    @BeforeEach
    void setUp(){
        main = new Vector2d(1,1);
    }
    @Test
    void vectorEqual_whenX1Y1X1Y1_shouldReturnTrue(){
        assertEquals(main, new Vector2d(1, 1));
    }
    @Test
    void vectorEqual_whenX1Y1X1Y0_shouldReturnFalse(){
        assertNotEquals(main, new Vector2d(1, 0));
    }

    @Test
    void vectorEqual_whenX1Y1AndSecondIsNotAnObject_shouldReturnFalse(){
        assertNotEquals(main,4);
    }

    @Test
    void vectorEqual_whenX1Y1AndSecondIsDifferentObject_shouldReturnFalse(){
        assertNotEquals(main,"TEST");
    }
    @Test
    void vectorEqual_whenX1Y1AndSecondIsArrayOfVectorObject_shouldReturnFalse(){
        assertNotEquals(main, new Vector2d[10]);
    }

    @Test
    void vectorToString_whenX1Y1_shouldReturn1_1InBrackets(){
        assertEquals("(1,1)",main.toString());
    }

    @Test
    void vectorPrecedes_whenX1Y1X2Y2_shouldReturnTrue(){
        assertTrue(main.precedes(new Vector2d(2,2)));
    }

    @Test
    void vectorPrecedes_whenX1Y1X0Y2_shouldReturnFalse(){
        assertFalse(main.precedes(new Vector2d(0,2)));
    }

    @Test
    void vectorPrecedes_whenX1Y1X0Y0_shouldReturnFalse(){
        assertFalse(main.precedes(new Vector2d(0,0)));
    }

    @Test
    void vectorPrecedes_whenX1Y1X1Y2_shouldReturnTrue(){
        assertTrue(main.precedes(new Vector2d(1,2)));
    }

    @Test
    void vectorPrecedes_whenX1Y1X2Y1_shouldReturnTrue(){
        assertTrue(main.precedes(new Vector2d(2,1)));
    }

    @Test
    void vectorPrecedes_whenX1Y1X1Y1_shouldReturnTrue(){
        assertTrue(main.precedes(new Vector2d(1,21)));
    }

    @Test
    void vectorFollows_whenX1Y1X2Y2_shouldReturnFalse(){
        assertFalse(main.follows( new Vector2d(2,2)));
    }

    @Test
    void vectorFollows_whenX1Y1X0Y2_shouldReturnFalse(){
        assertFalse(main.follows( new Vector2d(0,2)));
    }

    @Test
    void vectorFollows_whenX1Y1X0Y0_shouldReturnTrue(){
        assertTrue(main.follows( new Vector2d(0,0)));
    }

    @Test
    void vectorFollows_whenX1Y1X1Y2_shouldReturnFalse(){
        assertFalse(main.follows( new Vector2d(1,2)));
    }

    @Test
    void vectorFollows_whenX1Y1X2Y1_shouldReturnFalse(){
        assertFalse(main.follows( new Vector2d(2,1)));
    }

    @Test
    void vectorFollows_whenX1Y1X1Y1_shouldReturnTrue(){
        assertTrue(main.follows( new Vector2d(1,1)));
    }

    @Test
    void vectorUpperRight_whenX1Y1X2Y2_shouldReturnX2Y2(){
        assertEquals(new Vector2d(2,2),main.upperRight(new Vector2d(2,2)));
    }

    @Test
    void vectorUpperRight_whenX1Y1X0Y2_shouldReturnX1Y2(){
        assertEquals(new Vector2d(1,2),main.upperRight(new Vector2d(0,2)));
    }

    @Test
    void vectorUpperRight_whenX1Y1X2Y1_shouldReturnX2Y1(){
        assertEquals(new Vector2d(2,1),main.upperRight(new Vector2d(2,1)));
    }

    @Test
    void vectorUpperRight_whenX1Y1X1Y1_shouldReturnX1Y1(){
        assertEquals(new Vector2d(1,1),main.upperRight(new Vector2d(1,1)));
    }

    @Test
    void vectorUpperRight_whenX1Y1X_1Y_1_shouldReturnX1Y1(){
        assertEquals(new Vector2d(1,1),main.upperRight(new Vector2d(-1,-1)));
    }

    @Test
    void vectorLowerLeft_whenX1Y1X_10Y0_shouldReturnX_10Y0(){
        assertEquals(new Vector2d(-10,0),main.lowerLeft(new Vector2d(-10,0)));
    }

    @Test
    void vectorLowerLeft_whenX1Y1X0Y2_shouldReturnX0Y1(){
        assertEquals(new Vector2d(0,1),main.lowerLeft(new Vector2d(0,2)));
    }

    @Test
    void vectorLowerLeft_whenX1Y1X10Y_2_shouldReturnX1Y_2(){
        assertEquals(new Vector2d(1,-2),main.lowerLeft(new Vector2d(10,-2)));
    }

    @Test
    void vectorLowerLeft_whenX1Y1X1Y1_shouldReturnX1Y1(){
        assertEquals(new Vector2d(1,1),main.lowerLeft(new Vector2d(1,1)));
    }
    @Test
    void vectorLowerLeft_whenX1Y1X3y5_shouldReturnX1Y1(){
        assertEquals(new Vector2d(1,1),main.lowerLeft(new Vector2d(3,5)));
    }

    @Test
    void vectorAdd_whenX1Y1X0y0_shouldReturnX1Y1(){
        assertEquals(new Vector2d(1,1),main.add(new Vector2d(0,0)));
    }

    @Test
    void vectorAdd_whenX1Y1X10Y0_shouldReturnX11Y1(){
        assertEquals(new Vector2d(11,1),main.add(new Vector2d(10,0)));
    }

    @Test
    void vectorAdd_whenX1Y1X0Y10_shouldReturnX1Y11(){
        assertEquals(new Vector2d(1,11),main.add(new Vector2d(0,10)));
    }

    @Test
    void vectorAdd_whenX1Y1X_5Y10_shouldReturnX_4Y11(){
        assertEquals(new Vector2d(-4,11),main.add(new Vector2d(-5,10)));
    }

    @Test
    void vectorAdd_whenX1Y1X0Y_10_shouldReturnX1Y_9(){
        assertEquals(new Vector2d(1,-9),main.add(new Vector2d(0,-10)));
    }

    @Test
    void vectorSubtract_whenX1Y1X0Y0_shouldReturnX1Y1(){
        assertEquals(new Vector2d(1,1),main.subtract(new Vector2d(0,0)));
    }

    @Test
    void vectorSubtract_whenX1Y1X10Y0_shouldReturnX_9Y1(){
        assertEquals(new Vector2d(-9,1),main.subtract(new Vector2d(10,0)));
    }

    @Test
    void vectorSubtract_whenX1Y1X0Y10_shouldReturnX1Y_9(){
        assertEquals(new Vector2d(1,-9),main.subtract(new Vector2d(0,10)));
    }

    @Test
    void vectorSubtract_whenX1Y1X_5Y10_shouldReturnX6Y_9(){
        assertEquals(new Vector2d(6,-9),main.subtract(new Vector2d(-5,10)));
    }

    @Test
    void vectorSubtract_whenX1Y1X0Y_10_shouldReturnX1Y11(){
        assertEquals(new Vector2d(1,11),main.subtract(new Vector2d(0,-10)));
    }

    @Test
    void vectorOpposite_whenPositive_shouldReturnNegative(){
        assertEquals(new Vector2d(-1,-1),main.opposite());
    }

    @Test
    void vectorOpposite_whenNegative_shouldReturnPositive(){
        assertEquals(new Vector2d(1,1),new Vector2d(-1,-1).opposite());
    }

    @Test
    void vectorOpposite_whenZero_shouldReturnZero(){
        assertEquals(new Vector2d(0,0),new Vector2d(0,0).opposite());
    }

}