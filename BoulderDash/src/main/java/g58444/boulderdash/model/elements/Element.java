package g58444.boulderdash.model.elements;

import g58444.boulderdash.model.Board;
import g58444.boulderdash.model.Position;

import java.util.List;

/**
 * An element of the BoulderDash game. This class is abstract and is the parent of all the different elements of the
 * BoulderDash game.
 */
public abstract class Element {
    /**
     * This method looks for all the possible moves of an element depending on his position inside a given board. This
     * method is abstract, so this method will have to be implemented by the different elements.
     *
     * @param pos   The position of the element inside the board.
     * @param board The board where the element is placed on.
     * @return Returns a list of positions containing all the possible moves starting from the position on the board.
     */
    public abstract List<Position> getPossibleMoves(Position pos, Board board);

}
