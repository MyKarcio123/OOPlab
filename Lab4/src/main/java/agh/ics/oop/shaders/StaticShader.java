package agh.ics.oop.shaders;

import org.joml.Matrix4f;

public class StaticShader  extends ShaderProgram{

    private static final String VERTEX_FILE = "src/main/java/agh/ics/oop/shaders/vertexShader.txt";
    private static final String FRAGMENT_FILE = "src/main/java/agh/ics/oop/shaders/fragmentShader.txt";

    private int location_transformationMatrix;
    public StaticShader(){
        super(VERTEX_FILE,FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0,"position");
        super.bindAttribute(1,"textureCoords");
    }
    public void loadTransformationMatrix(Matrix4f matrix){
        super.load(location_transformationMatrix,matrix);
    }
}
