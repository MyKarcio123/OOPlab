package agh.ics.oop.models;

import agh.ics.oop.models.RawModel;
import agh.ics.oop.models.Texture;

public class TexturedModel {
    private RawModel rawModel;
    private Texture texture;

    public TexturedModel(RawModel model,Texture texture){
        this.rawModel = model;
        this.texture = texture;
    }

    public RawModel getRawModel() {
        return rawModel;
    }

    public Texture getTexture() {
        return texture;
    }
}
