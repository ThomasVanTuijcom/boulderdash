package g58444.boulderdash.model;

import java.util.Objects;

/**
 * A position inside our board, a position is composed of a row value and a column value.
 */
public class Position {
    private int row;
    private int col;

    /**
     * The constructor of a position that requires two arguments: the first one for the row value and the second one for
     * the column. Those arguments will be assigned to their private attributes.
     *
     * @param row    The row value.
     * @param column The column value.
     */
    public Position(int row, int column) {
        this.row = row;
        this.col = column;
    }

    /**
     * This method calculates the next position departing from this position to another one by given a direction.
     *
     * @param dir The direction where you want to have the next position.
     * @return Return the Position of the position in the given direction.
     */
    public Position next(Direction dir) {
        return (new Position(row + dir.getDeltaRow(), col + dir.getDeltaColumn()));
    }

    /**
     * Getter of the row value.
     *
     * @return Return the int value of the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter of the column value.
     *
     * @return Return the int value of the column
     */
    public int getCol() {
        return col;
    }

    /**
     * This method transforms our position into a String representation.
     *
     * @return Return the String representation of the position.
     */
    @Override
    public String toString() {
        return "Position: (" + row + ", " + col + ")";
    }

    /**
     * This method looks if two given positions are the same, if the row value and the column value of each one are
     * equal to each other, they are said as equal.
     *
     * @param o The position that you want to compare with.
     * @return Return a boolean value if they are equal or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    /**
     * This method transforms our position into a hashed int representation.
     *
     * @return Return the int hashed representation of our position.
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
