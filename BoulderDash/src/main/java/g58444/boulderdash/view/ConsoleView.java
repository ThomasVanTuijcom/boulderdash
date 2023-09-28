package g58444.boulderdash.view;

import g58444.boulderdash.model.Model;
import g58444.boulderdash.model.Position;
import g58444.boulderdash.model.elements.*;

import java.util.Scanner;

/**
 * This class is the view used for the console view of our game.
 */
public class ConsoleView{
    private Model model;

    /**
     * The constructor of this view only needs the model that we are working with.
     * @param model The model that we are working with.
     */
    public ConsoleView(Model model){
        this.model = model;
    }

    /**
     * Prints in the console a title to present the game author and the game name.
     */
    public void showTitle(){
        System.out.println("--------------------------------------------");
        System.out.println("-------------BOULDER DASH 58444-------------");
        System.out.println("--------------------------------------------");
        System.out.println();
    }

    /**
     * Prints in the console the board in his current state. The elements are represented by characters.
     *
     * Representation of the elements:
     *      - Player -> P
     *      - Diamond -> *
     *      - Dirt -> D
     *      - Door -> E
     *      - Rock -> R
     *      - Wall -> W
     *      - Empty -> [empty space]
     */
    public void showBoard(){
        for (int i = 0; i < model.getBoardHeight(); i++){
            for (int j = 0; j < model.getBoardWidth(); j++){
                Element element = model.getElement(new Position(i, j));
                if (element instanceof Player){
                    System.out.print('P');
                } else if (element instanceof Dirt){
                    System.out.print('D');
                } else if (element instanceof Diamond){
                    System.out.print('*');
                } else if (element instanceof Rock){
                    System.out.print('R');
                } else if(element instanceof Wall){
                    System.out.print('W');
                } else if(element instanceof Door){
                    System.out.print('E');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }

    /**
     * Asks the user for the next move that he want to do. The user can enter the directions where he wants to go, or he
     * can also enter undo or redo to re-execute or unexecute a command. The user has also the possibility to enter
     * abandon that will redirect him to the level selector.
     *
     * The directions: up, down, right or left
     * @return
     */
    public String askNextMove() {
        System.out.println("What's your next move?");
        Scanner kbd = new Scanner(System.in);
        return kbd.nextLine();
    }

    /**
     * Prints the amount of diamonds on the level and the amount of diamonds the player already captured.
     */
    public void showCapturedDiamonds() {
        System.out.println("There are " + (int) model.getAmountDiamonds() + " on the board");
        System.out.println("You have captured " + (int) model.getCollectedDiamonds() + " until now");
    }

    /**
     * Prints a message that says that the game is over.
     */
    public void showEndGame() {
        System.out.println("---------------------------------------------");
        System.out.println("------------------GAME OVER------------------");
        System.out.println("---------------------------------------------");
    }

    /**
     * Prints a message that says that the level is won.
     */
    public void showWinLevel() {
        System.out.println("--------------------------------------------");
        System.out.println("----------------YOU HAVE WON----------------");
        System.out.println("--------------------------------------------");
    }

    /**
     * Prints a message that says that the level is lost.
     */
    public void showLostLevel() {
        System.out.println("----------------------------------------------");
        System.out.println("----------------YOU HAVE LOST-----------------");
        System.out.println("----------------------------------------------");
    }

    /**
     * Asks the user to enter a number of the level he wants to start with.
     * @return
     */
    public int askLevel() {
        Scanner kbd = new Scanner(System.in);
        System.out.println("Which level do you want to start up?");
        while (!kbd.hasNextInt()) {
            kbd.next();
            System.out.println("This is not a number, please try again.");
        }
        return kbd.nextInt();
    }

    /**
     * Asks the user at the end of a level if he wants to continue playing or finish playing. This is a yes/no question.
     * @return Returns a String with the value yes or no depending on the user's answer.
     */
    public String askContinue(){
        Scanner kbd = new Scanner(System.in);
        if(model.hasPlayerWon()){
            System.out.println("Do you want to go to the next level? (yes/no)");
        } else{
            System.out.println("Do you want to restart the level? (yes/no)");
        }
        boolean yn = true;
        while (yn){
            String answer = kbd.nextLine();
            if (answer.equals("yes") || answer.equals("no")){
                yn = false;
                return answer;
            } else{
                System.out.println("You have to choose between yes or no, please try again.");
            }
        }
        return null;
    }

}
