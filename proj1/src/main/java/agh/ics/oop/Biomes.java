package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

import static agh.ics.oop.BiomeType.*;

public class Biomes {
    private final float[][] temperature;
    private final float[][] humidity;
    private BiomeType[][] biomeType;
    private final List<Vector2d> icyFields = new ArrayList<>();
    private final List<Vector2d> snowyFields = new ArrayList<>();
    private final List<Vector2d> forestFields = new ArrayList<>();
    private final List<Vector2d> bagnoFields = new ArrayList<>();
    private final List<Vector2d> jungleFields = new ArrayList<>();
    private final List<Vector2d> desertFields = new ArrayList<>();
    private final BiomeType[][] biomeArray = {
            {ICY, ICY, ICY, SNOWY, SNOWY},
            {FOREST,FOREST,FOREST,FOREST,FOREST},
            {FOREST,FOREST,FOREST,BAGNO,BAGNO},
            {FOREST,JUNGLE,JUNGLE,JUNGLE,JUNGLE},
            {DESERT,DESERT,DESERT,DESERT,JUNGLE}
    };
    public Biomes(NoiseData temperature,NoiseData humidity,int minValue,int maxValue) {
        this.temperature = generateBiomeMap(temperature,minValue,maxValue);
        this.humidity = generateBiomeMap(humidity,minValue,maxValue);
        assignBiomes();
    }
    private void assignBiomes(){
        biomeType = new BiomeType[temperature.length][temperature[0].length];
        for(int i=0;i<temperature.length;++i){
            for(int j=0;j<humidity[0].length;++j){
                BiomeType type = biomeArray[(int) temperature[i][j]][(int) humidity[i][j]];
                biomeType[i][j]=type;
                addCoordsToList(type,new Vector2d(i,j));
            }
        }
    }
    private float[][] generateBiomeMap(NoiseData noiseData,int minValue,int maxValue){
        int seed = RandomPosition.getRandomSeed();
        float minNoise = Float.MIN_VALUE;
        float maxNoise = Float.MIN_VALUE;
        float[][] noiseMap = new float[noiseData.getWidth()][noiseData.getHeight()];
        PerlinNoise noise = new PerlinNoise(seed, noiseData.getPersistence(), noiseData.getFrequency(), 1, noiseData.getOctaves(), noiseData.getScale());
        for(int i=0;i<noiseData.getWidth();++i){
            for(int j=0;j<noiseData.getHeight();++j){
                float noiseValue = (float) noise.getHeight(i,j);
                minNoise=Math.min(minNoise,noiseValue);
                maxNoise=Math.max(maxNoise,noiseValue);
                noiseMap[i][j] = noiseValue;
            }
        }
        for(int i=0;i<noiseData.getWidth();++i){
            for(int j=0;j<noiseData.getHeight();++j){
                noiseMap[i][j]=MathHelper.Clamp(minValue,maxValue,MathHelper.InvLerp(minNoise,maxNoise,noiseMap[i][j])*(maxValue+1));
            }
        }
        return noiseMap;
    }
    private void addCoordsToList(BiomeType type, Vector2d coords){
        if(type==SNOWY){snowyFields.add(coords);}
        else if(type==ICY){icyFields.add(coords);}
        else if(type==FOREST){forestFields.add(coords);}
        else if(type==BAGNO){bagnoFields.add(coords);}
        else if(type==JUNGLE){jungleFields.add(coords);}
        else{desertFields.add(coords);}
    }
    public BiomeType getBiomeTypeAt(Vector2d position) {
        return biomeType[position.getX()-1][position.getY()-1];
    }
    public List<Vector2d> getIcyFields() {
        return icyFields;
    }

    public List<Vector2d> getSnowyFields() {
        return snowyFields;
    }

    public List<Vector2d> getForestFields() {
        return forestFields;
    }

    public List<Vector2d> getBagnoFields() {
        return bagnoFields;
    }

    public List<Vector2d> getJungleFields() {
        return jungleFields;
    }

    public List<Vector2d> getDesertFields() {
        return desertFields;
    }

}
