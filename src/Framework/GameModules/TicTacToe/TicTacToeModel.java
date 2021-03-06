package Framework.GameModules.TicTacToe;

import Framework.GameManager;
import Framework.HelperClasses.GameState;
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
    }

    public void startGame(int playerOnTurn) {
        state = new TicTacToeState(new Board2d(3, 3), playerOnTurn);
        notifyViews();
    }

    @Override
    public GameState getGameState() {
        return state;
    }

    /**
     * acts on a move received from the server (both moves from player as opponent.)
     * @param player
     * @param move
     */
    @Override
    public void implementMove(int player, String move) {
        //TODO change state
        state.getBoard().setPlayerAtPosition(1, 1, player);
        /*if (player == 1) {
            state.setPlayerOnTurn(2);
            System.out.println("Doet 'ie het?");
        } else if (player == 2) {
            state.setPlayerOnTurn(1);
        }*/
        notifyViews();
    }

    /**
     * Does a move (sends a move from player to the server.)
     *
     * @param x
     * @param y
     */
    @Override
    public void didMove(int x, int y, int player) {
        //check for valid move
        if (state.isValidMove(x, y, player)) {
            gameManager.sendMove(x, y);
            notifyViews();
        }
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
