package agh.ics.oop.graphAlgorithms;

import agh.ics.oop.RoomType;
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
        return (int)Math.ceil(this.priority-o.priority);
    }
}
public class AStar {
    public static Map<Vector2d,RoomType> AStar(List<Edge> edgeList, Map<Vector2d, RoomType> numMap, Vector2d upperBound){
        System.out.println(edgeList);
        for(Edge edge : edgeList){
            Map<Vector2d,Vector2d> visited = new HashMap<>();
            Vector2d to = edge.v;
            Vector2d from = edge.u;
            //Vector2d from = calculateBestStartingPoint(edge.u,to);
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
                    if(!visited.containsKey(check) && numMap.getOrDefault(check,RoomType.CORRIDOR)!=RoomType.UNWALKABLE){
                        if(numMap.containsKey(check)) queue.add(new pqElement(check,(float)(value+(mannhatanDistance(check,to)*0.2))));
                        else queue.add(new pqElement(check,value+1+mannhatanDistance(check,to)));
                        visited.put(check,current);
                    }
                    if(check.equals(to)){
                        visited.put(check,current);
                        queue.clear();
                        break;
                    }
                }
            }
            Vector2d currentChecking = to;
            while(true){
                if(!numMap.containsKey(currentChecking)) numMap.put(currentChecking, RoomType.CORRIDOR);
                currentChecking = visited.get(currentChecking);
                if(currentChecking.equals(from)) break;
            }
        }
        return numMap;
    }
    private static Vector2d calculateBestStartingPoint(Vector2d from, Vector2d to){
        float minDist = Integer.MAX_VALUE;
        Vector2d currentStart = new Vector2d(from.getX(), from.getY());
        Vector2d[] moves = {new Vector2d(1,0),new Vector2d(-1,0),new Vector2d(0,-1),new Vector2d(0,1)};
        for(Vector2d move : moves){
            Vector2d newFrom = from.add(move);
            if(minDist > mannhatanDistance(newFrom,to)){
                currentStart = newFrom;
                minDist = mannhatanDistance(newFrom,to);
            }
        }
        return currentStart;
    }
    private static float mannhatanDistance(Vector2d from, Vector2d to){
        return (float) (Math.pow(from.getX()-to.getX(),2) + Math.pow(from.getY()-to.getY(),2));
        //return Math.abs(from.getX()-to.getX())+Math.abs(from.getY()-to.getY());
    }
}
