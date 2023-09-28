package g58444.boulderdash.controller;

import g58444.boulderdash.model.Direction;
import g58444.boulderdash.model.Model;
import g58444.boulderdash.view.BoardPane;
import g58444.boulderdash.view.ScorePane;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;

/**
 * This class is the controller used by the FxView (the javafx view). This controller is a layer between the model and
 * the view.
 */
public class FxController {
    private Model model;

    /**
     * The constructor of the controller only needs the model to work with.
     *
     * @param model The model that the controller will be working with.
     */
    public FxController(Model model) {
        this.model = model;
    }

    /**
     * This method calls the function of the model to build a level by giving a level number.
     *
     * @param level The number of the level you want to build.
     */
    public void buildLevel(int level) {
        model.buildLevel(level);
    }

    /**
     * This method calls to function to move the player from the model, with a direction depending on the key event that
     * the function receives in arguments. If the key event isn't an arrow key, the event will be consumed.
     *
     * @param keyEvent The key event that has been made.
     */
    public void move(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                model.movePlayer(Direction.N);
                break;
            case DOWN:
                model.movePlayer(Direction.S);
                break;
            case RIGHT:
                model.movePlayer(Direction.E);
                break;
            case LEFT:
                model.movePlayer(Direction.W);
                break;
            default:
                keyEvent.consume();
                break;
        }
    }

    /**
     * This method checks if the player has won.
     *
     * @return Returns true if he won the level false otherwise.
     */
    public boolean isWon() {
        return model.hasPlayerWon();
    }

    /**
     * This method checks if the level is finished, that counts if the player won or lost the level.
     *
     * @return Returns true if the level is finished false otherwise.
     */
    public boolean isLevelFinished() {
        return model.isGameOver();
    }

    /**
     * This method says if the player has collected enough of diamonds to let the exit appear.
     *
     * @return Returns true if he has enough false otherwise.
     */
    public boolean hasEnoughDiamond() {
        return (model.getCollectedDiamonds() / model.getAmountDiamonds() >= 0.5);
    }

    /**
     * This method is called when the level is over, this method will make the decision to restart the level or start
     * the next level depending on if the player won or not.
     */
    public void manageEndLevel() {
        if (model.hasPlayerWon()) {
            model.nextLevel();
        } else {
            model.restartLevel();
        }
    }

    /**
     * This method resets the board, resetting the board happens when we restart or start a level. The existing observer
     * is removed from the observable to let the place to the new ones.
     *
     * @param board The old board to remove as observer.
     * @param score The old score to remove as observer.
     */
    public void resetBoard(Node board, Node score) {
        model.removeObserver((BoardPane) board);
        model.removeObserver((ScorePane) score);
    }

    /**
     * This method is called when we want to undo a move.
     */
    public void undoMove() {
        model.undo();
    }

    /**
     * This method is called when we want to redo a move.
     */
    public void redoMove() {
        model.redo();
    }
}

