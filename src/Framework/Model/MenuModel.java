package Framework.Model;

import Framework.GameManager;
import Framework.HelperClasses.*;
import Framework.ServerManager;
import Framework.StageManager;

import java.util.ArrayList;

/**
 * The main model for the application.
 */
public class MenuModel extends Model implements CommandHandlerListener{
    private String host = "localhost";
    private int port = 7789;
    private AbstractBoard currentGame;
    private ServerManager serverManager;

    private StageManager stageManager;
    private GameManager gameManager;
    private String playerName;

    private ArrayList<Challenge> challenges;

    /**
     * The main model for the application.
     */
    public MenuModel(StageManager stageManager, ServerManager serverManager, GameManager gameManager) {
        this.stageManager = stageManager;
        this.serverManager = serverManager;
        this.gameManager = gameManager;
        this.challenges = new ArrayList<>();

        notifyViews();
    }

    public void changeView(String name){
        this.stageManager.showScene(name);
    }

    /**
     * Opens a loaded game.
     * @param name The name of the game.
     */
    public void openGame(String name) {
        if (this.gameManager.loadGame(name)) {
            this.gameManager.openGame(name);
        }
    }

    /**
     * Starts a new game of Tic Tac Toe.
     */
    public void newTicTacToe() {
        currentGame = new TicTacToeBoard();
    }

    /**
     * Starts a new game of Reversi.
     */
    public void newReversi(){
        currentGame = new ReversiBoard();
    }

    /**
     * Returns the currently active game.
     * @return
     */
    public AbstractBoard getCurrentGame() {
        return currentGame;
    }

    public void setPlayer(int x, int y, int player){
        if(currentGame.positionValid(x, y, player)) {
            sendMoveToServer(x, y);
            currentGame.setPlayer(x, y, player);
            notifyViews();
        } else {
            System.out.println("position invalid");
            currentGame.updateBoard();
        }
    }

    /**
     * Sends a move from a game to the server.
     *
     * @param x
     * @param y
     */
    public void sendMoveToServer (int x, int y) {
            sendToServer("move " + currentGame.coordinatesToInt(x, y));
    }

    /**
     * connects to the server.
     */
    public void connectServer(String host, int port) {
        CommandHandler commandHandler = new CommandHandler();
        commandHandler.addListener(this);
        serverManager.connect(host, port, commandHandler);
        notifyViews();
    }

    /**
     * Disconnects from the server.
     */
    public void disconnectServer() {
        if (serverManager == null) return;
        serverManager.disconnect();
        notifyViews();
    }

    /**
     * returns the host and port of the currently connected server.
     * The format is 0.0.0.0:1234
     * @return The current server connection
     */
    public String getCurrentConnection() {
        if (serverManager == null) return null;
        if (!serverManager.isConnected()) return null;
        return serverManager.getHost() + ":" + serverManager.getPort();


    }

    /**
     * Sends a message to the server.
     * @param input A message to send to the server.
     */
    public void sendToServer(String input) {
        if (serverManager == null) return;
        serverManager.send(input);
        notifyViews();
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    @Override
    public void receiveChallenge(String challenger, String challengeNumber, String gameType) {
        this.challenges.add(new Challenge(challenger, challengeNumber, gameType));
        System.out.println("Even kijken...");
        notifyViews();
    }

    public ArrayList<Challenge> getChallenges() {
        return challenges;
    }
}
