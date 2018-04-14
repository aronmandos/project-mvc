package Framework.Controller;

import Framework.Model.MenuModel;

public class MenuController extends Controller<MenuModel> {

    public MenuController(MenuModel model) {
        super(model);
    }

    /**
     * Connect to the given server
     * @param host host of the server
     * @param port port to connect to
     */
    public void Connect(String host, int port){ model.connectServer(host, port); }

    /**
     * Send input to connected server
     * @param input
     */
    public void SendToServer(String input){
        model.sendToServer(input);
    }

    /**
     * send a move to the connected server
     * @param move
     */
    public void sendMove(int move){
        model.sendToServer("move " + move);
    }

    /**
     * Disconnect from a server.
     */
    public void Disconnect() { model.disconnectServer();}

    /**
     * open a game
     * @param game The name of the game.
     */
    public void goToGame(String game) {
        this.model.openGame(game);
    }

    public void loginToServer(String playerName) {
        model.sendToServer("login " + playerName);
        model.setPlayerName(playerName);
    }

    public void sendChallenge(String opponentName, String gameType) {
        model.sendToServer("challenge \"" + opponentName + "\" \"" + gameType + "\"");
    }
}
