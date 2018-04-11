package Framework.GameModules;

import Framework.Controller.Controller;
import Framework.Controller.GameController;
import Framework.GameModules.TicTacToe.TicTacToeController;
import Framework.GameModules.TicTacToe.TicTacToeModel;
import Framework.GameModules.TicTacToe.TicTacToeView;
import Framework.Model.GameModel;
import Framework.Model.Model;
import Framework.View.GameView;
import Framework.View.View;

public abstract class AbstractGameModule {



    public Class<GameModel> modelClass;
    public Class<GameController> controllerClass;
    public Class<GameView> viewClass;

    public abstract GameModel getModel();
    public abstract GameController getController();
    public abstract GameView getView();



}
