package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptionsParser {

    public static List<MoveDirection> parse(String[] args){
        List<MoveDirection> directions = new ArrayList<MoveDirection>();
        MoveDirection[] availableDirections = {MoveDirection.FORWARD,MoveDirection.BACKWARD,MoveDirection.RIGHT,MoveDirection.LEFT};
        String[] correctDirections = {"f","b","r","l","forward","backward","right","left"};
        int index;
        for(String element : args){
            index = Arrays.asList(correctDirections).indexOf(element);
            if(index>=0){
                directions.add(availableDirections[index%4]);
            }
        }
        return directions;
    }
}
