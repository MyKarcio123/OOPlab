package agh.ics.oop.entities;

import agh.ics.oop.Vector2d;
import agh.ics.oop.models.TexturedModel;
import org.joml.Vector3f;

public abstract class Entity {
    private TexturedModel model;
    private Vector3f position;
    private float rotX,rotY,rotZ;
    private float scale;

    public Entity(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
        this.model = model;
        this.position = position;
        this.rotX = rotX;
        this.rotY = rotY;
        this.rotZ = rotZ;
        this.scale = scale;
    }
    public Entity(TexturedModel model){
        this.model = model;
        this.position = new Vector3f(0,1,0);
        this.rotX = 0;
        this.rotY = 45;
        this.rotZ = 0;
        this.scale = 1f;
    }
    public Entity(Vector2d positionOnPlane){
        this.position = new Vector3f(positionOnPlane.getX(),1,positionOnPlane.getY());
        this.rotX = 0;
        this.rotY = 45;
        this.rotZ = 0;
        this.scale = 1f;
    }

    public void increasePosition(float dx, float dy, float dz){
        this.position.x+=dx;
        this.position.y+=dy;
        this.position.z+=dz;
    }

    public void increaseRotation(float dx, float dy, float dz){
        this.rotX+=dx;
        this.rotY+=dy;
        this.rotZ+=dz;
    }
    public TexturedModel getModel() {
        return model;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getRotX() {
        return rotX;
    }

    public void setRotX(float rotX) {
        this.rotX = rotX;
    }

    public float getRotY() {
        return rotY;
    }

    public void setRotY(float rotY) {
        this.rotY = rotY;
    }

    public float getRotZ() {
        return rotZ;
    }

    public void setRotZ(float rotZ) {
        this.rotZ = rotZ;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
