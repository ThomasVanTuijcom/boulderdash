package g58444.boulderdash.model.elements;

import g58444.boulderdash.model.Board;
import g58444.boulderdash.model.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * The dirt is an element of the BoulderDash game. It does nothing except blocking diamonds and rocks to fall. When the
 * player steps on the dirt element it will disappear. This element is not subject to gravity.  This class is a subclass
 * of the element abstract class.
 */
public class Dirt extends Element {
    /**
     * This method returns an empty list because, the dirt can't be moved to somewhere else.
     *
     * @param pos   The position of the element inside the board.
     * @param board The board where the element is placed on.
     * @return Return an empty list of positions.
     */
    public List<Position> getPossibleMoves(Position pos, Board board) {
        return new ArrayList<>();
    }
}
