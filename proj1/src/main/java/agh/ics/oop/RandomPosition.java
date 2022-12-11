package agh.ics.oop;

import java.util.Random;

public class RandomPosition {
    public static Vector2d getRandomPosition(Vector2d lowerLeft, Vector2d upperRight){
        Random random = new Random();
        return new Vector2d(random.nextInt(lowerLeft.getX(), upperRight.getX()),
                random.nextInt(lowerLeft.getY(), upperRight.getY()));
    }
}
