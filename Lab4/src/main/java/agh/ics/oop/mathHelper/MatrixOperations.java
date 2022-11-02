package agh.ics.oop.mathHelper;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.nio.FloatBuffer;

public class MatrixOperations {

    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale){
        Matrix4f matrix = new Matrix4f();
        matrix.translate(translation).scale(scale).rotateXYZ((float)Math.toRadians(rx),(float)Math.toRadians(ry),(float)Math.toRadians(rz));
        return matrix;
    }
}
