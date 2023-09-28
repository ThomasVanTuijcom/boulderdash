package g58444.boulderdash.model.test;

import g58444.boulderdash.model.Board;
import g58444.boulderdash.model.Level;
import g58444.boulderdash.model.Position;
import g58444.boulderdash.model.elements.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;

    @BeforeEach
    public void setUp(){
        this.board = new Board(new Level(1));
    }

    @Test
    public void getElementAtWall(){
        Element elem = board.getElementAt(new Position(0,0));
        assertTrue(elem instanceof Wall);
    }

    @Test
    public void getElementAtPlayer(){
        Element elem = board.getElementAt(new Position(1,1));
        assertTrue(elem instanceof Player);
    }

    @Test
    public void getElementAtRock(){
        Element elem = board.getElementAt(new Position(1,6));
        assertTrue(elem instanceof Rock);
    }

    @Test
    public void getElementAtDiamond(){
        Element elem = board.getElementAt(new Position(4,5));
        assertTrue(elem instanceof Diamond);
    }

    @Test
    public void getPlayerPosition(){
        Position pos = board.getPlayerPosition();
        assertEquals(pos.getRow(), 1);
        assertEquals(pos.getCol(), 1);
    }

    @Test
    public void setElement(){
        board.setElement(new Diamond(), new Position(1, 5));
        assertTrue(board.getElementAt(new Position(1, 5)) instanceof Diamond);
        board.setElement(new Rock(), new Position(1, 5));
        assertTrue(board.getElementAt(new Position(1, 5)) instanceof Rock);
    }

    @Test
    public void dropElement(){
        board.setElement(new Diamond(), new Position(1, 5));
        assertTrue(board.getElementAt(new Position(1, 5)) instanceof Diamond);
        board.dropElement(new Position(1, 5));
        assertEquals(board.getElementAt(new Position(1, 5)), null);
    }

    @Test
    public void containsPositionInside(){
        assertTrue(board.contains(new Position(5, 5)));
    }

    @Test
    public void containsPositionInsideLeftUpperCornerLimit(){
        assertTrue(board.contains(new Position(0, 0)));
    }

    @Test
    public void containsPositionInsideRightBottomCornerLimit(){
        assertTrue(board.contains(new Position(board.getBoardHeight() - 1, board.getBoardWidth() - 1)));
    }

    @Test
    public void containsPositionOutside(){
        assertFalse(board.contains(new Position(-1, 0)));
    }

    @Test
    public void getElementPositionExist(){
        Element diamond = new Diamond();
        board.setElement(diamond, new Position(5, 5));
        Position result = board.getElementPosition(diamond);
        assertTrue(result.equals(new Position(5, 5)));
    }

    @Test
    public void getElementPositionDoesNotExist(){
        Element diamond = new Diamond();
        Position result = board.getElementPosition(diamond);
        assertEquals(result, null);
    }

    @Test
    public void getRocks(){
        List<Element> elements = board.getRocks();
        //There are 155 rocks on level 1
        assertEquals(elements.size(), 155);
    }

    @Test
    public void getDiamonds(){
        List<Element> elements = board.getDiamonds();
        //There are 20 diamonds on level 1
        assertEquals(elements.size(), 20);
    }

    @Test
    public void getRockPositionsChecksIfOnlyContainsRockPositions(){
        Position pos1 = new Position(4, 4);
        Position pos2 = new Position(5, 5);
        Position pos3 = new Position(6, 6);
        Position pos4 = new Position(7, 7);
        Position pos5 = new Position(8, 8);
        board.setElement(new Rock(), pos1);
        board.setElement(new Rock(), pos2);
        board.setElement(new Rock(), pos3);
        board.setElement(new Rock(), pos4);
        board.setElement(new Diamond(), pos5);

        assertTrue(board.getRockPositions().contains(pos1));
        assertTrue(board.getRockPositions().contains(pos2));
        assertTrue(board.getRockPositions().contains(pos3));
        assertTrue(board.getRockPositions().contains(pos4));
        assertTrue(!board.getRockPositions().contains(pos5));
    }

    @Test
    public void getRockPositionsChecksIfOnlyContainsDiamondPositions(){
        Position pos1 = new Position(4, 4);
        Position pos2 = new Position(5, 5);
        Position pos3 = new Position(6, 6);
        Position pos4 = new Position(7, 7);
        Position pos5 = new Position(8, 8);
        board.setElement(new Diamond(), pos1);
        board.setElement(new Diamond(), pos2);
        board.setElement(new Diamond(), pos3);
        board.setElement(new Diamond(), pos4);
        board.setElement(new Rock(), pos5);

        assertTrue(board.getDiamondPositions().contains(pos1));
        assertTrue(board.getDiamondPositions().contains(pos2));
        assertTrue(board.getDiamondPositions().contains(pos3));
        assertTrue(board.getDiamondPositions().contains(pos4));
        assertTrue(!board.getDiamondPositions().contains(pos5));
    }
}