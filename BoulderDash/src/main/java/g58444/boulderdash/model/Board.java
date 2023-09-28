package g58444.boulderdash.model;

import g58444.boulderdash.model.elements.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The board is a main component of our BoulderDash game. This represents the board where all the different elements
 * will be placed on.
 */
public class Board {
    private Element[][] board;
    private Level level;
    private Position doorPosition;
    private boolean isExitAppeared = false;
    private double collectedDiamonds;
    private double amountOfDiamonds;

    /**
     * The constructor of a board requires one argument: a level. The board will fetch the height and width of the level
     * and constructs a 2D array with these values to put all the different elements at their place thanks to the string
     * representation of the level.
     *
     * @param level The level of the board. Example: If level is 1, then the board will contain level 1.
     */
    public Board(Level level) {
        this.level = new Level(level);
        board = new Element[level.getLevelHeight()][level.getLevelWidth()];
        String levelAsString = level.getLevel();
        int cpt = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char elementAsChar = levelAsString.charAt(cpt);
                cpt++;
                switch (elementAsChar) {
                    case 'B':
                        board[i][j] = new Wall();
                        break;
                    case 'R':
                        board[i][j] = new Rock();
                        break;
                    case '*':
                        board[i][j] = new Diamond();
                        amountOfDiamonds++;
                        break;
                    case 'M':
                        board[i][j] = new Player();
                        break;
                    case 'E':
                        board[i][j] = new Dirt();
                        break;
                    case ' ':
                        board[i][j] = null;
                        break;
                    case 'D':
                        board[i][j] = new Dirt();
                        doorPosition = new Position(i, j);
                        break;
                }
            }
        }
    }

    /**
     * This method inserts a given element in arguments at the given position in arguments inside our board. If there
     * is an element at the given position it being suppressed.
     *
     * @param element The element to place.
     * @param pos     The position where to place the element.
     */
    public void setElement(Element element, Position pos) {
        if (!contains(pos)) {
            throw new IllegalArgumentException("The position is outside the board");
        }
        board[pos.getRow()][pos.getCol()] = element;
    }

    /**
     * This method removes the element at the given position. This method sets the value at that position to null.
     *
     * @param pos The position where to remove the element.
     */
    public void dropElement(Position pos) {
        if (!contains(pos)) {
            throw new IllegalArgumentException("The position is outside of the board");
        }
        board[pos.getRow()][pos.getCol()] = null;
    }

    /**
     * This method checks if a given position is inside the board or not.
     *
     * @param pos The position to check.
     * @return Return true if inside the board or false otherwise.
     */
    public boolean contains(Position pos) {
        return ((pos.getRow() >= 0 && pos.getRow() < board.length)
                && (pos.getCol() >= 0 && pos.getCol() < board[0].length));
    }

    /**
     * This method gets the element at a given position. An exception is thrown if the position isn't inside the board.
     *
     * @param pos The position where to get the element.
     * @return Returns null if the position is null otherwise it returns the element of the board at that position.
     */
    public Element getElementAt(Position pos) {
        if (pos == null) {
            return null;
        }
        if (!contains(pos)) {
            throw new IllegalArgumentException("The position isn't inside the board");
        }
        return board[pos.getRow()][pos.getCol()];
    }

    /**
     * This method gets the position of a given element. This method will travel threw the board until it finds the
     * given element and constructs a position to be returned.
     *
     * @param elem The element to search.
     * @return Returns the position of the given element, null if the element isn't on the board.
     */
    public Position getElementPosition(Element elem) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == elem) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    /**
     * This method will search for the player threw the board until he finds him. A position is constructed to be
     * returned.
     *
     * @return Returns the position of the player, null if the player is not on the board.
     */
    public Position getPlayerPosition() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] instanceof Player) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    /**
     * This method builds a list containing all the rocks inside the board. To build this list, this method travels
     * threw the board, when it encounters a rock element, it adds that element to the list.
     *
     * @return Returns the list of all the rocks on the board.
     */
    public List<Element> getRocks() {
        List<Element> elements = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] instanceof Rock) {
                    elements.add(board[i][j]);
                }
            }
        }
        return elements;
    }

    /**
     * This method builds a list containing all the diamonds inside the board. To build this list, this method travels
     * threw the board, when it encounters a diamond element, it adds that element to the list.
     *
     * @return Returns the list of all the diamonds on the board.
     */
    public List<Element> getDiamonds() {
        List<Element> elements = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] instanceof Diamond) {
                    elements.add(board[i][j]);
                }
            }
        }
        return elements;
    }

    /**
     * This method builds a list containing all the rock positions inside the board. To build this list, this method
     * travels threw the board, when it encounters a rock element, it adds its position to the list.
     *
     * @return Returns the list of all the rock positions on the board.
     */
    public List<Position> getRockPositions() {
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] instanceof Rock) {
                    positions.add(new Position(i, j));
                }
            }
        }
        return positions;
    }

    /**
     * This method builds a list containing all the diamond positions inside the board. To build this list, this method
     * travels threw the board, when it encounters a diamond element, it adds its position to the list.
     *
     * @return Returns the list of all the diamond positions on the board.
     */
    public List<Position> getDiamondPositions() {
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] instanceof Diamond) {
                    positions.add(new Position(i, j));
                }
            }
        }
        return positions;
    }

    /**
     * Getter to get the position of the door aka exit.
     * @return Returns the position of where the door (exit) needs to appear.
     */
    public Position getDoorPosition() {
        return doorPosition;
    }

    /**
     * Getter for the height of the board.
     *
     * @return Returns the int value representing the height of the board.
     */
    public int getBoardHeight() {
        return board.length;
    }

    /**
     * Getter for the width of the board.
     *
     * @return Returns the int value representing the width of the board.
     */
    public int getBoardWidth() {
        return board[0].length;
    }

    /**
     * Getter for the amount of diamonds available on the level of the board.
     *
     * @return Returns the int value representing the amount of diamonds available on the level.
     */
    public double getAmountOfDiamonds() {
        return this.amountOfDiamonds;
    }

    /**
     * Getter for the amount of diamonds the player already captured.
     *
     * @return Returns the int value representing the amount of diamonds that have been captured.
     */
    public double getCollectedDiamonds() {
        return collectedDiamonds;
    }

    /**
     * Mutator for the collected diamonds attribute
     * @param collectedDiamonds The amount of collected diamonds to update.
     */
    public void setCollectedDiamonds(double collectedDiamonds) {
        this.collectedDiamonds = collectedDiamonds;
    }

    /**
     * Getter to know if the exit has appeared on the board or not.
     * @return Returns either tru or false if the exit appeared.
     */
    public boolean isExitAppeared() {
        return isExitAppeared;
    }

    /**
     * Mutator to change the value of the exitAppeared boolean attribute.
     * @param exitAppeared The value which you want to put in the boolean attribute.
     */
    public void setExitAppeared(boolean exitAppeared) {
        isExitAppeared = exitAppeared;
    }
}
