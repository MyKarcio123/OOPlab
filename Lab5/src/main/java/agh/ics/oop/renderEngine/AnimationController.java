package agh.ics.oop.renderEngine;

import agh.ics.oop.entities.Entity;
import agh.ics.oop.mathHelper.VectorOperations;
import org.joml.Vector3f;

import static agh.ics.oop.mathHelper.VectorOperations.*;
import static java.lang.Math.abs;

public class AnimationController {
    private Entity entity;
    private Vector3f transform;
    private Vector3f rotation;
    private boolean atAnimation;
    private final float epsilon = 0.02f;

    private Vector3f dirTransform;
    private Vector3f dirRotation;

    public boolean isAtAnimation() {
        return atAnimation;
    }

    public AnimationController(){
        atAnimation = false;
    }
    public void makeAnimation(Entity entity,Vector3f localTransform, Vector3f rotation,float tratio){
        float ratio=60f;
        this.entity = entity;
        this.transform = localTransform;
        this.rotation = rotation;
        dirTransform = divide(substract(transform,entity.getPosition()),ratio);
        dirRotation = divide(substract(rotation,entity.getRotation()),ratio);
        atAnimation=true;
    }
    public void performCurrentAnimation(){
        if(atAnimation){
            entity.increasePosition(dirTransform);
            entity.increaseRotation(dirRotation);
            if(comparator(entity.getPosition(),transform) && comparator(entity.getRotation(),rotation)){
                atAnimation=false;
                entity.setPosition(transform);
                entity.setRotation(rotation);
            }
        }
    }
    private boolean comparator(Vector3f current,Vector3f dest){
        Vector3f result = substract(current,dest);
        return (abs(result.x)<epsilon && abs(result.y)<epsilon && abs(result.z)<epsilon);
    }

}
