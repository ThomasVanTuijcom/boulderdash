package g58444.boulderdash.model.elements;

import g58444.boulderdash.model.Board;
import g58444.boulderdash.model.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * The wall is an element of our BoulderDash game. The wall be surrounding our levels to box the player inside them. The
 * wall isn't subject to gravity, it does nothing except blocking the player. This class is a subclass of the element
 * abstract class.
 */
public class Wall extends Element {
    /**
     * This method returns an empty list because, the wall can't be moved to somewhere else.
     *
     * @param pos   The position of the element inside the board.
     * @param board The board where the element is placed on.
     * @return Return an empty list of positions.
     */
    public List<Position> getPossibleMoves(Position pos, Board board) {
        return new ArrayList<>();
    }
}
