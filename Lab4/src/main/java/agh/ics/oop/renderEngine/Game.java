package agh.ics.oop.renderEngine;

import agh.ics.oop.*;
import agh.ics.oop.entities.Camera;
import agh.ics.oop.entities.Entity;
import agh.ics.oop.models.Texture;
import agh.ics.oop.models.TexturedModel;
import agh.ics.oop.shaders.StaticShader;
import org.joml.Vector3f;

import java.util.List;

import static agh.ics.oop.renderEngine.Terrain.makeTerrain;

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
        entitiesToSimulate=entities;
        Entity terrain = makeTerrain((RectangularMap) map,new Texture("res/grassTexture.jpg"),loader);
        entities.add(terrain);
    }
    private void start(){
        IEngine engine = new SimulationEngine(directions,map,positionsOnPlane,entitiesToSimulate);
        engine.run();
    }
    private void update(){
        camera.calculateCameraMove();
        camera.move();
        renderer.prepare();
        shader.start();
        shader.loadViewMatrix(camera);
        renderEntitiesOnMap();
        shader.stop();
        window.updateWindow();
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
}
