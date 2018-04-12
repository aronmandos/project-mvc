import Framework.HelperClasses.Board2d;
import Framework.HelperClasses.GameState;
import Framework.HelperClasses.Move;

public class ReversiState extends GameState {
    public ReversiState(Board2d board, int playerOnTurn) {
        super(board, playerOnTurn);
    }

    @Override
    public Move[] getValidMoves() {
        return new Move[0];
    }
}
