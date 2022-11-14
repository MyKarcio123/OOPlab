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
            models[index]= new TexturedModel(loadObjModel(object,loader),textures[index]);
            ++index;
        }

        return models;
    }
    public static RawModel loadObjModel(String fileName, Loader loader) {
        FileReader fr = null;
        try {
            fr = new FileReader("res/" + fileName + ".obj");
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't load file!");
        }
        BufferedReader reader = new BufferedReader(fr);
        String line;
        List<Vector3f> verticies = new ArrayList<Vector3f>();
        List<Vector2f> textures = new ArrayList<Vector2f>();
        List<Vector3f> normals = new ArrayList<Vector3f>();
        List<Integer> indicies = new ArrayList<Integer>();
        float[] verticiesArray = null;
        float[] normalsArray = null;
        float[] textureArray = null;
        int[] indiciesArray = null;
        try{
            while (true){
                line=reader.readLine();
                String[] currentLine = line.split("\\s+");
                if(line.startsWith("v ")){
                    Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1]),Float.parseFloat(currentLine[2]),Float.parseFloat(currentLine[3]));
                    verticies.add(vertex);
                }else if(line.startsWith("vt ")){
                    Vector2f texture = new Vector2f(Float.parseFloat(currentLine[1]),Float.parseFloat(currentLine[2]));
                    textures.add(texture);
                }else if(line.startsWith("vn ")){
                    Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]),Float.parseFloat(currentLine[2]),Float.parseFloat(currentLine[3]));
                    normals.add(normal);
                }else if(line.startsWith("f ")){
                    textureArray = new float[verticies.size()*2];
                    normalsArray = new float[verticies.size()*3];
                    break;
                }
            }

            while (line!=null){
                if(!line.startsWith("f")){
                    line=reader.readLine();
                    continue;
                }
                String[] currentLine = line.split("\\s+");
                String[] vertex1 = currentLine[1].split("/");
                String[] vertex2 = currentLine[2].split("/");
                String[] vertex3 = currentLine[3].split("/");

                processVertex(vertex1,indicies,textures,normals,textureArray,normalsArray);
                processVertex(vertex2,indicies,textures,normals,textureArray,normalsArray);
                processVertex(vertex3,indicies,textures,normals,textureArray,normalsArray);
                if(currentLine.length==5) {
                    String[] vertex4 = currentLine[4].split("/");
                    processVertex(vertex1, indicies, textures, normals, textureArray, normalsArray);
                    processVertex(vertex3, indicies, textures, normals, textureArray, normalsArray);
                    processVertex(vertex4, indicies, textures, normals, textureArray, normalsArray);
                }
                line = reader.readLine();
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        verticiesArray = new float[verticies.size()*3];
        indiciesArray = new int[indicies.size()];

        int vertexPointer=0;
        for(Vector3f vertex:verticies){
            verticiesArray[vertexPointer++] = vertex.x;
            verticiesArray[vertexPointer++] = vertex.y;
            verticiesArray[vertexPointer++] = vertex.z;
        }
        for(int i=0;i<indicies.size();++i){
            indiciesArray[i]=indicies.get(i);
        }
        return loader.loadToVAO(verticiesArray,textureArray,indiciesArray);
    }

    private static void processVertex(String[] vertexData, List<Integer> indicies,List<Vector2f> textures,List<Vector3f> normals, float[] textureArray, float[] normalsArray){
        int currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;
        indicies.add(currentVertexPointer);
        Vector2f currentTex = textures.get(Integer.parseInt(vertexData[1])-1);
        textureArray[currentVertexPointer*2] = currentTex.x;
        textureArray[currentVertexPointer*2+1] = 1 - currentTex.y;
        Vector3f currentNorm = normals.get(Integer.parseInt(vertexData[2])-1);
        normalsArray[currentVertexPointer*3] = currentNorm.x;
        normalsArray[currentVertexPointer*3+1] = currentNorm.y;
        normalsArray[currentVertexPointer*3+2] = currentNorm.z;
    }
}