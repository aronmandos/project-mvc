package Framework.HelperClasses;

import Framework.HelperClasses.board2d.Board2d;

/**
 * A state that a game is in.
 */
public abstract class GameState {

    Board2d board;
    int playerOnTurn;

    public GameState(Board2d board, int playerOnTurn) {
        this.board = board;
        this.playerOnTurn = playerOnTurn;
    }

    /**
     * Returns the board of this state.
     * @return The board of this state.
     */
    public Board2d getBoard() {
        return board;
    }

    /**
     * Returns the player on turn during this state.
     * @return The player on turn during this state.
     */
    public int getPlayerOnTurn() {
        return playerOnTurn;
    }

    /**
     * Returns the valid moves that follow this state.
     * @return The valid moves that follow this state.
     */
    public abstract Move[] getValidMoves();
}
