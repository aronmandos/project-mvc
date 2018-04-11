package Framework;

import Framework.Controller.GameController;
import Framework.GameModules.AbstractGameModule;
import Framework.GameModules.TicTacToe.TicTacToeModule;
import Framework.Model.GameModel;
import Framework.View.GameView;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class GameManager {

    private HashMap<String, AbstractGameModule> gameList;
    private GameModel model;
    private GameController controller;
    private GameView view;
    public StageManager stageManager;

    public GameManager(StageManager stageManager) {
        this.stageManager = stageManager;
        gameList = new HashMap<>();
        buildGameList();
    }

    public boolean loadGame(String gameName) {
        AbstractGameModule module = gameList.get(gameName);

        if (module == null) {
            System.out.println("no such game: " + gameName);
            return false;
        } else {
            this.model = module.getModel();
            this.controller = module.getController();
            this.view = module.getView();

            this.stageManager.addView(this.view);
            //loaded game


            return true;
        }
    }

    public void unloadGame() {
        //TODO unload view (should currently block new samename views)
        //TODO unload a game and dispose of loaded resources safely (and not hog memory)
    }

    public void openGame(String name) {
        if (!gameList.containsKey(name)) {
            System.out.println("no such game loaded: " + name);
            return;
        }
        //NOTE if you add a generated ID to name and keep a list of open games, then multiple simultanious should be easy.
        this.stageManager.showScene(name);
    }

    public void openMenu() {
        this.stageManager.showScene("menu");
    }

    private void buildGameList(){
        //NOTE could be done by checking files (so games can be added postcompile)

        gameList.put("ttt", new TicTacToeModule(this));


        /*AbstractGameModule mod1 = gameList.get("ttt");
        try {
            GameModel x = mod1.modelClass.getConstructor().newInstance();
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }*/
    }
}
