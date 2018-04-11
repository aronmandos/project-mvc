package Framework.Controller;

import Framework.Model.ModelClient;
//import Framework.View.ViewReversi;
import Framework.View.ViewGameAbstract;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerClient extends Controller<ModelClient> {

    public ControllerClient(ModelClient model) {
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

    public void tttView(ActionEvent event){
        model.newTicTacToe();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ControllerGameAbstract TTTcontroller = new ControllerGameAbstract(model);
        ViewGameAbstract TTTview = new ViewGameAbstract(TTTcontroller, model);
        Scene s=new Scene(TTTview);
        model.addView(TTTview);
        stage.setScene(s);
        stage.show();
        stage.setOnHiding( e -> {System.out.println("Closing Stage");} );
        }

    public void reversiView(ActionEvent event){
        model.newReversi();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        ControllerGameAbstract reversiController= new ControllerGameAbstract(model);
        ViewGameAbstract reversiView = new ViewGameAbstract(reversiController, model);
        Scene s=new Scene(reversiView);
        model.addView(reversiView);
        stage.setScene(s);
        stage.show();
    }


}
