package agh.ics.oop.graphAlgorithms;

import agh.ics.oop.Vector2d;

import java.util.*;

class pqElement implements Comparable<pqElement>{
    float priority;
    Vector2d value;

    public pqElement(Vector2d value,float priority) {
        this.priority = priority;
        this.value = value;
    }

    @Override
    public int compareTo(pqElement o) {
        return (int)Math.ceil(o.priority-this.priority);
    }
}
public class AStar {
    public static Map<Vector2d,Integer> AStar(List<Edge> edgeList,Map<Vector2d,Integer> numMap,Vector2d upperBound){
        for(Edge edge : edgeList){
            Map<Vector2d,Vector2d> visited = new HashMap<>();
            Vector2d from = edge.u;
            Vector2d to = edge.v;
            PriorityQueue<pqElement> queue = new PriorityQueue<>();
            queue.add(new pqElement(from,0));
            Vector2d[] moves = {new Vector2d(1,0),new Vector2d(-1,0),new Vector2d(0,-1),new Vector2d(0,1)};
            while(!queue.isEmpty()){
                pqElement currentElement = queue.poll();
                Vector2d current = currentElement.value;
                float value = currentElement.priority;
                for(Vector2d move : moves){
                    Vector2d check = current.add(move);
                    if(!(check.upperRight(upperBound).equals(upperBound) && check.lowerLeft(new Vector2d(0, 0)).equals(new Vector2d(0, 0)))) continue;
                    if(!visited.containsKey(check)){
                        queue.add(new pqElement(check,value+1+mannhatanDistance(check,to)));
                        visited.put(check,current);
                    }
                    if(check.equals(to)){
                        queue.clear();
                        break;
                    }
                }
            }
            Vector2d currentChecking = to;
            while(true){
                if(!numMap.containsKey(currentChecking)) numMap.put(currentChecking,2);
                currentChecking = visited.get(currentChecking);
                if(currentChecking.equals(from)) break;
            }
        }
        return numMap;
    }
    private static float mannhatanDistance(Vector2d from, Vector2d to){
        return Math.abs(from.getX()-to.getX())+Math.abs(from.getY()-to.getY());
    }
}
