package Framework.HelperClasses;

public abstract class GameState {

    Board2d board;
    int playerOnTurn;

    public GameState(Board2d board, int playerOnTurn) {
        this.board = board;
        this.playerOnTurn = playerOnTurn;
    }

    public Board2d getBoard() {
        return board;
    }

    public int getPlayerOnTurn() {
        return playerOnTurn;
    }

    public abstract Move[] getValidMoves();
}
