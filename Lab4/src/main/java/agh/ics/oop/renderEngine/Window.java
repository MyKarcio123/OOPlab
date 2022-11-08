package agh.ics.oop.renderEngine;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWWindowCloseCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryUtil;

public class  Window {

    public static long windowID;

    public void createWindow(int width, int height, String title){
        if(!GLFW.glfwInit()){
            throw new IllegalStateException("Could not create GLFW");
        }

        windowID = GLFW.glfwCreateWindow(width,height,title, MemoryUtil.NULL,MemoryUtil.NULL);

        if(windowID == MemoryUtil.NULL) {
            throw new IllegalStateException("Can't make window");
        }

        GLFW.glfwMakeContextCurrent(windowID);
        GLFW.glfwSwapInterval(1);
        GLFW.glfwShowWindow(windowID);
        GL.createCapabilities();
    }

    public void updateWindow(){
        GLFW.glfwSwapBuffers(windowID);
        GLFW.glfwPollEvents();
        GLFW.glfwSwapInterval(1);
    }

    public void closeWindow(){
        GLFW.glfwDestroyWindow(windowID);
        GLFW.glfwTerminate();
    }

    public boolean shouldClose(){
        return GLFW.glfwWindowShouldClose(windowID);
    }

    public static long getWindowID() {
        return windowID;
    }
}
