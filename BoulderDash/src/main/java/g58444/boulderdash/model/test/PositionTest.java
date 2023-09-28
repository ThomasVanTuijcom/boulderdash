package g58444.boulderdash.model.test;

import g58444.boulderdash.model.Direction;
import g58444.boulderdash.model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    public void nextSouth(){
        Position pos = new Position(5,5);
        Position expected = pos.next(Direction.S);
        assertEquals(expected, new Position(6,5));
    }

    @Test
    public void nextNorth(){
        Position pos = new Position(5,5);
        Position expected = pos.next(Direction.N);
        assertEquals(expected, new Position(4,5));
    }

    @Test
    public void nextEast(){
        Position pos = new Position(5,5);
        Position expected = pos.next(Direction.E);
        assertEquals(expected, new Position(5,6));
    }

    @Test
    public void nextWest(){
        Position pos = new Position(5,5);
        Position expected = pos.next(Direction.W);
        assertEquals(expected, new Position(5,4));
    }
}