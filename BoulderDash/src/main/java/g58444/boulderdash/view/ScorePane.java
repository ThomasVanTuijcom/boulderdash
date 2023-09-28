package g58444.boulderdash.view;

import g58444.boulderdash.model.Model;
import g58444.boulderdash.oo.Observer;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * This class is a component of our java fx view. This class implements observer and is a subclass of GridPane. This
 * java fx component holds the label showing the score of the player in the current state.
 */
public class ScorePane extends GridPane implements Observer {
    private Model model;
    private Label exitDiamonds;
    private Label capturedDiamonds;
    private Label diamondStillOnBoard;

    /**
     * The constructor of the score pane needs to receive the model in arguments to know how many diamond there are on
     * the level and how many the player already collected.
     * @param model The model that we are working with.
     */
    public ScorePane(Model model){
        model.registerObserver(this);
        this.model = model;
        exitDiamonds = new Label("Amount of diamonds needed for exit: "
                + ((int) model.getAmountDiamonds()/2 + 1));
        capturedDiamonds = new Label("Amount of captured diamonds: " + (int) model.getCollectedDiamonds());
        diamondStillOnBoard = new Label("Amount of diamonds still on board: "
                + (int) (model.getAmountDiamonds() - model.getCollectedDiamonds()));
        this.add(exitDiamonds, 1, 1);
        this.add(capturedDiamonds, 2, 1);
        this.add(diamondStillOnBoard, 3, 1);

        this.setAlignment(Pos.CENTER);
        this.setHgap(50);
    }

    /**
     * This method updates this java fx component to his new state. This happens most of the time when the player moves.
     */
    @Override
    public void update() {
        capturedDiamonds.setText("Amount of captured diamonds: " + (int) model.getCollectedDiamonds());
        diamondStillOnBoard.setText("Amount of diamonds still on board: "
                + (int) (model.getAmountDiamonds() - model.getCollectedDiamonds()));
    }
}
