package g58444.boulderdash.model.test;

import g58444.boulderdash.model.Direction;
import g58444.boulderdash.model.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    private Model model;

    @BeforeEach
    public void setUp(){
        this.model = new Model();
        model.buildLevel(3);
    }

    @Test
    public void moveEastPossible(){
        assertDoesNotThrow(() -> model.movePlayer(Direction.E));
    }

    @Test
    public void moveEastNotPossible(){
        //Moving the player to a wall
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        assertThrows(IllegalArgumentException.class, () -> model.movePlayer(Direction.E));
    }

    @Test
    public void moveWestPossible(){
        model.movePlayer(Direction.E);
        assertDoesNotThrow(() -> model.movePlayer(Direction.W));
    }

    @Test
    public void moveWestNotPossible(){
        assertThrows(IllegalArgumentException.class, () -> model.movePlayer(Direction.W));
    }

    @Test
    public void moveNorthPossible(){
        model.movePlayer(Direction.S);
        assertDoesNotThrow(() -> model.movePlayer(Direction.N));
    }

    @Test
    public void moveNorthNotPossible(){
        assertThrows(IllegalArgumentException.class, () -> model.movePlayer(Direction.N));
    }

    @Test
    public void moveSouthNotPossible(){
        model.movePlayer(Direction.S);
        model.movePlayer(Direction.S);
        model.movePlayer(Direction.S);
        model.movePlayer(Direction.S);
        assertThrows(IllegalArgumentException.class, () -> model.movePlayer(Direction.S));
    }

    @Test
    public void moveSouthPossible(){
        assertDoesNotThrow(() -> model.movePlayer(Direction.S));
    }

    @Test
    public void gameIsNotOver(){
        assertFalse(model.isGameOver());
    }

    @Test
    public void gameIsNotOverEnoughDiamonds(){
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.S);
        model.movePlayer(Direction.S);
        assertFalse(model.isGameOver());
    }

    @Test
    public void gameIsNotOverPlayerOnDoorButNotEnoughDiamonds(){
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.S);
        model.movePlayer(Direction.S);
        model.movePlayer(Direction.S);
        assertFalse(model.isGameOver());
    }

    @Test
    public void gameIsOverEnoughDiamondsAndOnExit(){
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.S);
        model.movePlayer(Direction.S);
        model.movePlayer(Direction.S);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        assertTrue(model.isGameOver());
    }

    @Test
    public void gameIsOverPlayerIsKilled(){
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.S);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.S);
        assertTrue(model.isGameOver());
    }

    @Test
    public void playerHasNotWonStart(){
        assertFalse(model.hasPlayerWon());
    }

    @Test
    public void playerHasNotWonKilled(){
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.S);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.S);
        assertFalse(model.hasPlayerWon());
    }

    @Test
    public void playerWon(){
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.S);
        model.movePlayer(Direction.S);
        model.movePlayer(Direction.S);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        assertTrue(model.hasPlayerWon());
    }

    @Test
    public void undo(){
        assertThrows(IllegalArgumentException.class, () -> model.movePlayer(Direction.W));
        assertDoesNotThrow(() -> model.movePlayer(Direction.E));
        model.undo();
        assertThrows(IllegalArgumentException.class, () -> model.movePlayer(Direction.W));
    }

    @Test
    public void redo(){
        assertThrows(IllegalArgumentException.class, () -> model.movePlayer(Direction.W));
        assertDoesNotThrow(() -> model.movePlayer(Direction.E));
        model.undo();
        model.redo();
        assertDoesNotThrow(() -> model.movePlayer(Direction.W));
    }

    @Test
    public void getAmountOfDiamonds(){
        assertEquals(model.getAmountDiamonds(), 5.0);
    }

    @Test
    public void getCaptureDiamondsAtStart(){
        assertEquals(model.getAmountDiamonds(), 0.0);
    }

    @Test
    public void getCaptureDiamondsEnough(){
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.E);
        model.movePlayer(Direction.S);
        model.movePlayer(Direction.S);
        assertEquals(3.0, model.getCollectedDiamonds());
    }

}