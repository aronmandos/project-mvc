package Framework.GameModules.TicTacToe;

import Framework.Controller.GameController;
import Framework.GameManager;
import Framework.GameModules.AbstractGameModule;
import Framework.Model.GameModel;
import Framework.View.GameView;

/**
 * A game module for the game Tic Tac Toe
 */
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
