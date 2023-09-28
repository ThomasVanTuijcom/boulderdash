package g58444.boulderdash.model.elements;

import g58444.boulderdash.model.Board;
import g58444.boulderdash.model.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * The door is an element of our BoulderDash game. It doesn't do something specific, but at the start of the game it
 * will be not visible. It will only appear if the player collected enough diamond, the door will make it able to the
 * player to start the next level. This class is a subclass of the element abstract class.
 */
public class Door extends Element {
    /**
     * This method returns an empty list because, the door can't be moved to somewhere else.
     *
     * @param pos   The position of the element inside the board.
     * @param board The board where the element is placed on.
     * @return Return an empty list of positions.
     */
    @Override
    public List<Position> getPossibleMoves(Position pos, Board board) {
        return new ArrayList<>();
    }
}
