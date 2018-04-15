package Framework.Model;

import Framework.GameManager;
import Framework.HelperClasses.CommandHandlerListener;
import Framework.HelperClasses.GameState;

public abstract class GameModel extends Model {

    protected GameManager gameManager;

    public GameModel(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    /**
     * opens the menu
     */
    public void goToMenu(){
        gameManager.openMenu();
        //TODO should game be stopped when returning to menu?
    }

    /**
     * Starts the game with the given player
     * @param playerOnTurn
     */
    public abstract void startGame(int playerOnTurn);

    /**
     * returns the state of the game.
     * @return The state of the game.
     */
    public abstract GameState getGameState();

    public abstract void implementMove(int i, String move);

    public abstract void didMove(int x, int y, int player);
}
