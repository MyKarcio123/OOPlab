package agh.ics.oop;

public class Parameters {
    public static int STARTING_ENERGY = 10;
    public static int STARTING_ANIMALS = 10;
    public static int NEXT_GEN_TYPE = 0;
    public static int GEN_LENGTH = 10;
    public static int ENERGY_VALUE_FROM_GRASS = 0;
    public static int HEIGHT_MAP = 100;
    public static int WIDTH_MAP = 100;
    public static int MINIMUM_COPULATE_ENERGY = 100;
    public static int COPULATE_ENERGY_DECREASE = 10;
    public static int MUTATION_VARIANT = 0;
    public static int MINIMUM_MUTATION = 0;
    public static int MAXIMUM_MUTATION = 10;
    public static int STARTING_GRASS = 10;
    public static int MAP_VARIANT = 0;
    public static int NEW_GRASS_PER_DAY = 1;
    public static int GRASS_GROW_VARIANT = 0;
    private static int ID = 0;
    static public int getID(){
        ID+=1;
        return ID-1;
    }
}
