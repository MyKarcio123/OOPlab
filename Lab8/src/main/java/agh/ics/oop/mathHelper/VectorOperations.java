package agh.ics.oop.mathHelper;

import org.joml.Vector3f;

public class VectorOperations {
    public static Vector3f substract(Vector3f a,Vector3f b){
        return new Vector3f(a.x-b.x,a.y-b.y,a.z-b.z);
    }
    public static Vector3f divide(Vector3f a,float b){
        return new Vector3f(a.x/b,a.y/b,a.z/b);
    }
    public static Vector3f add(Vector3f a,Vector3f b){
        return new Vector3f(a.x+b.x,a.y+b.y,a.z+b.z);
    }
}
