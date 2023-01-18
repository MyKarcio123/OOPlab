package agh.ics.oop.graphAlgorithms;

import agh.ics.oop.Vector2d;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DelonayTriangulation {
    private List<Vector2d> vertices;
    private List<Edge> edges;
    private List<Triangle> triangles;

    public List<Edge> DelonayTriangulation(List<Vector2d> vertices){
        edges = new ArrayList<>();
        triangles = new ArrayList<>();
        this.vertices = vertices;
        triangulate();
        return edges;
    }

    private void triangulate(){
        float minX = vertices.get(0).getX();
        float minY = vertices.get(0).getY();
        float maxX = minX;
        float maxY = minY;

        for (Vector2d vertex : vertices){
            if (vertex.getX() < minX ) minX = vertex.getX();;
            if (vertex.getX() > maxX ) maxX = vertex.getX();;
            if (vertex.getX() < minY ) minY = vertex.getX();;
            if (vertex.getX() > maxY ) maxY = vertex.getX();;
        }

        float dx = maxX - minX;
        float dy = maxY - minY;
        float deltaMax = Math.max(dx,dy)*2;

        Vector2d p1 = new Vector2d(minX-1,minY-1);
        Vector2d p2 = new Vector2d(minX-1,maxY+deltaMax);
        Vector2d p3 = new Vector2d(maxX+deltaMax,minY-1);

        triangles.add(new Triangle(p1,p2,p3));

        for (Vector2d vertex : vertices){
            List<Edge> polygon = new ArrayList<>();

            for (Triangle t : triangles){
                if(t.CircumCircleContains(vertex)){
                    t.isBad = true;
                    polygon.add(new Edge(t.a,t.b));
                    polygon.add(new Edge(t.b,t.c));
                    polygon.add(new Edge(t.c,t.a));
                }
            }

            triangles.removeIf(triangle -> triangle.isBad);

            for (int i = 0; i<polygon.size();++i){
                for (int j = i + 1; j<polygon.size();++j){
                    if(polygon.get(i).equals(polygon.get(j))){
                        polygon.get(i).isBad = true;
                        polygon.get(j).isBad = true;
                    }
                }
            }

            polygon.removeIf(edge -> edge.isBad);

            for (Edge edge : polygon){
                triangles.add(new Triangle(edge.u,edge.v,vertex));
            }
        }

        triangles.removeIf(triangle -> triangle.ContainsVertex(p1) || triangle.ContainsVertex(p2) || triangle.ContainsVertex(p3));

        Set<Edge> edgeSet = new HashSet<>();

        for (Triangle t : triangles){
            Edge ab = new Edge(t.a,t.b);
            Edge bc = new Edge(t.b,t.c);
            Edge ca = new Edge(t.c,t.a);

            if(edgeSet.add(ab)){
                edges.add(ab);
            }

            if(edgeSet.add(bc)){
                edges.add(bc);
            }

            if(edgeSet.add(ca)){
                edges.add(ca);
            }
        }
    }

}
