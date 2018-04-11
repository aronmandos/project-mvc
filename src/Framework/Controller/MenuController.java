package Framework.Controller;

import Framework.Model.MenuModel;
//import Framework.View.ViewReversi;
import Framework.StageManager;

public class MenuController extends Controller<MenuModel> {

    public MenuController(MenuModel model) {
        super(model);
    }

    public void Connect(String host, int port){ model.connectServer(host, port); }

    public void SendToServer(String input){
        model.sendToServer(input);
    }

    public void sendMove(int move){
        model.sendToServer("move " + move);
    }

    public void Disconnect() { model.disconnectServer();}


    public void goToGame(String game) {
        this.model.openGame(game);
    }

/*    public void tttView(ActionEvent event){
        model.newTicTacToe();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        AbstractGameController TTTcontroller = new AbstractGameController(model);
        AbstractGameView TTTview = new AbstractGameView(TTTcontroller, model);
        Scene s=new Scene(TTTview);
        model.addView(TTTview);
        stage.setScene(s);
        stage.show();
        stage.setOnHiding( e -> {System.out.println("Closing Stage");} );
        }

    public void reversiView(ActionEvent event){
        model.newReversi();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        AbstractGameController reversiController= new AbstractGameController(model);
        AbstractGameView reversiView = new AbstractGameView(reversiController, model);
        Scene s=new Scene(reversiView);
        model.addView(reversiView);
        stage.setScene(s);
        stage.show();
    }*/


}
