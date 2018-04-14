package Framework.GameModules.TicTacToe;

import Framework.GameManager;
import Framework.HelperClasses.board2d.Board2d;
import Framework.HelperClasses.Move;
import Framework.Model.GameModel;

import java.util.ArrayList;

/**
 * A datamodel for a game of Tic Tac Toe.
 */
public class TicTacToeModel extends GameModel {

    TicTacToeState state;

    public TicTacToeModel(GameManager gameManager){
        super(gameManager);
        state = new TicTacToeState(new Board2d(3, 3), 0);
    }

    /**
     * Calculates the valid moves for the given player
     *
     * @param player The player to move.
     * @return The valid moves.
     */
    public Move[] CalculateValidMoves(int player) {
        //TODO determine whether this method is still needed. i think TicTacToeState.getValidMoves() should suffice.
        ArrayList<Move> moves = new ArrayList<Move>();
        for (int x = 0; x < state.getBoard().getColumns(); x++) {
            for (int y = 0; y < state.getBoard().getRows(); y++) {
                if(this.isValidMove(x, y, player)) {
                    moves.add(new Move(x, y));
                }
            }
        }
        return moves.toArray(new Move[0]);
    }
    /**
     * Calculates the valid moves for the current player.
     *
     * @return The valid moves.
     */
    public Move[] getValidMoves() {
        return state.getValidMoves();
    }

    /**
     * Returns whether a move is valid for the given player.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param player The player to move.
     * @return Whether a move is valid.
     */
    public boolean isValidMove(int x, int y, int player) {
        return state.isValidMove(x,y,player);
    }

}
