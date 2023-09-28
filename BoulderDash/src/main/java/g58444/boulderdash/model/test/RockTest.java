package g58444.boulderdash.model.test;

import g58444.boulderdash.model.Board;
import g58444.boulderdash.model.Direction;
import g58444.boulderdash.model.Level;
import g58444.boulderdash.model.Position;
import g58444.boulderdash.model.elements.Diamond;
import g58444.boulderdash.model.elements.Dirt;
import g58444.boulderdash.model.elements.Player;
import g58444.boulderdash.model.elements.Rock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RockTest {

    private Board board;

    @BeforeEach
    public void setUp(){
        this.board = new Board(new Level(1));
    }

    @Test
    public void getPossibleMovesOnlyVertical(){
        Rock rock = new Rock();
        Position pos = new Position(5, 5);
        board.setElement(rock, pos);

        //Placing dirt all around the rock
        board.setElement(new Dirt(), pos.next(Direction.N));
        board.setElement(new Dirt(), pos.next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.W));
        board.setElement(new Dirt(), pos.next(Direction.S));
        board.setElement(new Dirt(), pos.next(Direction.N).next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.N).next(Direction.W));
        board.setElement(new Dirt(), pos.next(Direction.S).next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.S).next(Direction.W));

        board.dropElement(pos.next(Direction.S));

        List<Position> result = rock.getPossibleMoves(pos, board);
        List<Position> expected = new ArrayList<>();
        expected.add(pos.next(Direction.S));
        assertEqualsIgnoringOrder(result, expected);
    }

    @Test
    public void getPossibleMovesNoMove(){
        Rock rock = new Rock();
        Position pos = new Position(5, 5);
        board.setElement(rock, pos);

        //Placing dirt all around the rock except underneath him
        board.setElement(new Dirt(), pos.next(Direction.N));
        board.setElement(new Dirt(), pos.next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.W));
        board.setElement(new Dirt(), pos.next(Direction.S));
        board.setElement(new Dirt(), pos.next(Direction.N).next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.N).next(Direction.W));
        board.setElement(new Dirt(), pos.next(Direction.S).next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.S).next(Direction.W));

        List<Position> result = rock.getPossibleMoves(pos, board);
        List<Position> expected = new ArrayList<>();
        assertEqualsIgnoringOrder(result, expected);
    }

    @Test
    public void getPossibleMovesFallOnPlayer(){
        Rock rock = new Rock();
        Position pos = new Position(5, 5);
        board.setElement(rock, pos);

        //Placing dirt all around the rock except underneath him
        board.setElement(new Dirt(), pos.next(Direction.N));
        board.setElement(new Dirt(), pos.next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.W));
        board.setElement(new Dirt(), pos.next(Direction.S));
        board.setElement(new Dirt(), pos.next(Direction.N).next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.N).next(Direction.W));
        board.setElement(new Dirt(), pos.next(Direction.S).next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.S).next(Direction.W));

        board.dropElement(pos.next(Direction.S));
        board.dropElement(pos.next(Direction.S).next(Direction.S));
        board.setElement(new Player(), pos.next(Direction.S).next(Direction.S));

        List<Position> result = rock.getPossibleMoves(pos, board);
        List<Position> expected = new ArrayList<>();
        expected.add(pos.next(Direction.S).next(Direction.S));
        expected.add(pos.next(Direction.S));
        assertEqualsIgnoringOrder(result, expected);
    }

    @Test
    public void getPossibleMovesSlideOnRockLeft(){
        Rock rock = new Rock();
        Position pos = new Position(5, 5);
        board.setElement(rock, pos);

        //Placing dirt all around the rock except underneath him
        board.setElement(new Dirt(), pos.next(Direction.N));
        board.setElement(new Dirt(), pos.next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.W));
        board.setElement(new Dirt(), pos.next(Direction.S));
        board.setElement(new Dirt(), pos.next(Direction.N).next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.N).next(Direction.W));
        board.setElement(new Dirt(), pos.next(Direction.S).next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.S).next(Direction.W));

        board.dropElement(pos.next(Direction.S));
        board.setElement(new Rock(), pos.next(Direction.S));

        board.dropElement(pos.next(Direction.W));
        board.dropElement(pos.next(Direction.W).next(Direction.S));


        List<Position> result = rock.getPossibleMoves(pos, board);
        List<Position> expected = new ArrayList<>();
        expected.add(pos.next(Direction.W));
        assertEqualsIgnoringOrder(result, expected);
    }

    @Test
    public void getPossibleMovesSlideOnRockRight(){
        Rock rock = new Rock();
        Position pos = new Position(5, 5);
        board.setElement(rock, pos);

        //Placing dirt all around the rock except underneath him
        board.setElement(new Dirt(), pos.next(Direction.N));
        board.setElement(new Dirt(), pos.next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.W));
        board.setElement(new Dirt(), pos.next(Direction.S));
        board.setElement(new Dirt(), pos.next(Direction.N).next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.N).next(Direction.W));
        board.setElement(new Dirt(), pos.next(Direction.S).next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.S).next(Direction.W));

        board.dropElement(pos.next(Direction.S));
        board.setElement(new Rock(), pos.next(Direction.S));

        board.dropElement(pos.next(Direction.E));
        board.dropElement(pos.next(Direction.E).next(Direction.S));


        List<Position> result = rock.getPossibleMoves(pos, board);
        List<Position> expected = new ArrayList<>();
        expected.add(pos.next(Direction.E));
        assertEqualsIgnoringOrder(result, expected);
    }

    @Test
    public void getPossibleMovesSlideOnDiamondLeft(){
        Rock rock = new Rock();
        Position pos = new Position(5, 5);
        board.setElement(rock, pos);

        //Placing dirt all around the rock except underneath him
        board.setElement(new Dirt(), pos.next(Direction.N));
        board.setElement(new Dirt(), pos.next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.W));
        board.setElement(new Dirt(), pos.next(Direction.S));
        board.setElement(new Dirt(), pos.next(Direction.N).next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.N).next(Direction.W));
        board.setElement(new Dirt(), pos.next(Direction.S).next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.S).next(Direction.W));

        board.dropElement(pos.next(Direction.S));
        board.setElement(new Diamond(), pos.next(Direction.S));

        board.dropElement(pos.next(Direction.W));
        board.dropElement(pos.next(Direction.W).next(Direction.S));


        List<Position> result = rock.getPossibleMoves(pos, board);
        List<Position> expected = new ArrayList<>();
        expected.add(pos.next(Direction.W));
        assertEqualsIgnoringOrder(result, expected);
    }

    @Test
    public void getPossibleMovesSlideOnDiamondRight(){
        Rock rock = new Rock();
        Position pos = new Position(5, 5);
        board.setElement(rock, pos);

        //Placing dirt all around the rock except underneath him
        board.setElement(new Dirt(), pos.next(Direction.N));
        board.setElement(new Dirt(), pos.next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.W));
        board.setElement(new Dirt(), pos.next(Direction.S));
        board.setElement(new Dirt(), pos.next(Direction.N).next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.N).next(Direction.W));
        board.setElement(new Dirt(), pos.next(Direction.S).next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.S).next(Direction.W));

        board.dropElement(pos.next(Direction.S));
        board.setElement(new Rock(), pos.next(Direction.S));

        board.dropElement(pos.next(Direction.E));
        board.dropElement(pos.next(Direction.E).next(Direction.S));


        List<Position> result = rock.getPossibleMoves(pos, board);
        List<Position> expected = new ArrayList<>();
        expected.add(pos.next(Direction.E));
        assertEqualsIgnoringOrder(result, expected);
    }

    private void assertEqualsIgnoringOrder(List<Position> expected, List<Position> actual) {
        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

}