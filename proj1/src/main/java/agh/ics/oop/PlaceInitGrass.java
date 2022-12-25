package agh.ics.oop;

import java.util.*;

import static java.lang.Math.min;

public class PlaceInitGrass {
    public static List<Vector2d> placeGrass (AbstractWorldMap map, int amount, int GRASS_GROW_VARIANT) {
        List<Vector2d> placesOfGrass = new ArrayList<>();
        //Wariant zrównoważony wzrost
        if (GRASS_GROW_VARIANT == 0){
            //wymiary równika
            int amountOnEquator = (int) amount*4/5;
            int equatorRadius = (int) (map.getMapUpperRight().getY()-map.getMapLowerLeft().getY())/10;
            int equatorY = (int)(map.getMapUpperRight().getY()+map.getMapLowerLeft().getY())/2;
            int equatorLowerBound = equatorY - equatorRadius;
            int equatorUpperBound = equatorY + equatorRadius;

            //robienie trawy na równiku
            ArrayList<Vector2d> grassPositions = new ArrayList<>();
            for (int i = map.getMapLowerLeft().getX(); i <= map.getMapUpperRight().getX(); i++) {
                for (int j = equatorLowerBound; j <= equatorUpperBound; j++) {
                    grassPositions.add(new Vector2d(i, j));
                }
            }

            Random random = new Random();
            for (int i = 0; i < amountOnEquator; ++i) {
                int randomIndex = random.nextInt(grassPositions.size());
                Vector2d grassPosition = grassPositions.get(randomIndex);
                placesOfGrass.add(grassPosition);
                grassPositions.remove(grassPosition);
            }

            //robienie trawy poza równikiem
            grassPositions.clear();
            for (int i = map.getMapLowerLeft().getX(); i <= map.getMapUpperRight().getX(); i++) {
                for (int j = map.getMapLowerLeft().getY(); j <= map.getMapUpperRight().getY(); j++) {
                    if(j<equatorLowerBound || j> equatorUpperBound){grassPositions.add(new Vector2d(i, j));
                    }
                }
            }

            for (int i = 0; i < amount - amountOnEquator; ++i) {
                int randomIndex = random.nextInt(grassPositions.size());
                Vector2d grassPosition = grassPositions.get(randomIndex);
                placesOfGrass.add(grassPosition);
                grassPositions.remove(grassPosition);
            }
            //wariant Toksyczne Trupy
        }else if(GRASS_GROW_VARIANT == 1){
            //szukanie ile jest to najmniej
            Integer minDeathToll = Integer.MAX_VALUE;
            for(Integer deathToll : map.getHistoryOfDeathAnimals().values()){
                minDeathToll = min(minDeathToll, deathToll);
            }
            //wybieranie pozycji na której mamy kłaść trawy
            ArrayList<Vector2d> grassPositions = new ArrayList<>();
            for (Map.Entry<Vector2d, Integer> entry : map.getHistoryOfDeathAnimals().entrySet()) {
                Vector2d key = entry.getKey();
                Integer value = entry.getValue();
                if (Objects.equals(value, minDeathToll)){
                    grassPositions.add(key);
                }
            }
            //wylosowywanie tych pozycji i kładzenie trawy
            Random random = new Random();
            for (int i = 0; i < amount; ++i) {
                int randomIndex = random.nextInt(grassPositions.size());
                Vector2d grassPosition = grassPositions.get(randomIndex);
                placesOfGrass.add(grassPosition);
                grassPositions.remove(grassPosition);
            }
        }
        return placesOfGrass;
    }


}
