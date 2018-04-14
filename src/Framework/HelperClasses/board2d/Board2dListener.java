package Framework.HelperClasses.board2d;

public interface Board2dListener {

    /**
     * Handles a mouseclick.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param primary primary mouse button or secondary
     */
    void squareWasClicked(int x, int y, boolean primary);
}
