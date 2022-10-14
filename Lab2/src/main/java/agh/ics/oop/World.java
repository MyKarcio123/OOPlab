package agh.ics.oop;
public class    World {
    public static void main(String[] args){
        System.out.println("System wystartował");
        MoveDirection[] arguments = new MoveDirection[args.length];
        for(int i=0;i<args.length;++i){
            arguments[i]=conversion(args[i]);
        }
        run(arguments);
        System.out.println("System zakończył działanie");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
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
