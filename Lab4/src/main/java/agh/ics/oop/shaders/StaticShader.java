package agh.ics.oop.shaders;

import agh.ics.oop.entities.Camera;
import agh.ics.oop.mathHelper.MatrixOperations;
import org.joml.Matrix4f;

import static agh.ics.oop.mathHelper.MatrixOperations.*;

public class StaticShader  extends ShaderProgram{

    private static final String VERTEX_FILE = "src/main/java/agh/ics/oop/shaders/vertexShader.txt";
    private static final String FRAGMENT_FILE = "src/main/java/agh/ics/oop/shaders/fragmentShader.txt";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;
    public StaticShader(){
        super(VERTEX_FILE,FRAGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");

    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0,"position");
        super.bindAttribute(1,"textureCoords");
    }
    public void loadTransformationMatrix(Matrix4f matrix){
        super.load(location_transformationMatrix,matrix);
    }
    public void loadProjectionMatrix(Matrix4f projection){
        super.load(location_projectionMatrix,projection);
    }
    public void loadViewMatrix(Camera camera){
        Matrix4f viewMatrix = createViewMatrix(camera);
        super.load(location_viewMatrix,viewMatrix);
    }
}
