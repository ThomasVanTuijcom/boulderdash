package g58444.boulderdash.view;

import g58444.boulderdash.controller.FxController;
import g58444.boulderdash.model.Model;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class is the javafx view, this class assemble all are components needed to represent our game in a fx view.
 */
public class FxView {
    private FxController controller;
    private Model model;
    private Scene scene;
    private VBox root;
    private GridPane score;
    private GridPane board;
    private ChoiceBox<Integer> levelChooser;
    private Button validateBtn;
    private Label chooseLevelLbl;
    private HBox buttons;
    private Button undoBtn;
    private Button redoBtn;
    private Button abandon;
    private Label discreteMessage;

    /**
     * The constructor of our FXView needs 3 arguments, the model that we are working with, the controller associated
     * with the FxView and the stage of our application. This usage of our model is in read-only.
     * @param model The model that we are working with.
     * @param controller The controller having the functions we will have to call.
     * @param stage The stage of our application.
     */
    public FxView(Model model, FxController controller, Stage stage) {
        this.controller = controller;
        this.model = model;

        this.root = new VBox();
        this.scene = new Scene(root);
        this.levelChooser = new ChoiceBox<>();
        this.validateBtn = new Button("Play this level");
        this.chooseLevelLbl = new Label("Choose a level:");
        this.buttons = new HBox();
        this.undoBtn = new Button("Undo");
        this.redoBtn = new Button("Redo");
        this.abandon = new Button("Abandon");
        this.discreteMessage = new Label();

        levelChooser.getItems().addAll(1, 2, 3, 4, 5);
        levelChooser.setValue(1);
        undoBtn.setFocusTraversable(false);
        redoBtn.setFocusTraversable(false);
        abandon.setFocusTraversable(false);
        buttons.getChildren().addAll(undoBtn, redoBtn, abandon);

        undoBtn.addEventHandler(ActionEvent.ACTION, e -> {
            controller.undoMove();
            if (!controller.hasEnoughDiamond()){
                discreteMessage.setText("");
            }
        });

        redoBtn.addEventHandler(ActionEvent.ACTION, e -> {
            controller.redoMove();
        });

        abandon.addEventHandler(ActionEvent.ACTION, e -> {
            start();
        });

        validateBtn.addEventHandler(ActionEvent.ACTION, e -> {
            controller.buildLevel(levelChooser.getValue());
            showLevel();
        });

        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            try {
                controller.move(keyEvent);
            } catch (Exception ex) {
                discreteMessage.setText(ex.getMessage());
                Timeline timer = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                    discreteMessage.setText("");
                }));
                timer.playFromStart();
            }
            if (controller.hasEnoughDiamond() && discreteMessage.getText().isEmpty()) {
                discreteMessage.setText("You have enough diamonds to escape, your sled has appeared Santa !");
            }
            if (controller.isLevelFinished()) {
                if (controller.isWon()) {
                    discreteMessage.setText("You have won, next level is loading...");
                    Timeline timer = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                        discreteMessage.setText("");
                    }));
                    timer.playFromStart();
                } else {
                    discreteMessage.setText("You have lost, the level is restarting...");
                    Timeline timer = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
                        discreteMessage.setText("");
                    }));
                    timer.playFromStart();
                }
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.5), e -> {
                    endLevelManagement();
                }));
                timeline.playFromStart();
            }
        });

        stage.setTitle("Boulderdash - 58444");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is the first method that will be called by our view, this will be the first panel the user will
     * see. He can choose his level on this panel.
     */
    public void start() {
        root.getChildren().clear();
        root.getChildren().add(chooseLevelLbl);
        root.getChildren().add(levelChooser);
        root.getChildren().add(validateBtn);

        root.setAlignment(Pos.CENTER);
        buttons.setAlignment(Pos.CENTER);

        chooseLevelLbl.setAlignment(Pos.CENTER);
        validateBtn.setAlignment(Pos.CENTER);
    }

    /**
     * Once the user validate a level, this function is called and it will construct the level the user chose.
     */
    public void showLevel() {
        root.getChildren().clear();
        this.board = new BoardPane(model);
        this.score = new ScorePane(model);

        root.getChildren().add(score);
        root.getChildren().add(board);
        root.getChildren().add(discreteMessage);
        root.getChildren().add(buttons);
    }

    /**
     * This method is called when a level is finished, it will manage the end of a level by reloading the current level
     * or starting the next one.
     */
    public void endLevelManagement() {
        try {
            controller.manageEndLevel();
        }catch (IllegalArgumentException ex){
            Alert won = new Alert(Alert.AlertType.INFORMATION, "Game Over");
            won.setHeaderText("You have won all the levels available.");
            won.setContentText("More could be added later on. You will be transferred back to the level chooser");
            won.show();
            Timeline time = new Timeline(new KeyFrame(Duration.seconds(2), e->{
                start();
            }));
            time.play();
        }
        controller.resetBoard(board, score);
        root.getChildren().clear();
        this.board = new BoardPane(model);
        this.score = new ScorePane(model);
        root.getChildren().add(score);
        root.getChildren().add(board);
        root.getChildren().add(discreteMessage);
        root.getChildren().addAll(buttons);
    }
}
