package agh.ics.oop;

import java.util.Objects;

public class Vector2d {
    private final float x;
    private final float y;

    public Vector2d(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "("+(int)x+","+(int)y+")";
    }

    public boolean precedes(Vector2d other) {
        return x <= other.x && y <= other.y;
    }

    public boolean follows(Vector2d other) {
        return x >= other.x && y >= other.y;
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(x + other.x, y + other.y);
    }

    public Vector2d substract(Vector2d other) {
        return new Vector2d(x - other.x, y - other.y);
    }

    public Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(x, other.x), Math.max(y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(Math.min(x, other.x), Math.min(y, other.y));
    }
    public Vector2d abs(){
        return new Vector2d(Math.abs(this.x),Math.abs(this.y));
    }
    public Vector2d rotRight(){
        return new Vector2d(this.y * -1,this.x );
    }
    public Vector2d opposite() {
        return new Vector2d(-x, -y);
    }
    public float distance(Vector2d v1){
        return (float) Math.sqrt(Math.pow(this.x- v1.getX(),2) + Math.pow(this.y-v1.getY(),2));
    }
    public float sqrMagnitude(){
        return (float) x*x+y*y;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Vector2d vector2dOther) {
            return (x == vector2dOther.x && y == vector2dOther.y);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return (int)x;
    }
    public float getFX() {
        return x;
    }

    public int getY() {
        return (int)y;
    }
    public float getFY() {
        return y;
    }
}
