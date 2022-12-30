package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomPosition {
    public static int getRandomSeed(){
        Random random = new Random();
        return random.nextInt(0,4000);
    }
    public static Vector2d getRandomPosition(Vector2d lowerLeft, Vector2d upperRight){
        Random random = new Random();
        return new Vector2d(random.nextInt(lowerLeft.getX(), upperRight.getX()),
                random.nextInt(lowerLeft.getY(), upperRight.getY()));
    }

    public static int getBinaryDigit(){
        Random random = new Random();
        return random.nextInt(0,2);
    }

    public static int getNumberOfGenesToChange(int genotypeLen){
        Random random = new Random();
        return random.nextInt(0,genotypeLen);
    }

    public static List<Integer> getListOfIndexesOfGenesToChange(int genotypeLen){
        int howManyGenesToChange = getNumberOfGenesToChange(genotypeLen);

        List<Integer> allIndexes = new ArrayList<>();
        List<Integer> indexesOfGenesToChange = new ArrayList<>();
        for (int i = 0; i < genotypeLen; i++) {
                allIndexes.add(i);
        }

        Random random = new Random();
        for (int i = 0; i < howManyGenesToChange; ++i) {
            int randomIndex = random.nextInt(allIndexes.size());
            indexesOfGenesToChange.add(randomIndex);
            allIndexes.remove(randomIndex);
        }

        return indexesOfGenesToChange;
    }
    public static Integer getRandomGene(){
        Random random = new Random();
        return random.nextInt(0,7);
    }
}
