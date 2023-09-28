package g58444.boulderdash.model.test;

import g58444.boulderdash.model.Level;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LevelTest {
    @Test
    public void getLevelHeight1(){
        Level level = new Level(1);
        assertEquals(level.getLevelHeight(), 20);
    }

    @Test
    public void getLevelHeight2(){
        Level level = new Level(2);
        assertEquals(level.getLevelHeight(), 25);
    }

    @Test
    public void getLevelWidth1(){
        Level level = new Level(1);
        assertEquals(level.getLevelWidth(), 40);
    }

    @Test
    public void getLevelWidth2(){
        Level level = new Level(2);
        assertEquals(level.getLevelWidth(), 45);
    }


}