package Framework.Controller;

import Framework.Model.GameModel;
import Framework.StageManager;

public class GameController extends Controller<GameModel>{

    public GameController(GameModel model) {
        super(model);
    }

    public void goToMenu(){
        model.goToMenu();
    }

    public void startGame(String game) {

    }

}

