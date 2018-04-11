package Framework.View;

import Framework.Controller.ControllerClient;
import Framework.Model.ModelClient;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The main view of the client.
 */
public class ViewClient extends View<ControllerClient, ModelClient>{


    private Label connectionLabel;

    public ViewClient(ControllerClient controller, ModelClient model){
        super(controller, model);
        connectionLabel = new Label("no connection");
        Label serverHostLabel = new Label("Server host");
        TextField serverHostField = new TextField("localhost");
        Label serverPortLabel = new Label("Server port");
        TextField serverPortField = new TextField("7789");
        Button connectBtn = new Button("Connect");
        Button disconnectBtn = new Button("Disconnect");

        Label testLabel = new Label("Server test");
        TextField testField = new TextField("help login");
        Button testBtn = new Button("Send test string");

        Button tttBtn = new Button("Play Tic-tac-toe");
        Button reversiBtn = new Button("Play Reversi");


        connectBtn.setOnAction(e ->{
            controller.Connect(serverHostField.getText(), Integer.valueOf(serverPortField.getText()));
        });

        disconnectBtn.setOnAction(e ->{
            controller.Disconnect();
        });


        testBtn.setOnAction( e->{
            controller.SendToServer(testField.getText());
        });

        tttBtn.setOnAction( e->{
            controller.tttView(e);
        });

        reversiBtn.setOnAction( e->{
            controller.reversiView(e);
        });


        this.getChildren().addAll(
                connectionLabel, serverHostLabel, serverHostField, serverPortLabel, serverPortField, connectBtn,
                disconnectBtn, testLabel, testField, testBtn, tttBtn, reversiBtn
        );
        model.addView(this);
        this.setPrefSize(500, 500);
    }


    /**
     * Update this view
     */
    @Override
    public void updateView() {
        System.out.println("updating views");
        this.updateConnected();


    }

    /**
     * update the connection status message.
     */
    private void updateConnected() {
        if (model.getCurrentConnection() != null) {
            connectionLabel.setText("Connected to: " + model.getCurrentConnection());
        } else {
            connectionLabel.setText("no connection");
        }
    }
}
