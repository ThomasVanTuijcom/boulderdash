package g58444.boulderdash.model.elements;

import g58444.boulderdash.model.Board;
import g58444.boulderdash.model.Direction;
import g58444.boulderdash.model.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * A rock is an element of the BoulderDash game. It is an uncollectible element by the player inside our game. The
 * rock is subject to gravity, so if the rock has place underneath him, he will fall or slide on a stone a fall
 * further on. This class is a subclass of the element abstract class.
 */
public class Rock extends Element {
    /**
     * The rock can fall vertically, slide on a rock/diamond or kill the player if he can. The rock has some
     * priorities, that means that the first move that he will add to the list if he can, is the move to kill a player.
     * This is very important because, if the rock has more than one move available, when he will have to move later
     * on. It will always prefer to kill the player than move somewhere else. The next priority is the vertical move,
     * finishing by the side moves with priority on the left side.
     * <p>
     * Priorities:
     * <p>
     * Kill player -> Vertical -> Left -> Right
     *
     * @param pos   The position of the element inside the board.
     * @param board The board where the element is placed on.
     * @return Returns a list of positions, that are all the possible places where the diamond can move.
     */
    public List<Position> getPossibleMoves(Position pos, Board board) {
        if (board.getElementAt(pos) == null) {
            throw new IllegalArgumentException("There is no rock at that position");
        }
        List<Position> possibleMoves = new ArrayList<Position>();
        Position east = pos.next(Direction.E);
        Position west = pos.next(Direction.W);
        Position south = pos.next(Direction.S);
        Position southwest = south.next(Direction.W);
        Position southeast = south.next(Direction.E);
        Position doubleSouth = pos.next(Direction.S).next(Direction.S);
        if (board.contains(doubleSouth) && board.getElementAt(doubleSouth) instanceof Player
                && board.getElementAt(south) == null) {
            possibleMoves.add(doubleSouth);
        }
        if (board.contains(south) && board.getElementAt(south) == null) {
            possibleMoves.add(south);
        }
        if (board.contains(southwest)
                && (board.getElementAt(south) instanceof Rock || board.getElementAt(south) instanceof Diamond)
                && board.getElementAt(west) == null && board.getElementAt(southwest) == null) {
            possibleMoves.add(west);
        }
        if (board.contains(southeast)
                && (board.getElementAt(south) instanceof Rock || board.getElementAt(south) instanceof Diamond)
                && board.getElementAt(east) == null && board.getElementAt(southeast) == null) {
            possibleMoves.add(east);
        }
        return possibleMoves;
    }
}
