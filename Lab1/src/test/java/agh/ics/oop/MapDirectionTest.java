package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void MapDirectionNext_WhenMapDirectionEast_ShouldReturnSouth(){
        MapDirection mapDirection = MapDirection.EAST;
        assertEquals(MapDirection.SOUTH,mapDirection.next());
    }
    @Test
    void MapDirectionNext_WhenMapDirectionSouth_ShouldReturnWest(){
        MapDirection mapDirection = MapDirection.SOUTH;
        assertEquals(MapDirection.WEST,mapDirection.next());
    }
    @Test
    void MapDirectionNext_WhenMapDirectionWest_ShouldReturnNorth(){
        MapDirection mapDirection = MapDirection.WEST;
        assertEquals(MapDirection.NORTH,mapDirection.next());
    }
    @Test
    void MapDirectionNext_WhenMapDirectionNorth_ShouldReturnEast(){
        MapDirection mapDirection = MapDirection.NORTH;
        assertEquals(MapDirection.EAST,mapDirection.next());
    }

    @Test
    void MapDirectionPrevious_WhenMapDirectionEast_ShouldReturnNorth(){
        MapDirection mapDirection = MapDirection.EAST;
        assertEquals(MapDirection.NORTH,mapDirection = mapDirection.previous());
    }
    @Test
    void MapDirectionPrevious_WhenMapDirectionNorth_ShouldReturnWest(){
        MapDirection mapDirection = MapDirection.NORTH;
        assertEquals(MapDirection.WEST,mapDirection = mapDirection.previous());
    }
    @Test
    void MapDirectionPrevious_WhenMapDirectionWest_ShouldReturnSouth(){
        MapDirection mapDirection = MapDirection.WEST;
        assertEquals(MapDirection.SOUTH,mapDirection = mapDirection.previous());
    }
    @Test
    void MapDirectionPrevious_WhenMapDirectionSouth_ShouldReturnEast(){
        MapDirection mapDirection = MapDirection.SOUTH;
        assertEquals(MapDirection.EAST,mapDirection.previous());
    }
}