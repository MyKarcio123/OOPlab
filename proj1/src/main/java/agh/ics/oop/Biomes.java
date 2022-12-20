package agh.ics.oop;

import static agh.ics.oop.BiomeType.*;

public class Biomes {
    private final float[][] temperature;
    private final float[][] humidity;
    private BiomeType[][] biomeType;

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
        biomeType = new BiomeType[temperature.length][temperature.length];
        for(int i=0;i<temperature.length;++i){
            for(int j=0;j<humidity.length;++j){
                biomeType[i][j]=biomeArray[(int) temperature[i][j]][(int) humidity[i][j]];
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

    public BiomeType getBiomeTypeAt(Vector2d position) {
        return biomeType[position.getX()][position.getY()];
    }

}
