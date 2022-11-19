package agh.ics.oop.renderEngine;

import agh.ics.oop.*;
import agh.ics.oop.entities.Entity;
import agh.ics.oop.entities.FaceType;
import agh.ics.oop.entities.Object;
import agh.ics.oop.entities.VoxelData;
import agh.ics.oop.models.RawModel;
import agh.ics.oop.models.TexturedModel;
import org.joml.Vector3f;
import agh.ics.oop.models.Texture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static agh.ics.oop.mathHelper.ListOperations.toFloatArray;

public class Terrain {
    private static List<Float> vertices;
    private static List<Float> uvs;
    private static List<Integer> indices;

    // maksymalnie 5 ścian sześcianu, górna jest obowiązkowa w każdym sześcianie, pozostałe tylko na krawędziach,
    public static Entity makeTerrain(IWorldMap map, Texture terrainTexture, Loader loader){
        vertices = new ArrayList<Float>();
        uvs = new ArrayList<Float>();
        indices = new ArrayList<Integer>();
        Vector2d leftDownCorner = map.getLeftBottomCorner();
        Vector2d rightTopCorner = map.getRightTopCorner();
        for(int i=leftDownCorner.getY();i<rightTopCorner.getY()+1;++i){
            for(int j=leftDownCorner.getX();j<rightTopCorner.getX()+1;++j){
                //TOP FACE
                AddFaceToTerrain(new Vector3f(j,0,i),FaceType.TOP,map);
                //LEFT FACE
                if(j == leftDownCorner.getX()){
                    AddFaceToTerrain(new Vector3f(j,0,i),FaceType.LEFT,map);
                }
                //RIGHT FACE
                if(j == rightTopCorner.getX()){
                    AddFaceToTerrain(new Vector3f(j,0,i),FaceType.RIGHT,map);
                }
                //FRONT FACE
                if(i == leftDownCorner.getY()){
                    AddFaceToTerrain(new Vector3f(j,0,i),FaceType.FRONT,map);
                }
                //BACK FACE
                if(i == rightTopCorner.getY()){
                    AddFaceToTerrain(new Vector3f(j,0,i),FaceType.BACK,map);
                }
            }
        }
        float[] verticesArray = toFloatArray(vertices);
        float[] uvsArray = toFloatArray(uvs);
        int[] indicesArray = indices.stream().mapToInt(Integer::intValue).toArray();
        RawModel terrainModel = loader.loadToVAO(verticesArray,uvsArray,indicesArray);
        TexturedModel texturedModel = new TexturedModel(terrainModel,terrainTexture);
        System.out.println("MAP POSITION : " + leftDownCorner.getX() + "  " + leftDownCorner.getY());
        return new Object(texturedModel,new Vector3f(0,0,0),0,0,0,1);
    }

    private static void AddFaceToTerrain(Vector3f position, FaceType faceType,IWorldMap map){
        int vertexIndex=vertices.size()/3;
        for(int i=0;i<4;++i) {
            int index=i;
            if (index == 3) {
                index=5;
            }
            Vector3f vertex = VoxelData.voxelVerts[VoxelData.faces[faceType.ordinal()][index]];
            vertices.add(vertex.x+position.x);
            vertices.add(vertex.y);
            vertices.add(vertex.z+position.z);
        }
        indices.add(0+vertexIndex);
        indices.add(1+vertexIndex);
        indices.add(2+vertexIndex);
        indices.add(2+vertexIndex);
        indices.add(1+vertexIndex);
        indices.add(3+vertexIndex);
        if(faceType==FaceType.TOP) {
            if(map.isGrass(new Vector2d((int)position.x,(int)position.z))){
                //LEFT TOP
                uvs.add(0.25f);
                uvs.add(0f);
                //LEFT DOWN
                uvs.add(0.25f);
                uvs.add(0.33f);
                //RIGHT TOP
                uvs.add(0.50f);
                uvs.add(0f);
                //RIGHT DOWN
                uvs.add(0.50f);
                uvs.add(0.33f);
            }else{
                //LEFT TOP
                uvs.add(0.25f);
                uvs.add(0.66f);
                //LEFT DOWN
                uvs.add(0.25f);
                uvs.add(1f);
                //RIGHT TOP
                uvs.add(0.50f);
                uvs.add(0.66f);
                //RIGHT DOWN
                uvs.add(0.50f);
                uvs.add(1f);
            }
        }else{
            if(map.isGrass(new Vector2d((int)position.x,(int)position.z))){
                //LEFT TOP
                uvs.add(0.25f);
                uvs.add(1-0.33f);
                //LEFT DOWN
                uvs.add(0.25f);
                uvs.add(1-0.66f);
                //RIGHT TOP
                uvs.add(0.50f);
                uvs.add(1-0.33f);
                //RIGHT DOWN
                uvs.add(0.50f);
                uvs.add(1-0.66f);
            }else{
                //LEFT TOP
                uvs.add(0.25f);
                uvs.add(0.66f);
                //LEFT DOWN
                uvs.add(0.25f);
                uvs.add(1f);
                //RIGHT TOP
                uvs.add(0.50f);
                uvs.add(0.66f);
                //RIGHT DOWN
                uvs.add(0.50f);
                uvs.add(1f);
            }
        }
    }
}
