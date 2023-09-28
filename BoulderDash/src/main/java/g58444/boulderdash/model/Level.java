package g58444.boulderdash.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The level class represents a level of our game. This class will require to have text documents having the levels
 * inside them. This class will contain the pure string representation of the level that will be constructed.
 */
public class Level {
    private String level = "";
    private int levelHeight;
    private int levelWidth;
    private int numberLevel;

    /**
     * The constructor of the level object. It requires one argument a number, this number is the number of the level
     * we want to start. The constructor will search for a text document in the ressources folder for a file named:
     * LEVEL_(number).txt. If he can't find the file, an IOException is thrown.
     *
     * @param number The number of the level we want, must be between 1 and 5.
     */
    public Level(int number) {
        if (number < 1 || number > 5) {
            throw new IllegalArgumentException("The levels are available from 1 to 5");
        }
        try (var in = Level.class.getResourceAsStream("/LEVEL_" + number + ".txt")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String data = reader.readLine();
            levelWidth = data.length();
            while (data != null && !data.isEmpty()) {
                level += data;
                levelHeight++;
                data = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
        numberLevel = number;
    }

    public Level (Level level){
        this(level.numberLevel);
    }

    /**
     * Getter of the level height
     *
     * @return Returns int value for the level height
     */
    public int getLevelHeight() {
        return levelHeight;
    }

    /**
     * Getter of the level width
     *
     * @return Returns int value for the level width
     */
    public int getLevelWidth() {
        return levelWidth;
    }

    /**
     * Getter of the string representation of the level
     *
     * @return Returns the string representation
     */
    public String getLevel() {
        return level;
    }
}
