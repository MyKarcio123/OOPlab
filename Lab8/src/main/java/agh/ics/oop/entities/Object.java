package agh.ics.oop.entities;

import agh.ics.oop.models.TexturedModel;
import org.joml.Vector3f;

public class Object extends Entity{
    public Object(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale){
        super(model, position, rotX, rotY, rotZ, scale);
    }
}
