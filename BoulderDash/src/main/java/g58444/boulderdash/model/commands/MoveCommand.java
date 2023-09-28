package g58444.boulderdash.model.commands;

import g58444.boulderdash.model.Board;
import g58444.boulderdash.model.Direction;
import g58444.boulderdash.model.Position;
import g58444.boulderdash.model.elements.*;

import java.util.List;

/**
 * This class implements the command interface. It represents the command to make the player move on the board. Its
 * receiver is the board. This command could be executed as unexecuted if we want to cancel a move. So this command will
 * be reversible if it hasn't been undone yet.
 */
public class MoveCommand implements Command {
    private Board board;
    private Direction direction;
    private boolean hasBeenUndone = false;
    private boolean collectedADiamond = false;
    private boolean exitAppeared = false;
    private List<Position> oldRockPositions;
    private List<Position> oldDiamondPositions;
    private Element playerDestinationElement;

    /**
     * The constructor of the command needs to receive the board and the direction where to move the player. Those
     * arguments are assigned to their respective attributes.
     *
     * @param board The board where to move the player.
     * @param dir   The direction where we want to move the player.
     */
    public MoveCommand(Board board, Direction dir) {
        this.board = board;
        this.direction = dir;
    }

    /**
     * This method is the execute method of our command. At the first place it will save the positions of all the rocks
     * and diamonds so that if we have to unexecute the command, we could place them on their old positions.
     */
    @Override
    public void execute() {
        //Saving the diamonds and rock positions
        oldDiamondPositions = board.getDiamondPositions();
        oldRockPositions = board.getRockPositions();

        //Moving the player
        Position playerPosition = board.getPlayerPosition();
        Player player = (Player) board.getElementAt(playerPosition);
        List<Position> possibleMoves = player.getPossibleMoves(playerPosition, board);
        Position destination = playerPosition.next(direction);
        if (possibleMoves.contains(destination)) {
            if (board.getElementAt(destination) instanceof Diamond) {
                board.setCollectedDiamonds(board.getCollectedDiamonds() + 1);
                collectedADiamond = true;
            }
            if (board.getElementAt(destination) instanceof Rock) {
                board.setElement(new Rock(), destination.next(direction));
            }
            playerDestinationElement = board.getElementAt(destination);
            board.dropElement(playerPosition);
            board.setElement(player, destination);
        } else {
            throw new IllegalArgumentException("You can't go over there, please change your direction !");
        }

        //Moving the rocks
        List<Element> rocks = board.getRocks();
        for (int i = 0; i < rocks.size(); i++) {
            Position elemPos = board.getElementPosition(rocks.get(i));
            Element rock = board.getElementAt(elemPos);
            if (elemPos != null) {
                List<Position> moves = rock.getPossibleMoves(elemPos, board);
                while (!moves.isEmpty()) {
                    board.dropElement(elemPos);
                    if (board.getElementAt(moves.get(0)) instanceof Player) {
                        board.setElement(rock, moves.get(0));

                        board.dropElement(moves.get(0));
                        board.dropElement(moves.get(0).next(Direction.S));
                        board.dropElement(moves.get(0).next(Direction.N));
                        board.dropElement(moves.get(0).next(Direction.E));
                        board.dropElement(moves.get(0).next(Direction.W));
                        board.dropElement(moves.get(0).next(Direction.S).next(Direction.E));
                        board.dropElement(moves.get(0).next(Direction.S).next(Direction.W));
                        board.dropElement(moves.get(0).next(Direction.N).next(Direction.E));
                        board.dropElement(moves.get(0).next(Direction.N).next(Direction.W));
                        moves.clear();
                        rocks.remove(rock);
                        i = 0;
                    } else {
                        board.setElement(rock, moves.get(0));
                        elemPos = board.getElementPosition(rock);
                        moves = rock.getPossibleMoves(moves.get(0), board);
                        i = 0;
                    }
                }
            }
        }

        //Moving the diamonds
        List<Element> diamonds = board.getDiamonds();
        for (int i = 0; i < diamonds.size(); i++) {
            Position elemPos = board.getElementPosition(diamonds.get(i));
            Element diamond = board.getElementAt(elemPos);
            if (elemPos != null) {
                List<Position> moves = diamond.getPossibleMoves(elemPos, board);
                while (!moves.isEmpty()) {
                    board.dropElement(elemPos);
                    if (board.getElementAt(moves.get(0)) instanceof Player) {
                        board.setElement(diamond, moves.get(0));

                        board.dropElement(moves.get(0));
                        board.dropElement(moves.get(0).next(Direction.S));
                        board.dropElement(moves.get(0).next(Direction.N));
                        board.dropElement(moves.get(0).next(Direction.E));
                        board.dropElement(moves.get(0).next(Direction.W));
                        board.dropElement(moves.get(0).next(Direction.S).next(Direction.E));
                        board.dropElement(moves.get(0).next(Direction.S).next(Direction.W));
                        board.dropElement(moves.get(0).next(Direction.N).next(Direction.E));
                        board.dropElement(moves.get(0).next(Direction.N).next(Direction.W));
                        moves.clear();
                        rocks.remove(diamond);
                        i = 0;
                    } else {
                        board.setElement(diamond, moves.get(0));
                        elemPos = board.getElementPosition(diamond);
                        moves = diamond.getPossibleMoves(moves.get(0), board);
                        i = 0;
                    }
                }
            }
        }

        hasBeenUndone = false;

        //Let the door appear when it can
        if (!board.isExitAppeared() && board.getCollectedDiamonds() / board.getAmountOfDiamonds() >= 0.5){
            board.setElement(new Door(), board.getDoorPosition());
            board.setExitAppeared(true);
        }
    }

    /**
     * This method unexecutes (cancel) this command. It puts the board to his state before the command has been
     * executed.
     */
    @Override
    public void unexecute() {
        try {
            Position playerPosition = board.getPlayerPosition();
            Player player = (Player) board.getElementAt(playerPosition);
            if (direction == Direction.W) {
                board.setElement(player, playerPosition.next(Direction.E));
            } else if (direction == Direction.S) {
                board.setElement(player, playerPosition.next(Direction.N));
            } else if (direction == Direction.E) {
                board.setElement(player, playerPosition.next(Direction.W));
            } else if (direction == Direction.N) {
                board.setElement(player, playerPosition.next(Direction.S));
            }
            board.setElement(playerDestinationElement, playerPosition);
            for (Position pos : oldRockPositions) {
                board.setElement(new Rock(), pos);
            }
            for (Position pos : oldDiamondPositions) {
                board.setElement(new Diamond(), pos);
            }
            if (collectedADiamond) {
                board.setCollectedDiamonds(board.getCollectedDiamonds() - 1);
            }
            hasBeenUndone = true;
            if (board.getCollectedDiamonds() / board.getAmountOfDiamonds() < 0.5) {
                board.dropElement(board.getDoorPosition());
                board.setElement(new Dirt(), board.getDoorPosition());
                board.setExitAppeared(false);
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * This method says if the command is reversible. It is reversible if the command hasn't been undone yet.
     *
     * @return Returns true if it is reversible false otherwise.
     */
    @Override
    public boolean isReversible() {
        return !hasBeenUndone;
    }
}
