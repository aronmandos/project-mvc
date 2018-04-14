package Framework.GameModules.TicTacToe;

import Framework.HelperClasses.board2d.Board2d;
import Framework.HelperClasses.GameState;
import Framework.HelperClasses.Move;

import java.util.ArrayList;

/**
 * A state of a Tic Tac Toe game.
 */
public class TicTacToeState extends GameState {

    public TicTacToeState(Board2d board, int playerOnTurn){
        super(board, playerOnTurn);
    }

    /**
     * Returns the valid moves that can follow this state.
     * @return The valid moves that can follow this state.
     */
    public Move[] getValidMoves() {
        ArrayList<Move> moves = new ArrayList<Move>();
        for (int x = 0; x < this.getBoard().getColumns(); x++) {
            for (int y = 0; y < this.getBoard().getRows(); y++) {
                if(this.isValidMove(x, y, this.getPlayerOnTurn())) {
                    moves.add(new Move(x, y));
                }
            }
        }
        return moves.toArray(new Move[0]);
    }

    /**
     * Checks whether a certain move is valid to do after this state.
     * @param x X coordinate
     * @param y Y coordinate
     * @param player The player to make the move
     * @return Whether the move is valid
     */
    public boolean isValidMove(int x, int y, int player) {
        int positionStatus = this.getBoard().getPlayerAtPosition(x, y);

        if(this.getPlayerOnTurn() != player) {
            return false;
        }
        if (positionStatus != 0) {
            return false;
        }

        return true;
    }

    /**
     * Is this state an end state
     * #==0 -> no end yet.
     * #>0 -> player # has won
     * #<0 -> this is a draw
     *
     * @return the type of state this is.
     */
    public int isWinState(){

        if (pap(0,0) > 0 ) {
            if (samePlayer(0,0,1,0,2,0) ||
                    samePlayer(0,0,0,1,0,2)) {
                return pap(0,0);
            }
        }

        if (pap(2,2) > 0 ) {
            if (samePlayer(2,0,2,1,2,2) ||
                    samePlayer(0,2,1,2,2,2)) {
                return pap(0,0);
            }
        }

        if (pap(1,1) > 0 ) {
            if (samePlayer(0,0,1,1,2,2) ||
                    samePlayer(0,2,1,1,2,0) ||
                    samePlayer(0,1,1,1,2,1) ||
                    samePlayer(1,0,1,1,1,2)) {
                return pap(1,1);
            }
        }

        //TODO check for draw

        return 0; //TODO replace int with enum
    }

    /**
     * whether a player has the given 3 locations.
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     * @return
     */
    private boolean samePlayer(int x1, int y1, int x2, int y2, int x3, int y3) {
        return pap(x1,y1) == pap(x2,y2) && pap(x1,y1) == pap(x3,x3);
    }

    /**
     * Returns the player at the given location.
     *
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @return player id
     */
    private int pap(int x, int y) {
        return this.getBoard().getPlayerAtPosition(x, y);
    }
}
