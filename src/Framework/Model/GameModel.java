package Framework.Model;

import Framework.GameManager;
import Framework.HelperClasses.CommandHandlerListener;

public abstract class GameModel extends Model {

    private GameManager gameManager;

    public GameModel(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void goToMenu(){
        gameManager.openMenu();
    }

    public abstract void startGame(int playerOnTurn);

}
