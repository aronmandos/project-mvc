package Framework.GameModules.TicTacToe;

import Framework.GameManager;
import Framework.HelperClasses.Board2d;
import Framework.HelperClasses.GameState;
import Framework.HelperClasses.Move;
import Framework.Model.GameModel;

import java.util.ArrayList;

public class TicTacToeModel extends GameModel {

    TicTacToeState state;

    public TicTacToeModel(GameManager gameManager){
        super(gameManager);
        state = new TicTacToeState(new Board2d(3, 3), 0);
    }

    public Move[] CalculateValidMoves(int player) {
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

    public boolean isValidMove(int x, int y, int player) {
        return state.isValidMove(x,y,player);
    }

}
