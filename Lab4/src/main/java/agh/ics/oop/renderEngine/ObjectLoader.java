package agh.ics.oop.renderEngine;

import agh.ics.oop.Vector2d;
import agh.ics.oop.models.RawModel;
import agh.ics.oop.models.Texture;
import agh.ics.oop.models.TexturedModel;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;

import static agh.ics.oop.mathHelper.ListOperations.toFloatArray;

public class ObjectLoader {
    private static int index=0;

    public static TexturedModel[] loadObjModels(String[] objetcsPaths,Texture[] textures, Loader loader){
        int index=0;
        TexturedModel[] models = new TexturedModel[textures.length];
        for(String object : objetcsPaths){
            System.out.println(index);
            models[index]= new TexturedModel(loadObjModel(object,loader),textures[index]);
            ++index;
        }

        return models;
    }
    public static RawModel loadObjModel(String fileName, Loader loader){
        FileReader fr = null;
        try{
            fr = new FileReader("res/"+fileName+".obj");
        }catch (FileNotFoundException e){
            System.err.println("Couldn't load file!");
        }
        BufferedReader reader = new BufferedReader(fr);
        List<Vector3f> verticesList = new ArrayList<Vector3f>();
        List<Vector2f> uvsList = new ArrayList<Vector2f>();
        List<Vector3f> normalsList = new ArrayList<Vector3f>();
        List<Float> vertices = new ArrayList<Float>();
        List<Integer> indices = new ArrayList<Integer>();
        List<Float> uvs = new ArrayList<Float>();
        List<Float> normals = new ArrayList<Float>();
        try {
            String line = reader.readLine();
            while(!line.startsWith("f ")){
                String[] currentLine = line.split("\\s+");
                if(line.startsWith("v ")){
                    Vector3f element = new Vector3f(Float.parseFloat(currentLine[1]),Float.parseFloat(currentLine[2]),Float.parseFloat(currentLine[3]));
                    verticesList.add(element);
                }
                else if(line.startsWith("vn ")){
                    Vector3f element = new Vector3f(Float.parseFloat(currentLine[1]),Float.parseFloat(currentLine[2]),Float.parseFloat(currentLine[3]));
                    normalsList.add(element);
                }
                else if(line.startsWith("vt ")){
                    Vector2f element = new Vector2f(Float.parseFloat(currentLine[1]),Float.parseFloat(currentLine[2]));
                    uvsList.add(element);
                }
                line=reader.readLine();
            }
            while(line.startsWith("f ")){
                processVertexes(verticesList,uvsList,normalsList,vertices,indices,uvs,normals,line);
                line=reader.readLine();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        float[] verticesArray = toFloatArray(vertices);
        int[] indicesArray = indices.stream().mapToInt(Integer::intValue).toArray();
        float[] uvsArray = toFloatArray(uvs);
        float[] normalsArray = toFloatArray(normals);
        return loader.loadToVAO(verticesArray,uvsArray,indicesArray);
    }
    private static float[] vector3fListToFloatArray(List<Vector3f> list){
        float[] array = new float[list.size()*3];
        for(int i=0;i<list.size();++i){
            array[i*3] = list.get(i).x();
            array[i*3+1] = list.get(i).y();
            array[i*3+2] = list.get(i).z();
        }
        return array;
    }
    private static void processVertexes(List<Vector3f> verticesList,List<Vector2f> uvsList,List<Vector3f> normalsList,List<Float> vertices, List<Integer> indices,List<Float> uvs,List<Float> normals,String line){
        String[][] vertexes;
        String[] currentLine = line.split("\\s+");
        String[] vertex1 = currentLine[1].split("/");
        String[] vertex2 = currentLine[2].split("/");
        String[] vertex3 = currentLine[3].split("/");
        if(currentLine.length==5) {
            String[] vertex4 = currentLine[4].split("/");
            vertexes = new String[][]{vertex1, vertex2, vertex3, vertex4};
        }else{
            vertexes = new String[][]{vertex1, vertex2, vertex3};
        }
        for(int i=0;i<vertexes.length;++i){
            indices.add(index);
            index++;
            vertices.add(verticesList.get(Integer.parseInt(vertexes[i][0])-1).x);
            vertices.add(verticesList.get(Integer.parseInt(vertexes[i][0])-1).y);
            vertices.add(verticesList.get(Integer.parseInt(vertexes[i][0])-1).z);
            uvs.add(uvsList.get(Integer.parseInt(vertexes[i][1])-1).x);
            uvs.add(1-uvsList.get(Integer.parseInt(vertexes[i][1])-1).y);
            normals.add(normalsList.get(Integer.parseInt(vertexes[i][2])-1).x);
            normals.add(normalsList.get(Integer.parseInt(vertexes[i][2])-1).y);
            normals.add(normalsList.get(Integer.parseInt(vertexes[i][2])-1).z);
            if(i==3){
                indices.add(index-4);
                indices.add(index-2);
            }
        }
    }
}