package agh.ics.oop.renderEngine;

import agh.ics.oop.*;
import agh.ics.oop.entities.Camera;
import agh.ics.oop.entities.Entity;
import agh.ics.oop.gui.App;
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

public class Game implements IEngine,Runnable{
    private Window window;
    private Loader loader;
    private Renderer renderer;
    private StaticShader shader;
    private Camera camera;

    private List<Entity> entities;

    private List<Entity> entitiesToSimulate;
    private final int width;
    private final int height;
    private final String title;
    private final List<MoveDirection> directions;
    static IWorldMap map;
    private final Vector2d[] positionsOnPlane;
    private final String[] objectsPaths;
    private final String[] texturesPaths;
    private SimulationEngine engine;
    private Entity terrain;
    private Thread engineThread;
    private App app;
    public static AnimationController animationController;
    public int testValue = 0;
    private int lastTestValue = 0;
    private static int oldState = GLFW_RELEASE;
    public Game(int width, int height, String title, List<MoveDirection> directions, IWorldMap map, Vector2d[] positionsOnPlane, String[] objectsPaths, String[] texturesPaths,App app){
        this.width = width;
        this.height = height;
        this.title = title;
        this.directions = directions;
        Game.map = map;
        this.positionsOnPlane = positionsOnPlane;
        this.objectsPaths = objectsPaths;
        this.texturesPaths = texturesPaths;
        animationController = new AnimationController();
        this.app = app;
    }
    private void prepareWindow(){
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
    }
    private void start(){
        engine = new SimulationEngine(directions,map,positionsOnPlane,entitiesToSimulate,app,this);
        terrain = makeTerrain(map,new Texture("res/grassTexture.jpg"),loader);
        engineThread = new Thread(engine);
    }
    private void update(){
        graphicEnigneOperations();
        glfwSetKeyCallback();
        animationController.performCurrentAnimation();
    }
    private void end(){
        shader.cleanUp();
        loader.cleanUp();
        window.closeWindow();
    }

    public void run(){
        prepareWindow();
        start();
        app.refreshMap();
        engineThread.start();
        while(!window.shouldClose()){
            update();
            makeNewTerrain();
        }
        end();
    }
    private void renderEntitiesOnMap(){
        for(Entity entity:entities){
            renderer.render(entity,shader);
            renderer.render(terrain,shader);
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
    public void makeNewTerrain(){
        if(testValue!=lastTestValue) {
            terrain = makeTerrain(map, new Texture("res/grassTexture.jpg"), loader);
            lastTestValue=testValue;
        }
    }
    private void glfwSetKeyCallback(){
        int state = GLFW.glfwGetMouseButton(Window.windowID,GLFW.GLFW_MOUSE_BUTTON_LEFT);
        if(state==GLFW_RELEASE && oldState == GLFW_PRESS && !animationController.isAtAnimation()){
            engineThread.start();
        }
        oldState=state;
    }
}
