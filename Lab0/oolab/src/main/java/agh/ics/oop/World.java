package agh.ics.oop;

public class World {
    public static void main(String[] args){
        System.out.println("System wystartował");
        Direction.direction[] arguments = new Direction.direction[args.length];
        for(int i=0;i<args.length;++i){
            arguments[i]=conversion(args[i]);
        }
        run(arguments);
        System.out.println("System zakończył działanie");
    }
    public static void run(Direction.direction[] args){
        for(Direction.direction argument : args){
            switch (argument) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tylu");
                case LEFT -> System.out.println("Zwierzak skreca w lewo");
                case RIGHT -> System.out.println("Zwierzak skreca w prawo");
            }
        }
    }
    public static Direction.direction conversion(String command){
        switch (command) {
            case "f":
                return Direction.direction.FORWARD;
            case "b":
                return Direction.direction.BACKWARD;
            case "l":
                return Direction.direction.LEFT;
            case "r":
                return Direction.direction.RIGHT;
            default:
                return Direction.direction.NONE;
        }
    }
}
