package agh.ics.oop;

import java.util.Objects;

public class Vector2d {
    private final int x,y;
    private int hashCode;

    public Vector2d(int x,int y){
        this.x=x;
        this.y=y;
        this.hashCode = Objects.hash(x, y);
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    @Override
    public String toString() {
        return "("+x+","+y+")";
    }

    public boolean precedes(Vector2d other){
        return (this.x<=other.x && this.y<=other.y);
    }

    public boolean follows(Vector2d other){
        return (this.x>=other.x && this.y>=other.y);
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(this.x+other.x,this.y+other.y);
    }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x-other.x,this.y-other.y);
    }

    public Vector2d upperRight(Vector2d other){
        return new Vector2d(Math.max(this.x, other.x),Math.max(this.y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other){
        return new Vector2d(Math.min(this.x,other.x),Math.min(this.y,other.y));
    }

    public Vector2d opposite(){
        return new Vector2d(this.x*-1,this.y*-1);
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Vector2d vector2dOther){
            return(this.x==vector2dOther.x && this.y==vector2dOther.y);
        }
        return false;
    }
    @Override
    public int hashCode() {
        return this.hashCode;
    }
}
