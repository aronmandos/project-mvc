package Framework.HelperClasses.board2d;

public class Board2d {

    private int[][] positions;
    private int rows;
    private int columns;

    public Board2d(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.positions = new int[rows][columns];

    }

    public void clearBoard() {
        this.positions = new int[this.rows][this.columns];
    }

    /**
     * Whether the given position is whitin the board.
     * @param x X coordinate
     * @param y Y coordinate
     * @return Whether the given position is whitin the board.
     */
    public boolean positionInBounds(int x, int y) {
        if (x < columns && x >= 0 && y < rows && y >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the player at tha given position.
     * @param x X coordinate
     * @param y Y coordinate
     * @return The player at tha given position.
     */
    public int getPlayerAtPosition(int x, int y){
        return positions[x][y];
    }

    /**
     * Sets the given player at tha given position.
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void setPlayerAtPosition(int x, int y, int playerId){
        positions[x][y] = playerId;
    }

    /**
     * Counts the amount of locations on this board are held by the given player.
     * @param playerId The given ID number of a player
     * @return The amount of locations held by the given player.
     */
    public int amountOfPositionsHeldByPlayer(int playerId){
        int amount = 0;
        for(int x = 0; x < columns; x++){
            for(int y = 0; y < rows; y++){
                if(positions[x][y] == playerId){
                    amount++;
                }
            }
        }
        return amount;
    }

    /**
     * converts XY coordinates to a single number coordinate
     * @param x X coordinate
     * @param y Y coordinate
     * @return number coordinate
     */
    public int coordinatesToInt (int x, int y) {
        return y * columns + x;
    }

    /**
     * Returns the amount of rows of this board.
     * @return The amount of rows of this board.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns the amount of columns of this board.
     * @return The amount of columns of this board.
     */
    public int getColumns() {
        return columns;
    }

    /**
     * Returns all positions of this board.
     * @return The positions of this board.
     */
    public int[][] getPositions() {
        return positions;
    }

}
