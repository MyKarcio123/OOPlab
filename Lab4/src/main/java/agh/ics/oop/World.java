package agh.ics.oop;

import agh.ics.oop.models.Texture;
import agh.ics.oop.renderEngine.Game;

import java.util.List;

import static agh.ics.oop.OptionsParser.parse;

public class World {
    public static void main(String[] args){

        List<MoveDirection> directions = parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = {new Vector2d(5,5), new Vector2d(2,1) };
        String[] objects = {"LOW_POLY_piece_Knight_FINAL","12221_Cat_v1_l3"};
        String[] textures = {"res/horse_texture.png","res/Cat_diffuse.jpg"};

        Game gameEngine = new Game(1280,720,"World Simulation",directions,map,positions,objects,textures);
        gameEngine.run();

    }
}
