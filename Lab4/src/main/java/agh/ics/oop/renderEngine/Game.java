package agh.ics.oop.renderEngine;

import agh.ics.oop.Vector2d;
import agh.ics.oop.entities.Entity;
import agh.ics.oop.models.RawModel;
import agh.ics.oop.models.Texture;
import agh.ics.oop.models.TexturedModel;
import agh.ics.oop.shaders.StaticShader;
import org.joml.Vector3f;

public class Game{
    private final Window window;
    private final Loader loader;
    private final Renderer renderer;
    private final StaticShader shader;
    private Entity entity;

    public Game(int width, int height, String title){
        window = new Window();
        window.createWindow(width,height,title);

        loader = new Loader();
        renderer = new Renderer();
        shader = new StaticShader();

    }
    private void start(){

        float[] vertices = {
                -0.5f,0.5f,0f,
                -0.5f,-0.5f,0f,
                0.5f,-0.5f,0f,
                0.5f,0.5f,0f
        };

        int[] indices = {
                0,1,3,
                3,1,2
        };

        float[] uvs = {
                0,0,
                0,1,
                1,1,
                1,0
        };
        RawModel model = loader.loadToVAO(vertices,uvs,indices);
        Texture texture = new Texture("res/dirt.png");
        TexturedModel texturedModel = new TexturedModel(model,texture);
        entity = new Entity(texturedModel,new Vector3f(-1,0,0),0,0,0,1);
    }
    private void update(){
        entity.increasePosition(0.002f,0,0);
        entity.increaseRotation(0,1,0);
        renderer.prepare();
        shader.start();
        renderer.render(entity,shader);
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
}
