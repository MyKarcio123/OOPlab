package agh.ics.oop.rooms;

import agh.ics.oop.RoomType;
import agh.ics.oop.Vector2d;
import agh.ics.oop.graphAlgorithms.AStar;
import agh.ics.oop.graphAlgorithms.DelonayTriangulation;
import agh.ics.oop.graphAlgorithms.Edge;
import agh.ics.oop.graphAlgorithms.MST;

import java.util.*;
import java.util.stream.Collectors;

public class RoomMap {
    private final Map<Vector2d,Room> levelMap = new HashMap<>();
    private Map<Vector2d, RoomType> numMap = new HashMap<>();
    private final Vector2d lowerLeft = new Vector2d(0,0);
    private final List<Vector2d> position = new ArrayList<>();
    private final List<Vector2d> centers = new ArrayList<>();
    private final Vector2d upperRight;
    private float[][] mapGraph;
    private final int roomAmount;
    private final int mapSize;
    private Vector2d bossRoom;
    private Vector2d playerPos;

    public int getMapSize() {
        return mapSize;
    }

    // zakładam że funkcja zależności poziomu od ilości pomieszczeń powinna mieć przyrost maksymalnie liniowy
    public RoomMap(int level){
        roomAmount = (int)Math.ceil((3.1*level+2.5));
        mapSize = roomAmount*4;
        upperRight = new Vector2d(mapSize+2,mapSize+2);
        for(int i=2;i<mapSize-2;++i){
            for(int j=2;j<mapSize-2;++j){
                position.add(new Vector2d(i,j));
            }
        }
        Collections.shuffle(position);
        GenerateRooms();
        DelonayTriangulation triangulation = new DelonayTriangulation();
        List<Edge> edges = triangulation.DelonayTriangulation(centers);
        List<Edge> newEdges = MST.Kruskal(edges,roomAmount,centers);
        List<Edge> deletes = new ArrayList<>(edges.stream().filter(element -> !newEdges.contains(element)).toList());
        Collections.shuffle(deletes);
        int deleteSize = deletes.size();
        deletes = deletes.subList(0,(int)(Math.ceil(deleteSize*0.3))+1);
        newEdges.addAll(deletes);
        makeGraph(newEdges);
        numMap = AStar.AStar(newEdges,numMap,upperRight);
        putPlayer();

    }
    private void putPlayer(){
        float distance = 0;
        Vector2d localPlayer = new Vector2d(0,0);
        for(Vector2d center : centers){
            float newDistance = calculateDistance(center,bossRoom);
            if(newDistance>distance){
                distance = newDistance;
                localPlayer = center;
            }
        }
        Vector2d[] moves = {new Vector2d(2,0),new Vector2d(-2,0),new Vector2d(0,-2),new Vector2d(0,2)};
        for(Vector2d move : moves){
            if(numMap.getOrDefault(localPlayer.add(move),RoomType.UNWALKABLE)==RoomType.CORRIDOR){
                playerPos=localPlayer.add(move);
                numMap.replace(playerPos,RoomType.PLAYER);
                break;
            }
        }
    }
    private void GenerateRooms(){
        int iter = 0;
        Vector2d[] moves = {new Vector2d(2,2),new Vector2d(-2,2),new Vector2d(-2,-2),new Vector2d(2,-2),
                new Vector2d(2,0),new Vector2d(-2,0),new Vector2d(0,-2),new Vector2d(0,2)};
        for(int i=0;i<roomAmount;++i){
            boolean goodPosition;
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
                    fillRoomInMap(possiblePlace,iter);
                    iter+=1;
                }
                position.remove(0);
            }while(!goodPosition);
        }
    }
    private void fillRoomInMap(Vector2d center,int iter){
        RoomType currentType = RoomType.ROOM;
        if(iter==0) {
            currentType = RoomType.BOSS;
            bossRoom = center;
        }
        else if(iter==1) currentType = RoomType.SHOP;
        levelMap.put(center,new Room());
        centers.add(center);
        for(int k=center.getX()-1;k<=center.getX()+1;++k){
            for(int l=center.getY()-1;l<=center.getY()+1;++l){
                if(k!=center.getX() && l!=center.getY()){
                    numMap.put(new Vector2d(k,l),RoomType.UNWALKABLE);
                }
                else if(k==center.getX() && l== center.getY()){
                    numMap.put(new Vector2d(k,l),RoomType.UNWALKABLE);
                }
                else {
                    numMap.put(new Vector2d(k, l), currentType);
                }
            }
        }
    }
    private void makeGraph(List<Edge> edges){
        mapGraph = new float[mapSize][mapSize];
        for (Edge edge : edges){
            float dist = edge.u.distance(edge.v);
            mapGraph[edge.u.getX()][edge.u.getY()]=dist;
            mapGraph[edge.v.getX()][edge.v.getY()]=dist;
        }

    }
    public void movePlayer(){

    }
    public RoomType haveRoom(Vector2d position){
        if(!numMap.containsKey(position)) return RoomType.UNWALKABLE;
        return numMap.get(position);
    }
    private float calculateDistance(Vector2d from, Vector2d to){
        return (float)(Math.pow((from.getX()-to.getX()),2)+Math.pow((from.getY()-to.getY()),2));
    }
}
