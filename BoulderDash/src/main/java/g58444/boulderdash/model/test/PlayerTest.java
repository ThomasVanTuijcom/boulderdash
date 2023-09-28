package g58444.boulderdash.model.test;

import g58444.boulderdash.model.Board;
import g58444.boulderdash.model.Direction;
import g58444.boulderdash.model.Level;
import g58444.boulderdash.model.Position;
import g58444.boulderdash.model.elements.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Board board;

    @BeforeEach
    public void setUp(){
        this.board = new Board(new Level(5));
    }

    @Test
    public void getPossibleMovesBlockedInCorner(){
        Player player = new Player();
        Position pos = new Position(1, 1);
        board.setElement(player, pos);

        List<Position> result = player.getPossibleMoves(pos, board);
        List<Position> expected = new ArrayList<>();
        expected.add(new Position(2,1));
        expected.add(new Position(1,2));
        assertEqualsIgnoringOrder(result, expected);
    }

    @Test
    public void getPossibleMovesAllDirections(){
        Player player = new Player();
        Position pos = new Position(5, 5);
        board.setElement(player, pos);

        board.setElement(new Dirt(), pos.next(Direction.S));
        board.setElement(new Dirt(), pos.next(Direction.N));
        board.setElement(new Dirt(), pos.next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.W));

        List<Position> result = player.getPossibleMoves(pos, board);
        List<Position> expected = new ArrayList<>();
        expected.add(pos.next(Direction.S));
        expected.add(pos.next(Direction.N));
        expected.add(pos.next(Direction.E));
        expected.add(pos.next(Direction.W));
        assertEqualsIgnoringOrder(result, expected);
    }

    @Test
    public void getPossibleMovesBlockedBetweenRocks(){
        Player player = new Player();
        Position pos = new Position(5, 5);
        board.setElement(player, pos);

        board.setElement(new Rock(), pos.next(Direction.S));
        board.setElement(new Rock(), pos.next(Direction.N));
        board.setElement(new Rock(), pos.next(Direction.E));
        board.setElement(new Rock(), pos.next(Direction.W));

        List<Position> result = player.getPossibleMoves(pos, board);
        List<Position> expected = new ArrayList<>();
        assertEqualsIgnoringOrder(result, expected);
    }

    @Test
    public void getPossibleMovesPushRockRight(){
        Player player = new Player();
        Position pos = new Position(5, 5);
        board.setElement(player, pos);

        //Puttin dirt arround the player
        board.setElement(new Dirt(), pos.next(Direction.S));
        board.setElement(new Dirt(), pos.next(Direction.N));
        board.setElement(new Dirt(), pos.next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.W));

        board.setElement(new Rock(), pos.next(Direction.E));
        board.dropElement(pos.next(Direction.E).next(Direction.E));

        List<Position> result = player.getPossibleMoves(pos, board);
        List<Position> expected = new ArrayList<>();
        expected.add(pos.next(Direction.E));
        expected.add(pos.next(Direction.S));
        expected.add(pos.next(Direction.N));
        expected.add(pos.next(Direction.W));
        assertEqualsIgnoringOrder(result, expected);
    }

    @Test
    public void getPossibleMovesPushRockLeft(){
        Player player = new Player();
        Position pos = new Position(5, 5);
        board.setElement(player, pos);

        //Puttin dirt arround the player
        board.setElement(new Dirt(), pos.next(Direction.S));
        board.setElement(new Dirt(), pos.next(Direction.N));
        board.setElement(new Dirt(), pos.next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.W));

        board.setElement(new Rock(), pos.next(Direction.W));
        board.dropElement(pos.next(Direction.W).next(Direction.W));

        List<Position> result = player.getPossibleMoves(pos, board);
        List<Position> expected = new ArrayList<>();
        expected.add(pos.next(Direction.E));
        expected.add(pos.next(Direction.S));
        expected.add(pos.next(Direction.N));
        expected.add(pos.next(Direction.W));
        assertEqualsIgnoringOrder(result, expected);
    }

    @Test
    public void getPossibleMovesCaptureDiamonds(){
        Player player = new Player();
        Position pos = new Position(5, 5);
        board.setElement(player, pos);

        //Puttin dirt arround the player
        board.setElement(new Diamond(), pos.next(Direction.S));
        board.setElement(new Diamond(), pos.next(Direction.N));
        board.setElement(new Dirt(), pos.next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.W));

        List<Position> result = player.getPossibleMoves(pos, board);
        List<Position> expected = new ArrayList<>();
        expected.add(pos.next(Direction.E));
        expected.add(pos.next(Direction.S));
        expected.add(pos.next(Direction.N));
        expected.add(pos.next(Direction.W));
        assertEqualsIgnoringOrder(result, expected);
    }

    @Test
    public void getPossibleMovesStepOnExit(){
        Player player = new Player();
        Position pos = new Position(5, 5);
        board.setElement(player, pos);

        //Puttin dirt arround the player
        board.setElement(new Dirt(), pos.next(Direction.S));
        board.setElement(new Door(), pos.next(Direction.N));
        board.setElement(new Dirt(), pos.next(Direction.E));
        board.setElement(new Dirt(), pos.next(Direction.W));

        List<Position> result = player.getPossibleMoves(pos, board);
        List<Position> expected = new ArrayList<>();
        expected.add(pos.next(Direction.E));
        expected.add(pos.next(Direction.S));
        expected.add(pos.next(Direction.N));
        expected.add(pos.next(Direction.W));
        assertEqualsIgnoringOrder(result, expected);
    }

    private void assertEqualsIgnoringOrder(List<Position> expected, List<Position> actual) {
        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

}