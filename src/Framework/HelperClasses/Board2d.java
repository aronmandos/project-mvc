package Framework.HelperClasses;

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

    public boolean positionInBounds(int x, int y) {
        if (x < columns && x >= 0 && y < rows && y >= 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getPlayerAtPosition(int x, int y){
        return positions[x][y];
    }

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

    public int coordinatesToInt (int x, int y) {
        return y * columns + x;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int[][] getPositions() {
        return positions;
    }

    /*private void init() {
        for(int i=0; i<rows; i++) {
            int[] row = positions[i];
            for(int j=0; j<columns; j++) {
            }
        }
    }*/

}
