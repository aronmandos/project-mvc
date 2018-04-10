package Framework.Model;

import Framework.HelperClasses.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ModelClient extends Model {

    private Socket socket;
    private String host = "localhost";
    private int port = 7789;
    private BoardAbstract currentGame;
    private Client client;

    public ModelClient() {
        client = new Client();
    }

    public void newTicTacToe() {
        currentGame = new BoardTicTacToe();
    }

    public void newReversi(){
        currentGame = new BoardReversi();
    }

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

    public void sendMoveToServer (int x, int y) {
            sendToServer("move " + currentGame.coordinatesToInt(x, y));
    }

    public void establishConnection() {
        client.establishConnection();
    }

    public void sendToServer(String input) {
        client.sendToServer(input);
    }

    public void logout() {
        client.closeSocket();
    }
}
