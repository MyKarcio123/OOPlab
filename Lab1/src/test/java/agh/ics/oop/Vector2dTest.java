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
    void VectorEqual_Whenx1y1x1y1_ShouldReturnTrue(){
        assertEquals(main, new Vector2d(1, 1));
    }
    @Test
    void VectorEqual_Whenx1y1x1y0_ShouldReturnFalse(){
        assertNotEquals(main, new Vector2d(1, 0));
    }

    @Test
    void VectorEqual_Whenx1y1AndSecondIsNotAnObject_ShouldReturnFalse(){
        assertNotEquals(main,4);
    }

    @Test
    void VectorEqual_Whenx1y1AndSecondIsDiffrentObject_ShouldReturnFalse(){
        assertNotEquals(main,"TEST");
    }
    @Test
    void VectorEqual_Whenx1y1AndSecondIsArrayOfVectorObject_ShouldReturnFalse(){
        assertNotEquals(main, new Vector2d[10]);
    }

    @Test
    void VectorToString_Whenx1y1_ShouldReturn1_1InBrackets(){
        assertEquals("(1,1)",main.toString());
    }

    @Test
    void VectorPrecedes_Whenx1y1x2y2_ShouldReturnTrue(){
        assertTrue(main.precedes(new Vector2d(2,2)));
    }

    @Test
    void VectorPrecedes_Whenx1y1x0y2_ShouldReturnFalse(){
        assertFalse(main.precedes(new Vector2d(0,2)));
    }

    @Test
    void VectorPrecedes_Whenx1y1x0y0_ShouldReturnFalse(){
        assertFalse(main.precedes(new Vector2d(0,0)));
    }

    @Test
    void VectorPrecedes_Whenx1y1x1y2_ShouldReturnTrue(){
        assertTrue(main.precedes(new Vector2d(1,2)));
    }

    @Test
    void VectorPrecedes_Whenx1y1x2y1_ShouldReturnTrue(){
        assertTrue(main.precedes(new Vector2d(2,1)));
    }

    @Test
    void VectorPrecedes_Whenx1y1x1y1_ShouldReturnTrue(){
        assertTrue(main.precedes(new Vector2d(1,21)));
    }

    @Test
    void VectorFollows_Whenx1y1x2y2_ShouldReturnFalse(){
        assertFalse(main.follows( new Vector2d(2,2)));
    }

    @Test
    void VectorFollows_Whenx1y1x0y2_ShouldReturnFalse(){
        assertFalse(main.follows( new Vector2d(0,2)));
    }

    @Test
    void VectorFollows_Whenx1y1x0y0_ShouldReturnTrue(){
        assertTrue(main.follows( new Vector2d(0,0)));
    }

    @Test
    void VectorFollows_Whenx1y1x1y2_ShouldReturnFalse(){
        assertFalse(main.follows( new Vector2d(1,2)));
    }

    @Test
    void VectorFollows_Whenx1y1x2y1_ShouldReturnFalse(){
        assertFalse(main.follows( new Vector2d(2,1)));
    }

    @Test
    void VectorFollows_Whenx1y1x1y1_ShouldReturnTrue(){
        assertTrue(main.follows( new Vector2d(1,1)));
    }

    @Test
    void VectorUpperRight_Whenx1y1x2y2_ShouldReturnx2y2(){
        assertEquals(new Vector2d(2,2),main.upperRight(new Vector2d(2,2)));
    }

    @Test
    void VectorUpperRight_Whenx1y1x0y2_ShouldReturnx1y2(){
        assertEquals(new Vector2d(1,2),main.upperRight(new Vector2d(0,2)));
    }

    @Test
    void VectorUpperRight_Whenx1y1x2y1_ShouldReturnx2y1(){
        assertEquals(new Vector2d(2,1),main.upperRight(new Vector2d(2,1)));
    }

    @Test
    void VectorUpperRight_Whenx1y1x1y1_ShouldReturnx1y1(){
        assertEquals(new Vector2d(1,1),main.upperRight(new Vector2d(1,1)));
    }

    @Test
    void VectorUpperRight_Whenx1y1x_1y_1_ShouldReturnx1y1(){
        assertEquals(new Vector2d(1,1),main.upperRight(new Vector2d(-1,-1)));
    }

    @Test
    void VectorLowerLeft_Whenx1y1x_10y0_ShouldReturnx_10y0(){
        assertEquals(new Vector2d(-10,0),main.lowerLeft(new Vector2d(-10,0)));
    }

    @Test
    void VectorLowerLeft_Whenx1y1x0y2_ShouldReturnx0y1(){
        assertEquals(new Vector2d(0,1),main.lowerLeft(new Vector2d(0,2)));
    }

    @Test
    void VectorLowerLeft_Whenx1y1x10y_2ShouldReturnx1y_2(){
        assertEquals(new Vector2d(1,-2),main.lowerLeft(new Vector2d(10,-2)));
    }

    @Test
    void VectorLowerLeft_Whenx1y1x1y1_ShouldReturnx1y1(){
        assertEquals(new Vector2d(1,1),main.lowerLeft(new Vector2d(1,1)));
    }
    @Test
    void VectorLowerLeft_Whenx1y1x3y5_ShouldReturnx1y1(){
        assertEquals(new Vector2d(1,1),main.lowerLeft(new Vector2d(3,5)));
    }

    @Test
    void VectorAdd_Whenx1y1x0y0_ShouldReturnx1y1(){
        assertEquals(new Vector2d(1,1),main.add(new Vector2d(0,0)));
    }

    @Test
    void VectorAdd_Whenx1y1x10y0_ShouldReturnx11y1(){
        assertEquals(new Vector2d(11,1),main.add(new Vector2d(10,0)));
    }

    @Test
    void VectorAdd_Whenx1y1x0y10_ShouldReturnx1y11(){
        assertEquals(new Vector2d(1,11),main.add(new Vector2d(0,10)));
    }

    @Test
    void VectorAdd_Whenx1y1x_5y10_ShouldReturnx_4y11(){
        assertEquals(new Vector2d(-4,11),main.add(new Vector2d(-5,10)));
    }

    @Test
    void VectorAdd_Whenx1y1x0y_10_ShouldReturnx1y_9(){
        assertEquals(new Vector2d(1,-9),main.add(new Vector2d(0,-10)));
    }

    @Test
    void VectorSubstract_Whenx1y1x0y0_ShouldReturnx1y1(){
        assertEquals(new Vector2d(1,1),main.subtract(new Vector2d(0,0)));
    }

    @Test
    void VectorSubstract_Whenx1y1x10y0_ShouldReturnx_9y1(){
        assertEquals(new Vector2d(-9,1),main.subtract(new Vector2d(10,0)));
    }

    @Test
    void VectorSubstract_Whenx1y1x0y10_ShouldReturnx1y_9(){
        assertEquals(new Vector2d(1,-9),main.subtract(new Vector2d(0,10)));
    }

    @Test
    void VectorSubstract_Whenx1y1x_5y10_ShouldReturnx6y_9(){
        assertEquals(new Vector2d(6,-9),main.subtract(new Vector2d(-5,10)));
    }

    @Test
    void VectorSubstract_Whenx1y1x0y_10_ShouldReturnx1y11(){
        assertEquals(new Vector2d(1,11),main.subtract(new Vector2d(0,-10)));
    }

    @Test
    void VectorOpposite_WhenPositive_ShouldReturnNegative(){
        assertEquals(new Vector2d(-1,-1),main.opposite());
    }

    @Test
    void VectorOpposite_WhenNegative_ShouldReturnPositive(){
        assertEquals(new Vector2d(1,1),new Vector2d(-1,-1).opposite());
    }

    @Test
    void VectorOpposite_WhenZero_ShouldReturnZero(){
        assertEquals(new Vector2d(0,0),new Vector2d(0,0).opposite());
    }

}