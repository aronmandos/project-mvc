package Framework.GameModules.TicTacToe;

import Framework.HelperClasses.Board2d;
import Framework.HelperClasses.GameState;
import Framework.HelperClasses.Move;

import java.util.ArrayList;

public class TicTacToeState extends GameState {

    public TicTacToeState(Board2d board, int playerOnTurn){
        super(board, playerOnTurn);

    }

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

        return 0;
    }

    private boolean samePlayer(int x1, int y1, int x2, int y2, int x3, int y3) {
        return pap(x1,y1) == pap(x2,y2) && pap(x1,y1) == pap(x3,x3);
    }

    private int pap(int x, int y) {
        return this.getBoard().getPlayerAtPosition(x, y);
    }
}
