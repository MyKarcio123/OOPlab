package agh.ics.oop;

import java.util.List;

public class DataParameters {
    private final List<String> currentConfig;

    public DataParameters(List<String> currentConfig) {
        this.currentConfig = currentConfig;
    }

    public String getName(){
        return currentConfig.get(0);
    }
    public int getHeight(){
        return Integer.parseInt(currentConfig.get(1));
    }
    public int getWidth(){
        return Integer.parseInt(currentConfig.get(2));
    }
    public int getStartingGrass(){
        return Integer.parseInt(currentConfig.get(3));
    }
    public int getEnergyFromGrass(){
        return Integer.parseInt(currentConfig.get(4));
    }
    public int getNewGrassPerDay(){
        return Integer.parseInt(currentConfig.get(5));
    }
    public int getStartingAnimals(){
        return Integer.parseInt(currentConfig.get(6));
    }
    public int getStratingAnimalsEnergy(){
        return Integer.parseInt(currentConfig.get(7));
    }
    public int getMinimumCopulateEnergy(){
        return Integer.parseInt(currentConfig.get(8));
    }
    public int getCopulateEnergyDecrease(){
        return Integer.parseInt(currentConfig.get(9));
    }
    public int getMinimumMutation(){
        return Integer.parseInt(currentConfig.get(10));
    }
    public int getMaximumMutation(){
        return Integer.parseInt(currentConfig.get(11));
    }
    public int getGenomeLength(){
        return Integer.parseInt(currentConfig.get(12));
    }
    public int getMapVariant(){
        return Integer.parseInt(currentConfig.get(13));
    }
    public int getGrassGrowVariant(){
        return Integer.parseInt(currentConfig.get(14));
    }
    public int getMutationVariant(){
        return Integer.parseInt(currentConfig.get(15));
    }
    public int getNextGenType(){
        return Integer.parseInt(currentConfig.get(16));
    }
    public int getWalkEnergyDecrease(){return Integer.parseInt("1");}
    public int getMoveDelay(){return Integer.parseInt("500");}
    private static int ID = 0;
    static public int getDataID(){
        ID+=1;
        return ID-1;
    }

}
