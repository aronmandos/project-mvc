package Framework.Model;

import Framework.GameManager;
import Framework.HelperClasses.*;
import Framework.StageManager;

/**
 * The main model for the application.
 */
public class MenuModel extends Model {
    private String host = "localhost";
    private int port = 7789;
    private AbstractBoard currentGame;
    private Server server;

    private StageManager stageManager;
    private GameManager gameManager;

    /**
     * The main model for the application.
     */
    public MenuModel(StageManager stageManager, GameManager gameManager) {
        this.stageManager = stageManager;
        this.gameManager = gameManager;

        server = new Server(host, port);
        notifyViews();
    }

    public void changeView(String name){
        this.stageManager.showScene(name);
    }

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
        server = new Server(host, port);
        server.connect();
        notifyViews();
    }

    /**
     * Disconnects from the server.
     */
    public void disconnectServer() {
        server.disconnect();
        notifyViews();
    }

    /**
     * returns the host and port of the currently connected server.
     * The format is 0.0.0.0:1234
     * @return The current server connection
     */
    public String getCurrentConnection() {
        if (server.isConnected()) {
            return server.getHost() + ":" + server.getPort();
        } else {
            return null;
        }

    }

    /**
     * Sends a message to the server.
     * @param input A message to send to the server.
     */
    public void sendToServer(String input) {
        server.send(input);
        notifyViews();
    }
}
