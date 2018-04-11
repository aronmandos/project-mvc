package Framework.Model;

import Framework.HelperClasses.*;

import java.net.Socket;

/**
 * The main model for the application.
 */
public class ModelClient extends Model {
    private String host = "localhost";
    private int port = 7789;
    private BoardAbstract currentGame;
    private Server server;

    /**
     * The main model for the application.
     */
    public ModelClient() {
        server = new Server(host, port);
        notifyViews();
    }

    /**
     * Starts a new game of Tic Tac Toe.
     */
    public void newTicTacToe() {
        currentGame = new BoardTicTacToe();
    }

    /**
     * Starts a new game of Reversi.
     */
    public void newReversi(){
        currentGame = new BoardReversi();
    }

    /**
     * Returns the currently active game.
     * @return
     */
    public BoardAbstract getCurrentGame() {
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
