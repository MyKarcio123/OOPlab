package agh.ics.oop;

import agh.ics.oop.models.Texture;
import agh.ics.oop.renderEngine.Game;

import java.util.List;

import static agh.ics.oop.OptionsParser.parse;

public class World {
    public static void main(String[] args){

        List<MoveDirection> directions = parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        map = new GrassField(10);
        Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4) };
        String[] objects = {"12221_Cat_v1_l3","12268_banjofrog_v1_L3"};
        String[] textures = {"res/Cat_diffuse.jpg","res/12268_banjofrog_diffuse.jpg"};

        Game gameEngine = new Game(1280,720,"World Simulation",directions,map,positions,objects,textures);
        gameEngine.run();

    }
}
