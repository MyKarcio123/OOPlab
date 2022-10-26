package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class   MapDirectionTest {

    @Test
    void mapDirectionNext_whenMapDirectionEast_shouldReturnSouth(){
        assertEquals(MapDirection.SOUTH,MapDirection.EAST.next());
    }
    @Test
    void mapDirectionNext_whenMapDirectionSouth_shouldReturnWest(){
        assertEquals(MapDirection.WEST,MapDirection.SOUTH.next());
    }
    @Test
    void mapDirectionNext_whenMapDirectionWest_shouldReturnNorth(){
        assertEquals(MapDirection.NORTH,MapDirection.WEST.next());
    }
    @Test
    void mapDirectionNext_whenMapDirectionNorth_shouldReturnEast(){
        assertEquals(MapDirection.EAST,MapDirection.NORTH.next());
    }

    @Test
    void mapDirectionPrevious_whenMapDirectionEast_shouldReturnNorth(){
        assertEquals(MapDirection.NORTH,MapDirection.EAST.previous());
    }
    @Test
    void mapDirectionPrevious_whenMapDirectionNorth_shouldReturnWest(){
        assertEquals(MapDirection.WEST,MapDirection.NORTH.previous());
    }
    @Test
    void mapDirectionPrevious_whenMapDirectionWest_shouldReturnSouth(){
        assertEquals(MapDirection.SOUTH,MapDirection.WEST.previous());
    }
    @Test
    void mapDirectionPrevious_whenMapDirectionSouth_shouldReturnEast(){
        assertEquals(MapDirection.EAST,MapDirection.SOUTH.previous());
    }
}