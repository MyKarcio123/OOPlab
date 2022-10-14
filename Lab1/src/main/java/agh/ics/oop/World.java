package agh.ics.oop;
public class World {
    public static void main(String[] args){
        System.out.println("System wystartował");
        direction[] arguments = new direction[args.length];
        for(int i=0;i<args.length;++i){
            arguments[i]=conversion(args[i]);
        }
        run(arguments);
        System.out.println("System zakończył działanie");
    }
    public static void run(direction[] args){
        for(direction argument : args){
            switch (argument) {
                case FORWARD -> System.out.println("Zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("Zwierzak idzie do tylu");
                case LEFT -> System.out.println("Zwierzak skreca w lewo");
                case RIGHT -> System.out.println("Zwierzak skreca w prawo");
            }
        }
    }
    public static direction conversion(String command){
        return switch (command) {
            case "f" -> direction.FORWARD;
            case "b" -> direction.BACKWARD;
            case "l" -> direction.LEFT;
            case "r" -> direction.RIGHT;
            default -> direction.NONE;
        };
    }
}
