package agh.ics.oop;

public class RectangularMap implements IWorldMap{
    private final int width;
    private final int height;
    private final Object[][] map;

    public RectangularMap(int mapWidth,int mapHeight){
        width = mapWidth;
        height = mapHeight;
        map = new Object[height][width];
    }
    public boolean canMoveTo(Vector2d position){return true;}
    public boolean place(Animal animal){return true;}
    public boolean isOccupied(Vector2d position){return true;}
    public Object objectAt(Vector2d position){return map[position.getY()][position.getX()];}

}
