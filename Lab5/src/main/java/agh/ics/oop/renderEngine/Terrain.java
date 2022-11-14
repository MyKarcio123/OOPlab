package agh.ics.oop.renderEngine;

import agh.ics.oop.IEngine;
import agh.ics.oop.IWorldMap;
import agh.ics.oop.RectangularMap;
import agh.ics.oop.Vector2d;
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
    private static List<Float> vertices = new ArrayList<Float>();
    private static List<Float> uvs = new ArrayList<Float>();
    private static List<Integer> indices = new ArrayList<Integer>();
    // maksymalnie 5 ścian sześcianu, górna jest obowiązkowa w każdym sześcianie, pozostałe tylko na krawędziach,
    public static Entity makeTerrain(RectangularMap map, Texture terrainTexture, Loader loader){
        Vector2d leftDownCorner = map.getLeftBottomCorner();
        Vector2d rightTopCorner = map.getRightTopCorner();
        for(int i=leftDownCorner.getY();i<=rightTopCorner.getY();++i){
            for(int j=leftDownCorner.getX();j<=rightTopCorner.getX();++j){
                //TOP FACE
                AddFaceToTerrain(new Vector3f(j,0,i),FaceType.TOP);
                //LEFT FACE
                if(j == leftDownCorner.getX()){
                    AddFaceToTerrain(new Vector3f(j,0,i),FaceType.LEFT);
                }
                //RIGHT FACE
                if(j == rightTopCorner.getX()){
                    AddFaceToTerrain(new Vector3f(j,0,i),FaceType.RIGHT);
                }
                //FRONT FACE
                if(i == leftDownCorner.getY()){
                    AddFaceToTerrain(new Vector3f(j,0,i),FaceType.FRONT);
                }
                //BACK FACE
                if(i == rightTopCorner.getY()){
                    AddFaceToTerrain(new Vector3f(j,0,i),FaceType.BACK);
                }
            }
        }
        float[] verticesArray = toFloatArray(vertices);
        float[] uvsArray = toFloatArray(uvs);
        int[] indicesArray = indices.stream().mapToInt(Integer::intValue).toArray();
        RawModel terrainModel = loader.loadToVAO(verticesArray,uvsArray,indicesArray);
        TexturedModel texturedModel = new TexturedModel(terrainModel,terrainTexture);
        return new Object(texturedModel,new Vector3f(leftDownCorner.getX(),0,leftDownCorner.getY()),0,0,0,1);
    }

    private static void AddFaceToTerrain(Vector3f position, FaceType faceType){
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
        }
    }
}
