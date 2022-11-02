package agh.ics.oop;

import agh.ics.oop.renderEngine.Game;

import java.util.List;

import static agh.ics.oop.OptionsParser.parse;

public class World {
    public static void main(String[] args){
        Game gameEngine = new Game(1280,720,"test");
        gameEngine.run();

        List<MoveDirection> directions = parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
    }
}
