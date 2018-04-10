package Framework.View;

import Framework.Controller.ControllerClient;
import Framework.Model.ModelClient;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;

public class ViewClient extends View<ControllerClient, ModelClient>{


    public ViewClient(ControllerClient controller, ModelClient model){
        super(controller, model);
        Button sendBtn, reversiBtn, tttBtn, connectBtn, logoutBtn;
        connectBtn = new Button("Connect To Server");
        Label label = new Label("Placeholder label");
        TextField input = new TextField("login naam");

        sendBtn = new Button("Send String to server");
        tttBtn = new Button("Play Tic-tac-toe");
        reversiBtn = new Button("Play Reversi");
        logoutBtn = new Button("Logout");

        connectBtn.setOnAction(e ->{
            controller.Connect();
        });

        sendBtn.setOnAction( e->{
            controller.SendToServer(input.getText());
        });

        tttBtn.setOnAction( e->{
            controller.tttView(e);
        });

        reversiBtn.setOnAction( e->{
            controller.reversiView(e);
        });

        logoutBtn.setOnAction(e ->{
            controller.logout();
        });



        this.getChildren().addAll(connectBtn, label, input, sendBtn, tttBtn, reversiBtn, logoutBtn);
        model.addView(this);
        this.setPrefSize(500, 500);
    }



    @Override
    public void updateView() {

    }
}
