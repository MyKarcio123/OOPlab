package agh.ics.oop.renderEngine;

import agh.ics.oop.entities.Entity;
import agh.ics.oop.mathHelper.MatrixOperations;
import agh.ics.oop.models.RawModel;
import agh.ics.oop.models.TexturedModel;
import agh.ics.oop.shaders.StaticShader;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.IntBuffer;
import java.util.List;

public class Renderer {

    private static final float FOV = 70f;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000.0f;

    private Matrix4f projectionMatrix;

    public Renderer(StaticShader shader){
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
        createProjectionMatrix();
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void prepare(){
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(1,0,0,1);
    }
    public void render(List<Entity> entities, StaticShader shader){
        for(Entity entity:entities){
            render(entity,shader);
        }
    }
    public void render(Entity entity, StaticShader shader){
        TexturedModel texturedModel = entity.getModel();
        RawModel model = texturedModel.getRawModel();
        GL30.glBindVertexArray(model.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        Matrix4f transformationMatrix = MatrixOperations.createTransformationMatrix(entity.getPosition(), entity.getRotX(), entity.getRotY(), entity.getRotZ(), entity.getScale());

        shader.loadTransformationMatrix(transformationMatrix);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL13.glBindTexture(GL11.GL_TEXTURE_2D,texturedModel.getTexture().getTexID());
        GL11.glDrawElements(GL11.GL_TRIANGLES,model.getVertexCount(),GL11.GL_UNSIGNED_INT,0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }

    private void createProjectionMatrix(){
        IntBuffer w = BufferUtils.createIntBuffer(1);
        IntBuffer h = BufferUtils.createIntBuffer(1);
        GLFW.glfwGetWindowSize(Window.windowID,w,h);
        int width = w.get(0);
        int height = h.get(0);
        float aspectRatio = (float) width / (float) height;
        float yScale = (float) (1f/Math.tan(Math.toRadians(FOV/2f)))*aspectRatio;
        float xScale = yScale / aspectRatio;
        float PLANE_LENGTH = FAR_PLANE - NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00(xScale);
        projectionMatrix.m11(yScale);
        projectionMatrix.m22(-((NEAR_PLANE + FAR_PLANE)/PLANE_LENGTH));
        projectionMatrix.m23(-1);
        projectionMatrix.m32(-((2*NEAR_PLANE * FAR_PLANE)/PLANE_LENGTH));
        projectionMatrix.m33(0);
    }
}
