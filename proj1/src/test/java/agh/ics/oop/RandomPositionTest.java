package agh.ics.oop;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RandomPositionTest {
    Vector2d upper1 = new Vector2d(3,3);
    Vector2d lower1 = new Vector2d(-3,-3);
    Vector2d upper2 = new Vector2d(4,4);
    Vector2d lower2 = new Vector2d(-4,-4);
    Vector2d randPos1 = RandomPosition.getRandomPosition(lower1, upper1);
    Vector2d randPos2 = RandomPosition.getRandomPosition(lower1, upper1);;
    Vector2d randPos3 = RandomPosition.getRandomPosition(lower2, upper2);
    Vector2d randPos4 = RandomPosition.getRandomPosition(lower2, upper2);
    int genNo1 = RandomPosition.getNumberOfGenesToChange(11);
    int genNo2 = RandomPosition.getNumberOfGenesToChange(24);
    int genNo3 = RandomPosition.getNumberOfGenesToChange(69);
    int genNo4 = RandomPosition.getNumberOfGenesToChange(9);
    int genNo5 = RandomPosition.getNumberOfGenesToChange(5);
    int genNo6 = RandomPosition.getNumberOfGenesToChange(420);
    int genNo7 = RandomPosition.getNumberOfGenesToChange(49);
    int genNo8 = RandomPosition.getNumberOfGenesToChange(23);
    int randGene1 = RandomPosition.getRandomGene();
    int randGene2 = RandomPosition.getRandomGene();
    int randGene3 = RandomPosition.getRandomGene();
    int randGene4 = RandomPosition.getRandomGene();
    int randGene5 = RandomPosition.getRandomGene();
    int randGene6 = RandomPosition.getRandomGene();


    @Test
    void getRandomPosition(){
        assertTrue(lower1.getX() <= randPos1.getX() && lower1.getY() <= randPos1.getY());
        assertTrue(randPos1.getX() <= upper1.getX() && randPos1.getY() <= randPos1.getY());

        assertTrue(lower1.getX() <= randPos2.getX() && lower1.getY() <= randPos2.getY());
        assertTrue(randPos2.getX() <= upper2.getX() && randPos2.getY() <= upper2.getY());

        assertTrue(lower2.getX() <= randPos3.getX() && lower2.getY() <= randPos3.getY());
        assertTrue(randPos3.getX() <= upper2.getX() && randPos3.getY() <= upper2.getY());

        assertTrue(lower2.getX() <= randPos4.getX() && lower2.getY() <= randPos4.getY());
        assertTrue(randPos4.getX() <= upper2.getX() && randPos4.getY() <= upper2.getY());
    }

    @Test
    void getBinaryDigitTest(){
        int digit1 = RandomPosition.getBinaryDigit();
        int digit2 = RandomPosition.getBinaryDigit();
        int digit3 = RandomPosition.getBinaryDigit();
        int digit4 = RandomPosition.getBinaryDigit();
        int digit5 = RandomPosition.getBinaryDigit();

        assertTrue(digit1 == 0 || digit1 == 1);
        assertTrue(digit2 == 0 || digit2 == 1);
        assertTrue(digit3 == 0 || digit3 == 1);
        assertTrue(digit4 == 0 || digit4 == 1);
        assertTrue(digit5 == 0 || digit5 == 1);
    }

    @Test
    void getNumberOfGenesToChange(){
        assertTrue(0 <= genNo1 && genNo1 <= 11);
        assertTrue(0 <= genNo2 && genNo2 <= 24);
        assertTrue(0 <= genNo3 && genNo3 <= 69);
        assertTrue(0 <= genNo4 && genNo4 <= 9);
        assertTrue(0 <= genNo5 && genNo5 <= 5);
        assertTrue(0 <= genNo6 && genNo6 <= 420);
        assertTrue(0 <= genNo7 && genNo7 <= 59);
        assertTrue(0 <= genNo8 && genNo8 <= 23);
    }

    @Test
    void getListOfIndexesOfGenesToChange(){
        List<Integer> randIndexes1 = RandomPosition.getListOfIndexesOfGenesToChange(10);
        List<Integer> randIndexes2 = RandomPosition.getListOfIndexesOfGenesToChange(24);
        List<Integer> randIndexes3 = RandomPosition.getListOfIndexesOfGenesToChange(37);

        assertTrue(randIndexes1.size() <= 10);
        assertTrue(randIndexes2.size() <= 24);
        assertTrue(randIndexes3.size() <= 37);
    }

    @Test
    void getRandomGene(){
        assertTrue(0 <= randGene1 && randGene1 <= 7);
        assertTrue(0 <= randGene2 && randGene2 <= 7);
        assertTrue(0 <= randGene3 && randGene3 <= 7);
        assertTrue(0 <= randGene4 && randGene4 <= 7);
        assertTrue(0 <= randGene5 && randGene5 <= 7);
        assertTrue(0 <= randGene6 && randGene6 <= 7);
    }
}
