package Framework.Controller;

import Framework.Model.GameModel;

public abstract class GameController<M extends GameModel>extends Controller<GameModel>{

    public GameController(M model) {
        super(model);
    }

    /**
     * go back to the menu
     */
    public void goToMenu(){
        model.goToMenu();
    }

    /**
     * start the game.
     * @param game
     */
    public void startGame(String game) {
        //TODO initialize game.
    }

    public abstract void handleSquareClick(int x, int y, int player, boolean primary);
    public abstract void handleMove(String player, String details, String move);
}

