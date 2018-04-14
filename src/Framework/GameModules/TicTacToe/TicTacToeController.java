package Framework.GameModules.TicTacToe;

import Framework.Controller.GameController;

public class TicTacToeController extends GameController<TicTacToeModel> {
    public TicTacToeController(TicTacToeModel model) {
        super(model);
    }

    /**
     * Handles a mouseclick.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param primary primary mouse button or secondary
     */
    public void handleSquareClick(int x, int y, boolean primary){
        //TODO hanlde
        System.out.println(" "+ x + y + primary);
    }
}
