package agh.ics.oop.entities;

import agh.ics.oop.renderEngine.Window;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import java.nio.DoubleBuffer;

import static java.lang.Math.*;
import static org.lwjgl.glfw.GLFW.*;

public class Camera {

    private Vector3f position;
    private float pitch;
    private float yaw;
    private float roll;

    private boolean firstTick = false;
    private float beforeMouseX = 0;
    private float beforeMouseY = 0;
    public Camera(){
        position = new Vector3f(0,0,0);
        pitch = 0.0f;
        yaw = 0.0f;
        roll = 0.0f;
    }

    public void move(){
        int state = GLFW.glfwGetKey(Window.windowID,GLFW.GLFW_KEY_W);
        if(state == GLFW_PRESS){
            position = position.sub(0,0,0.02f);
        }
        state = GLFW.glfwGetKey(Window.windowID,GLFW.GLFW_KEY_D);
        if(state == GLFW_PRESS){
            position = position.add(0.02f,0,0);
        }
        state = GLFW.glfwGetKey(Window.windowID,GLFW.GLFW_KEY_A);
        if(state == GLFW_PRESS){
            position = position.sub(0.02f,0,0);
        }
        state = GLFW.glfwGetKey(Window.windowID,GLFW.GLFW_KEY_S);
        if(state == GLFW_PRESS){
            position = position.add(0,0,0.02f);
        }
    }
    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }

    public void calculateCameraMove(){
        DoubleBuffer mouseX = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer mouseY = BufferUtils.createDoubleBuffer(1);

        glfwGetCursorPos(Window.windowID, mouseX, mouseY);
        mouseX.rewind();
        mouseY.rewind();

        int state = GLFW.glfwGetMouseButton(Window.windowID,GLFW.GLFW_MOUSE_BUTTON_RIGHT);
        if(state == GLFW_PRESS){
            if(firstTick) {
                float newMouseX = (float) mouseX.get(0);
                float newMouseY = (float) mouseY.get(0);
                float pitchChange = (beforeMouseY - newMouseY) * 0.1f;
                float yawChange = (beforeMouseX - newMouseX) * 0.1f;
                pitch -= pitchChange;
                yaw -= yawChange;
                beforeMouseX = (float) mouseX.get(0);
                beforeMouseY = (float) mouseY.get(0);
            }else{
                beforeMouseX = (float) mouseX.get(0);
                beforeMouseY = (float) mouseY.get(0);
                firstTick=true;
            }
        }else{
            firstTick=false;
        }
    }
}
