package agh.ics.oop.graphAlgorithms;

import agh.ics.oop.Vector2d;

public class Triangle {
    public Vector2d a;
    public Vector2d b;
    public Vector2d c;
    public boolean isBad;

    public Triangle(){}
    public Triangle(Vector2d a, Vector2d b, Vector2d c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean ContainsVertex(Vector2d v){
        return v.distance(a) < 0.01f || v.distance(b) < 0.01f || v.distance(c) < 0.01f;
    }

    public boolean CircumCircleContains(Vector2d v){
        float ab = a.sqrMagnitude();
        float cd = b.sqrMagnitude();
        float ef = c.sqrMagnitude();

        float circumX = (ab * (c.getFY() - b.getFY()) + cd * (a.getFY() - c.getFY()) + ef * (b.getFY() - a.getFY())) / (a.getFX() * (c.getFY() - b.getFY()) + b.getFX() * (a.getFY() - c.getFY()) + c.getFX() * (b.getFY() - a.getFY()));
        float circumY = (ab * (c.getFX() - b.getFX()) + cd * (a.getFX() - c.getFX()) + ef * (b.getFX() - a.getFX())) / (a.getFY() * (c.getFX() - b.getFX()) + b.getFY() * (a.getFX() - c.getFX()) + c.getFY() * (b.getFX() - a.getFX()));

        Vector2d circum = new Vector2d(circumX / 2,circumY / 2);
        Vector2d radiousVec = a.substract(circum);
        float circumRadious = radiousVec.sqrMagnitude();
        Vector2d distanceVec = v.substract(circum);
        float dist = distanceVec.sqrMagnitude();

        return dist <= circumRadious;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Triangle triangleOther) {
            return (this.a==triangleOther.a || this.a==triangleOther.b || this.a==triangleOther.c) &&
            (this.b==triangleOther.a || this.b==triangleOther.b || this.b==triangleOther.c) &&
            (this.c==triangleOther.a || this.c==triangleOther.b || this.c==triangleOther.c);
        }
        return false;
    }

}
