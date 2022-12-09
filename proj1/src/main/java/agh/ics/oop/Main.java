package agh.ics.oop;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        SetMultimap<Vector2d,Integer> testMap = MultimapBuilder.hashKeys().treeSetValues().build();
        testMap.put(new Vector2d(2,2),10);
        testMap.put(new Vector2d(2,2),9);
        testMap.put(new Vector2d(2,2),11);
        testMap.put(new Vector2d(2,2),13);
        Set<Integer> test = testMap.get(new Vector2d(2,2));
        System.out.println(test);
    }
}