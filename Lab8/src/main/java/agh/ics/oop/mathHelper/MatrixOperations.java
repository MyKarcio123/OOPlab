package agh.ics.oop.mathHelper;

import agh.ics.oop.entities.Camera;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.nio.FloatBuffer;

public class MatrixOperations {

    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale){
        Matrix4f matrix = new Matrix4f();
        matrix.translate(translation).scale(scale).rotateXYZ((float)Math.toRadians(rx),(float)Math.toRadians(ry),(float)Math.toRadians(rz));
        return matrix;
    }
    public static Matrix4f createViewMatrix(Camera camera){
        Matrix4f viewMatrix = new Matrix4f();
        Vector3f cameraPos = camera.getPosition();
        cameraPos = new Vector3f(-cameraPos.x(),-cameraPos.y(),-cameraPos.z());
        viewMatrix.translate(cameraPos).rotateXYZ((float)Math.toRadians(camera.getPitch()),(float)Math.toRadians(camera.getYaw()),(float)Math.toRadians(camera.getRoll()));
        return viewMatrix;
    }
}
