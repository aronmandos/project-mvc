package Framework.Model;

import Framework.GameManager;

public class GameModel extends Model {

    private GameManager gameManager;

    public GameModel(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void goToMenu(){
        gameManager.openMenu();
    }

}
