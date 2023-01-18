package agh.ics.oop.rooms;

import agh.ics.oop.Vector2d;
import agh.ics.oop.graphAlgorithms.DelonayTriangulation;
import agh.ics.oop.graphAlgorithms.Edge;
import agh.ics.oop.graphAlgorithms.MST;

import java.util.*;

public class RoomMap {
    private final Map<Vector2d,Room> levelMap = new HashMap<>();
    private final Map<Vector2d,Integer> numMap = new HashMap<>();
    private final Vector2d lowerLeft = new Vector2d(0,0);
    private final List<Vector2d> position = new ArrayList<>();
    private final List<Vector2d> centers = new ArrayList<>();
    private final Vector2d upperRight;
    private float[][] mapGraph;
    private final int roomAmount;


    // zakładam że funkcja zależności poziomu od ilości pomieszczeń powinna mieć przyrost maksymalnie liniowy
    public RoomMap(int level){
        roomAmount = (int)Math.ceil((3.1*level+2.5));
        int mapSize = roomAmount*4;
        upperRight = new Vector2d(mapSize+2,mapSize+2);
        for(int i=2;i<mapSize-2;++i){
            for(int j=2;j<mapSize-2;++j){
                position.add(new Vector2d(i,j));
            }
        }
        Collections.shuffle(position);
        GenerateRooms();
        DelonayTriangulation triangulation = new DelonayTriangulation();
        System.out.println(centers);
        List<Edge> edges = triangulation.DelonayTriangulation(centers);
        makeGraph(edges);
        MST.Kruskal(edges,roomAmount,centers);

    }
    private void GenerateRooms(){
        Vector2d[] moves = {new Vector2d(2,2),new Vector2d(-2,2),new Vector2d(-2,-2),new Vector2d(2,-2),
                new Vector2d(2,0),new Vector2d(-2,0),new Vector2d(0,-2),new Vector2d(0,2)};
        for(int i=0;i<roomAmount;++i){
            boolean goodPosition = true;
            do{
                goodPosition = true;
                Vector2d possiblePlace = position.get(0);
                for(Vector2d move : moves){
                    Vector2d placeToCheck = new Vector2d(possiblePlace.getX()+move.getX(),possiblePlace.getY()+move.getY());
                    if(numMap.containsKey(placeToCheck)){
                        goodPosition=false;
                        break;
                    }
                }
                if(goodPosition){
                    fillRoomInMap(possiblePlace);
                }
                position.remove(0);
            }while(!goodPosition);
        }
    }
    private void fillRoomInMap(Vector2d center){
        levelMap.put(center,new Room());
        centers.add(center);
        for(int k=center.getX()-1;k<=center.getX()+1;++k){
            for(int l=center.getY()-1;l<=center.getY()+1;++l){
                numMap.put(new Vector2d(k,l),1);
            }
        }
    }
    private void makeGraph(List<Edge> edges){
        mapGraph = new float[roomAmount*4][roomAmount*4];
        for (Edge edge : edges){
            float dist = edge.u.distance(edge.v);
            mapGraph[edge.u.getX()][edge.u.getY()]=dist;
            mapGraph[edge.v.getX()][edge.v.getY()]=dist;
        }
    }

}
