package Framework.GameModules.TicTacToe;

import Framework.Controller.Controller;
import Framework.Controller.GameController;
import Framework.Controller.MenuController;
import Framework.GameManager;
import Framework.GameModules.AbstractGameModule;
import Framework.Model.GameModel;
import Framework.Model.MenuModel;
import Framework.Model.Model;
import Framework.StageManager;
import Framework.View.GameView;
import Framework.View.MenuView;
import Framework.View.View;

import java.lang.reflect.InvocationTargetException;

public class TicTacToeModule extends AbstractGameModule {

    public Class<TicTacToeModel> modelClass = TicTacToeModel.class;
    public Class<TicTacToeController> controllerClass = TicTacToeController.class;
    public Class<TicTacToeView> viewClass = TicTacToeView.class;

    private TicTacToeModel ticTacToeModel;
    private TicTacToeController ticTacToeController;
    private TicTacToeView ticTacToeView;

    public TicTacToeModule(GameManager gameManager) {

        ticTacToeModel = new TicTacToeModel(gameManager);
        ticTacToeController = new TicTacToeController(ticTacToeModel);
        ticTacToeView = new TicTacToeView(ticTacToeController, ticTacToeModel);
    }

    @Override
    public GameModel getModel() {
        return ticTacToeModel;
    }

    @Override
    public GameController getController() {
        return ticTacToeController;
    }

    @Override
    public GameView getView() {
        return ticTacToeView;
    }
}
