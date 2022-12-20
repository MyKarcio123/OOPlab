package agh.ics.oop;

public class NoiseData {
    private final int width;
    private final int height;
    private final double persistence;
    private final double frequency;
    private final int octaves;
    private final float scale;

    public NoiseData(int width, int height, double persistence, double frequency, int octaves, float scale) {
        this.width = width;
        this.height = height;
        this.persistence = persistence;
        this.frequency = frequency;
        this.octaves = octaves;
        this.scale = scale;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getPersistence() {
        return persistence;
    }

    public double getFrequency() {
        return frequency;
    }

    public int getOctaves() {
        return octaves;
    }

    public float getScale() {
        return scale;
    }
}
