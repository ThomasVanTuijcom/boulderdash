package g58444.boulderdash.model.elements;

import g58444.boulderdash.model.Board;
import g58444.boulderdash.model.Direction;
import g58444.boulderdash.model.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * The player is an element of our BoulderDash game. The player is probably the most important element of our game. The
 * user will travel threw the game thanks to this element. A player isn't subject to gravity, but he can move in 4
 * directions: North (UP), South (DOWN), West (LEFT) and East (RIGHT). But there are some conditions to it, the player
 * can be blocked by the walls or the rocks. When a player encounter a diamond he can collect it. The player can also
 * push the rocks to fall. This class is a subclass of the element abstract class.
 */
public class Player extends Element {

    /**
     * This method will return all the possible positions where the player can move depending on his position on the
     * board. The player can step on the next elements: dirt, diamonds, door or empty elements. There is no system of
     * priority for the player, because the user can choose where he wants to go.
     *
     * @param pos   The position of the element inside the board.
     * @param board The board where the element is placed on.
     * @return Returns a list of positions containing all the positions where the player could move.
     */
    public List<Position> getPossibleMoves(Position pos, Board board) {
        if (board.getElementAt(pos) == null) {
            throw new IllegalArgumentException("There is no player at that position");
        }
        List<Position> possibleMoves = new ArrayList<Position>();
        Position east = pos.next(Direction.E);
        Position west = pos.next(Direction.W);
        Position north = pos.next(Direction.N);
        Position south = pos.next(Direction.S);
        if (board.getElementAt(east) == null || board.getElementAt(east) instanceof Dirt
                || board.getElementAt(east) instanceof Diamond || board.getElementAt(east) instanceof Door) {
            possibleMoves.add(east);
        }
        if (board.getElementAt(west) == null || board.getElementAt(west) instanceof Dirt
                || board.getElementAt(west) instanceof Diamond || board.getElementAt(west) instanceof Door) {
            possibleMoves.add(west);
        }
        if (board.getElementAt(north) == null || board.getElementAt(north) instanceof Dirt
                || board.getElementAt(north) instanceof Diamond || board.getElementAt(north) instanceof Door) {
            possibleMoves.add(north);
        }
        if (board.getElementAt(south) == null || board.getElementAt(south) instanceof Dirt
                || board.getElementAt(south) instanceof Diamond || board.getElementAt(south) instanceof Door) {
            possibleMoves.add(south);
        }
        if (board.getElementAt(east) instanceof Rock && board.getElementAt(east.next(Direction.E)) == null) {
            possibleMoves.add(east);
        }
        if (board.getElementAt(west) instanceof Rock && board.getElementAt(west.next(Direction.W)) == null) {
            possibleMoves.add(west);
        }
        return possibleMoves;
    }

}
