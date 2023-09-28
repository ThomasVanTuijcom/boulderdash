package g58444.boulderdash.model;

import g58444.boulderdash.model.commands.Command;
import g58444.boulderdash.model.commands.CommandsManager;
import g58444.boulderdash.model.commands.MoveCommand;
import g58444.boulderdash.model.elements.Element;
import g58444.boulderdash.oo.Observable;
import g58444.boulderdash.oo.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * For this project we are using the facade design pattern, this class is the facade of our project. This class
 * has the major game functions to launch a level, restart a level etc. This class implements Observable because we are
 * also using the Observer design pattern.
 */
public class Model implements Observable {
    private Board board;
    private int currentLevel;
    private boolean exitVisible = false;
    private CommandsManager manager;
    private List<Observer> observers = new ArrayList<>();

    /**
     * The constructor of our facade doesn't need any argument. It will initialize a command manager to be able to
     * undo or redo certain moves.
     */
    public Model() {
        this.manager = new CommandsManager();
    }

    /**
     * This method builds the level given in arguments. The argument must be between 1 and 10. Because only 10 levels
     * are available. This method initialize the board where we will be playing on.
     *
     * @param level The level number you want to start up.
     */
    public void buildLevel(int level) {
        if (level < 1 || level > 10) {
            throw new IllegalArgumentException("Levels available are: 1 to 10");
        }
        this.board = new Board(new Level(level));
        currentLevel = level;
    }

    /**
     * This method is called to start the next level, it calls the buildLevel(int level) method. This happens when the
     * player wins the level.
     */
    public void nextLevel() {
        buildLevel(++currentLevel);
        this.exitVisible = false;
    }

    /**
     * This method is called to restart the current level, it calls the buildLevel(int level) method. This happens when
     * the player loses the level.
     */
    public void restartLevel() {
        buildLevel(currentLevel);
        this.exitVisible = false;
    }

    /**
     * This method checks if the current level is finished or not. The level is finished if the player does not exist
     * on the board anymore, that means that the player has been killed by another element. Or the level is finished
     * when the player is on the exit with enough diamonds.
     *
     * @return Returns true when the level is finished, false otherwise.
     */
    public boolean isGameOver() {
        if (board.getPlayerPosition() == null) {
            return true;
        } else if ((board.getCollectedDiamonds() / board.getAmountOfDiamonds()) >= 0.5
                && board.getPlayerPosition().equals(board.getDoorPosition())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method looks if the player has won or not. The player has won the level when he has enough diamonds to
     * escape and that the player finds himself on the exit door. When that's not the case he didn't win.
     *
     * @return Returns true when he won and false otherwise.
     */
    public boolean hasPlayerWon() {
        if ((board.getCollectedDiamonds() / board.getAmountOfDiamonds()) >= 0.5
                && board.getPlayerPosition().equals(board.getDoorPosition())) {
            return true;
        }
        return false;
    }

    /**
     * This method uses the method to get an element at a given position from the board.
     *
     * @param pos The position where to get the element.
     * @return Returns the element at that position, null ever there is no element.
     */
    public Element getElement(Position pos) {
        return board.getElementAt(pos);
    }

    /**
     * This method moves to player to a certain direction. This method creates a command, that represents the move. This
     * command will be registered inside a history because, the user could choose to cancel a move he made or redo it.
     * When this method is called it will notify his observers to update.
     *
     * @param dir The direction where to move the player.
     */
    public void movePlayer(Direction dir) {
        Command command = new MoveCommand(board, dir);
        manager.register(command);
        command.execute();
        notifyObserver();
    }

    /**
     * This method is called to undo a move that has been made. It calls the undo method of the command manager.
     * Afterwards it notifies the observers of that change.
     */
    public void undo() {
        manager.undo();
        notifyObserver();
    }

    /**
     * This method is called to redo a move that has been made. It calls the redo method of the command manager.
     * Afterwards it notifies the observers of that change.
     */
    public void redo() {
        manager.redo();
        notifyObserver();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * Getter for the amount of diamond the player collected.
     *
     * @return Returns the double value of the amount of diamonds the player collected.
     */
    public double getCollectedDiamonds() {
        return board.getCollectedDiamonds();
    }

    /**
     * Getter for the amount of diamond on the level.
     *
     * @return Returns the double value of the amount of diamonds on the level.
     */
    public double getAmountDiamonds() {
        return board.getAmountOfDiamonds();
    }

    /**
     * Getter for the height of the board.
     *
     * @return Returns the int value of the board height.
     */
    public int getBoardHeight() {
        return board.getBoardHeight();
    }

    /**
     * Getter for the width of the board.
     *
     * @return Returns the int value of the board width.
     */
    public int getBoardWidth() {
        return board.getBoardWidth();
    }
}

