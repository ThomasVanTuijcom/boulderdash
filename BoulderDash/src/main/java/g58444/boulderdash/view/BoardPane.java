package g58444.boulderdash.view;

import g58444.boulderdash.model.Model;
import g58444.boulderdash.model.Position;
import g58444.boulderdash.model.elements.*;
import g58444.boulderdash.oo.Observer;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * This class is a java fx component of our fx view. This class extends GridPane, it holds inside it all the image
 * referring to them different element of our board. This class implements observer.
 */
public class BoardPane extends GridPane implements Observer {
    private Model model;
    private Element[][] showBoard;
    private int widthInView = 30;
    private int heightInView = 15;
    private int rowBeginInView;
    private int rowEndInView;
    private int columnBeginInView;
    private int columnEndInView;

    /**
     * The constructor of the board pane needs to receive the model as an argument to know where each element is placed
     * to transform them to their respective picture. The construct makes a 2D array and saves the current state of the
     * shown visual board, so that we can easily compare afterwards what changes had been made.
     *
     * The representations of each element:
     *      - Player -> Santa Claus
     *      - Diamond -> A gift
     *      - Dirt -> Snow
     *      - Door -> Santa's sled
     *      - Rock -> Christmas tree ball
     *      - Wall -> Chocolate wall
     *      - Empty -> Grass
     * @param model The model that we are using to play on.
     */
    public BoardPane(Model model) {
        this.model = model;
        showBoard = new Element[model.getBoardHeight()][model.getBoardWidth()];
        model.registerObserver(this);
        for (int i = 0; i < model.getBoardHeight(); i++) {
            for (int j = 0; j < model.getBoardWidth(); j++) {
                Image img = null;
                Element elem = model.getElement(new Position(i, j));
                showBoard[i][j] = elem;
                if (elem instanceof Player) {
                    img = new Image("/player.png", 32, 32, false, false);
                } else if (elem instanceof Wall) {
                    img = new Image("/wall.png", 32, 32, false, false);
                } else if (elem instanceof Diamond) {
                    img = new Image("/diamond.png", 32, 32, false, false);
                } else if (elem instanceof Rock) {
                    img = new Image("/rock.png", 32, 32, false, false);
                } else if (elem instanceof Dirt) {
                    img = new Image("/dirt.png", 32, 32, false, false);
                } else if (elem instanceof Door) {
                    img = new Image("/door.png", 32, 32, false, false);
                } else if (elem == null) {
                    img = new Image("/empty.png", 32, 32, false, false);
                }
                this.add(new ImageView(img), j, i);
            }
        }
        this.setAlignment(Pos.CENTER);
    }

    /**
     * This method is called when the board pane has to be updated, that arrives when the player made a movement. This
     * method travels to each case of the shown board. But if there is any difference between the board that is shown to
     * the user and the board that has been modified inside his class by the player movement. The image at that place
     * is updated by the right one.
     */
    @Override
    public void update() {
        int cpt = 0;
        for (int i = 0; i < model.getBoardHeight(); i++) {
            for (int j = 0; j < model.getBoardWidth(); j++) {
                Element shownElement = showBoard[i][j];
                Element boardElement = model.getElement(new Position(i, j));
                if (shownElement == null && boardElement != null) {
                    ImageView imgView = (ImageView) this.getChildren().get(cpt);
                    Image img = null;
                    Element elem = model.getElement(new Position(i, j));
                    if (elem instanceof Player) {
                        img = new Image("/player.png", 32, 32, false, false);
                    } else if (elem instanceof Wall) {
                        img = new Image("/wall.png", 32, 32, false, false);
                    } else if (elem instanceof Diamond) {
                        img = new Image("/diamond.png", 32, 32, false, false);
                    } else if (elem instanceof Rock) {
                        img = new Image("/rock.png", 32, 32, false, false);
                    } else if (elem instanceof Dirt) {
                        img = new Image("/dirt.png", 32, 32, false, false);
                    } else if (elem instanceof Door) {
                        img = new Image("/door.png", 32, 32, false, false);
                    } else if (elem == null) {
                        img = new Image("/empty.png", 32, 32, false, false);
                    }
                    imgView.setImage(img);
                    showBoard[i][j] = elem;
                } else if (boardElement == null || (shownElement.getClass() != boardElement.getClass())) {
                    ImageView imgView = (ImageView) this.getChildren().get(cpt);
                    Image img = null;
                    Element elem = model.getElement(new Position(i, j));
                    if (elem instanceof Player) {
                        img = new Image("/player.png", 32, 32, false, false);
                    } else if (elem instanceof Wall) {
                        img = new Image("/wall.png", 32, 32, false, false);
                    } else if (elem instanceof Diamond) {
                        img = new Image("/diamond.png", 32, 32, false, false);
                    } else if (elem instanceof Rock) {
                        img = new Image("/rock.png", 32, 32, false, false);
                    } else if (elem instanceof Dirt) {
                        img = new Image("/dirt.png", 32, 32, false, false);
                    } else if (elem instanceof Door) {
                        img = new Image("/door.png", 32, 32, false, false);
                    } else if (elem == null) {
                        img = new Image("/empty.png", 32, 32, false, false);
                    }
                    imgView.setImage(img);
                    showBoard[i][j] = elem;
                }
                cpt++;
            }
        }
    }
}
