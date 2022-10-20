package agh.ics.oop;

import java.util.List;

import static agh.ics.oop.OptionsParser.parse;

public class World {
    public static void main(String[] args){
        Animal pig = new Animal();
        List <MoveDirection> directions = parse(args);
        for(MoveDirection direction : directions){
            pig.move(direction);
        }
        System.out.println(pig);
    }
    public static void run(MoveDirection[] args){
        for(MoveDirection argument : args){
            switch (argument) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tylu");
                case LEFT -> System.out.println("Zwierzak skreca w lewo");
                case RIGHT -> System.out.println("Zwierzak skreca w prawo");
            }
        }
    }
    public static MoveDirection conversion(String command){
        return switch (command) {
            case "f" -> MoveDirection.FORWARD;
            case "b" -> MoveDirection.BACKWARD;
            case "l" -> MoveDirection.LEFT;
            case "r" -> MoveDirection.RIGHT;
            default -> MoveDirection.NONE;
        };
    }
}
