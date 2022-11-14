package agh.ics.oop.renderEngine;

import agh.ics.oop.*;
import agh.ics.oop.entities.Camera;
import agh.ics.oop.entities.Entity;
import agh.ics.oop.models.Texture;
import agh.ics.oop.models.TexturedModel;
import agh.ics.oop.shaders.StaticShader;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;

import java.util.ArrayList;
import java.util.List;

import static agh.ics.oop.renderEngine.Terrain.makeTerrain;
import static org.lwjgl.glfw.GLFW.*;

public class Game{
    private final Window window;
    private final Loader loader;
    private final Renderer renderer;
    private final StaticShader shader;
    private final Camera camera;

    private final List<Entity> entities;

    private final List<Entity> entitiesToSimulate;
    private final List<MoveDirection> directions;
    static IWorldMap map;
    private final Vector2d[] positionsOnPlane;
    private SimulationEngine engine;

    private static int oldState = GLFW_RELEASE;
    public Game(int width, int height, String title, List<MoveDirection> directions, IWorldMap map, Vector2d[] positionsOnPlane, String[] objectsPaths, String[] texturesPaths){
        this.directions = directions;
        this.map = map;
        this.positionsOnPlane = positionsOnPlane;
        window = new Window();
        window.createWindow(width,height,title);

        loader = new Loader();
        shader = new StaticShader();
        renderer = new Renderer(shader);

        camera = new Camera();
        Texture[] textures = new Texture[texturesPaths.length];
        for(int i=0;i<texturesPaths.length;++i){
            textures[i]=new Texture(texturesPaths[i]);
        }
        entities = EntitiesLoader.loadEntities(ObjectLoader.loadObjModels(objectsPaths,textures,loader));
        entitiesToSimulate=new ArrayList<>(entities);
        Entity terrain = makeTerrain((RectangularMap) map,new Texture("res/grassTexture.jpg"),loader);
        entities.add(terrain);

    }
    private void start(){
        engine = new SimulationEngine(directions,map,positionsOnPlane,entitiesToSimulate);
    }
    private void update(){
        graphicEnigneOperations();
        glfwSetKeyCallback();
    }
    private void end(){
        shader.cleanUp();
        loader.cleanUp();
        window.closeWindow();
    }

    public void run(){
        start();
        while(!window.shouldClose()){
            update();
        }
        end();
    }
    private void renderEntitiesOnMap(){
        for(Entity entity:entities){
            renderer.render(entity,shader);
        }
    }
    private void graphicEnigneOperations(){
        camera.calculateCameraMove();
        camera.move();
        renderer.prepare();
        shader.start();
        shader.loadViewMatrix(camera);
        renderEntitiesOnMap();
        shader.stop();
        window.updateWindow();
    }
    private void glfwSetKeyCallback(){
        int state = GLFW.glfwGetMouseButton(Window.windowID,GLFW.GLFW_MOUSE_BUTTON_LEFT);
        if(state==GLFW_RELEASE && oldState == GLFW_PRESS){
            engine.run();
        }
        oldState=state;
    }
}
