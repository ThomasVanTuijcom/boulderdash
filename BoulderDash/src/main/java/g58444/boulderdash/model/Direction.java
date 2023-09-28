package g58444.boulderdash.model;

/**
 * This enumeration represents all the directions where we could travel inside our game. It is composed with a delta row
 * and column. These values represent the values of the different ways to step to a next element.
 */
public enum Direction {
    N(-1, 0),
    S(1, 0),
    E(0, 1),
    W(0, -1);

    private int deltaRow;
    private int deltaColumn;

    /**
     * The constructor of our enumeration values. It needs 2 arguments that are the shift values. These arguments will
     * be assigned to private attributes.
     *
     * @param deltaRow    The shift on the Y axis.
     * @param deltaColumn The shift on the X axis.
     */
    Direction(int deltaRow, int deltaColumn) {
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;
    }

    /**
     * Getter of the deltaRow.
     *
     * @return Returns an int value of the deltaRow
     */
    public int getDeltaRow() {
        return deltaRow;
    }

    /**
     * Getter of the deltaColumn.
     *
     * @return Returns an int value of the deltaColumn
     */
    public int getDeltaColumn() {
        return deltaColumn;
    }
}
