package agh.ics.oop.graphAlgorithms;

import agh.ics.oop.Vector2d;

import java.util.Objects;

public class Edge {
    public Vector2d u;
    public Vector2d v;
    public float weight;
    public boolean isBad;

    public Edge(){}

    public Edge(Vector2d u, Vector2d v){
        this.u = u;
        this.v = v;
        this.weight=u.distance(v);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Edge edgeOther) {
            return (this.v.equals(edgeOther.v) || this.v.equals(edgeOther.u)) &&
                    (this.u.equals(edgeOther.v) || this.u.equals(edgeOther.u));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return u.hashCode()+v.hashCode();
    }

    @Override
    public String toString() {
        return "(" + u.getX() + "," + u.getY() + ") (" + v.getX() + "," + v.getY() + ")";
    }
}
