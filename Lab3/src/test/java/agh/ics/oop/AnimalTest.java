package agh.ics.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static agh.ics.oop.OptionsParser.parse;
import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    Animal animal;
    @BeforeEach
    void setUp(){
        animal = new Animal();
    }

    @Test
    void orientationTest(){
        List<MapDirection> orientations = new ArrayList<>();
        MapDirection[] answer = {MapDirection.NORTH,MapDirection.EAST,MapDirection.SOUTH,MapDirection.WEST,MapDirection.NORTH,MapDirection.NORTH,MapDirection.WEST,MapDirection.SOUTH,MapDirection.EAST,MapDirection.NORTH};
        for(int i=0;i<5;++i) {
            orientations.add(animal.getOrientation());
            animal.move(MoveDirection.RIGHT);
        }
        for(int i=0;i<5;++i){
            animal.move(MoveDirection.LEFT);
            orientations.add(animal.getOrientation());
        }
        assertEquals(Arrays.asList(answer),orientations);
    }
    @Test
    void moveTest(){
        String[] args = {"f","r","b","l","b","left","right","backward","forward","for","back","bi","ri","test"," ",""};
        List <MoveDirection> directions = parse(args);
        for(MoveDirection direction : directions){
            animal.move(direction);
        }
        assertAll(
                () -> assertEquals(MapDirection.NORTH,animal.getOrientation()),
                () -> assertTrue(animal.isAt(new Vector2d(1,2)))
        );
    }
    @Test
    void goingOutMap() {
        String[] args = {"f","f","f","f","f","f","f","f","f"};
        List <MoveDirection> directions = parse(args);
        for(MoveDirection direction : directions){
            animal.move(direction);
        }
        assertEquals(new Vector2d(2,4),animal.getPosition());
    }
}