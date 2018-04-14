package Framework;

import Framework.Controller.GameController;
import Framework.GameModules.AbstractGameModule;
import Framework.GameModules.TicTacToe.TicTacToeModule;
import Framework.HelperClasses.CommandHandlerListener;
import Framework.Model.GameModel;
import Framework.View.GameView;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class GameManager implements CommandHandlerListener {

    private HashMap<String, AbstractGameModule> gameList;
    private GameModel model;
    private GameController controller;
    private GameView view;
    private StageManager stageManager;
    private ServerManager serverManager;

    public GameManager(StageManager stageManager, ServerManager serverManager) {
        this.stageManager = stageManager;
        this.serverManager = serverManager;
        gameList = new HashMap<>();

        this.serverManager.addCommandHandlerListener(this);
        buildGameList();
    }

    public boolean loadGame(String gameName) {
        AbstractGameModule module = gameList.get(gameName);

        System.out.println("loading module: " + gameName);
        if (module == null) {
            System.out.println("no such game: " + gameName);
            return false;
        } else {
            this.model = module.getModel();
            this.controller = module.getController();
            this.view = module.getView();

            this.stageManager.addView(this.view);
            //loaded game
            System.out.println("loaded module: " + gameName + this.model + this.controller + this.view);

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

    @Override
    public void receiveChallenge(String challenger, String challengeNumber, String gameType) {
        //do nothing
    }

    @Override
    public void recieveMove(String player, String move, String details) {
        //TODO handle move
        //TODO translate data to Move object!
        System.out.println("Move recieved: "+ player +"|"+ move + "|"+ details + " "+ this.controller);
        this.controller.handleMove(player, move, details);
    }

    @Override
    public void receiveMatchStart(String playerToMove, String gameType, String opponent) {
        // this.loadGame(gameType); TODO hou zelfde namen aan of vertaal gamenaam
        this.loadGame("ttt");
        this.startGame(Integer.parseInt(playerToMove));
    }

    private void startGame(int playerToMove) {
        this.model.startGame(playerToMove);
    }


}
