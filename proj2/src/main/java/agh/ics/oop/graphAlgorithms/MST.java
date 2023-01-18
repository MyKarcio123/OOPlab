package agh.ics.oop.graphAlgorithms;

import agh.ics.oop.Vector2d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
class Subset {
    int parent, rank;
};
public class MST {
    public static void Kruskal(List<Edge> edges,int roomAmount,List<Vector2d> rooms){
        List<Edge> result = new ArrayList<>();
        edges.sort((o1, o2) -> (int) (o1.weight - o2.weight));
        Subset[] subsets = new Subset[rooms.size()];
        for(int i=0;i<rooms.size();++i){
            subsets[i]=new Subset();
            subsets[i].parent=i;
            subsets[i].rank=0;
        }
        int i=0;
        int e=0;
        while(e<roomAmount-1){
            Edge currentEdge = edges.get(i);
            ++i;
            int x = find(subsets,rooms.indexOf(currentEdge.u));
            int y = find(subsets,rooms.indexOf(currentEdge.v));

            if(x!=y){
                result.add(currentEdge);
                ++e;
                Union(subsets,x,y);
            }
        }
        System.out.println(result);
    }
    static int find(Subset[] subsets,int i)
    {
        // find root and make root as parent of i
        // (path compression)
        if (subsets[i].parent != i)
            subsets[i].parent
                    = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }

    // A function that does union of two sets
    // of x and y (uses union by rank)
    static void Union(Subset[] subsets, int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        // Attach smaller rank tree under root
        // of high rank tree (Union by Rank)
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;

            // If ranks are same, then make one as
            // root and increment its rank by one
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }
}
