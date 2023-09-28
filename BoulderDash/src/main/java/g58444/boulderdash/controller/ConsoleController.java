package g58444.boulderdash.controller;

import g58444.boulderdash.model.Direction;
import g58444.boulderdash.model.Model;
import g58444.boulderdash.view.ConsoleView;

/**
 * This class is the controller of our MVC used for the console output of our game. This controller calls functions
 * from the model and the view that is used for the console.
 */
public class ConsoleController {
    private Model model;
    private ConsoleView consoleView;

    /**
     * The constructor of the controller needs to receive the model and the view he has to work with.
     *
     * @param model The model to work with.
     * @param view  The view that has to be displayed.
     */
    public ConsoleController(Model model, ConsoleView view) {
        this.model = model;
        this.consoleView = view;
    }

    /**
     * This method is called to start the game up, it brings all the parts of the model and the view together. The game
     * will be playable with console inputs.
     */
    public void start() {
        consoleView.showTitle();
        boolean playing = true;
        int numberLevel = consoleView.askLevel();
        model.buildLevel(numberLevel);
        consoleView = new ConsoleView(model);
        while (playing) {
            consoleView.showCapturedDiamonds();
            consoleView.showBoard();
            String response = consoleView.askNextMove();
            try {
                switch (response) {
                    case "up":
                        model.movePlayer(Direction.N);
                        break;
                    case "down":
                        model.movePlayer(Direction.S);
                        break;
                    case "right":
                        model.movePlayer(Direction.E);
                        break;
                    case "left":
                        model.movePlayer(Direction.W);
                        break;
                    case "undo":
                        model.undo();
                        break;
                    case "redo":
                        model.redo();
                        break;
                    case "abandon":
                        start();
                        break;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            if (model.isGameOver()) {
                consoleView.showBoard();
                if (model.hasPlayerWon()) {
                    consoleView.showWinLevel();
                    if (consoleView.askContinue().equals("yes")) {
                        try {
                            model.nextLevel();
                        } catch (IllegalArgumentException ex) {
                            playing = false;
                        }
                    } else {
                        playing = false;
                    }
                } else {
                    consoleView.showLostLevel();
                    if (consoleView.askContinue().equals("yes")) {
                        model.buildLevel(numberLevel);
                    } else {
                        playing = false;
                    }
                }
            }
        }
        consoleView.showEndGame();
    }
}
