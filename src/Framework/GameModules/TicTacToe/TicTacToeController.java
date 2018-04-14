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
    @Override
    public void handleSquareClick(int x, int y, boolean primary){
        //TODO hanlde
        System.out.println(" "+ x + y + primary);
        model.didMove(x, y);
    }


    @Override
    public void handleMove(String player, String details, String move) {
        //TODO handle move
        System.out.println("Move binnengekomen: "+ player + "|"+ details +"|"+ move);

        this.model.implementMove(1, move);
        //TODO translate move to Move object
        //TODO translate playername to int (or make Player object)
    }
}
