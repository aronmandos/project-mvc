package Framework.View;

import Framework.Controller.MenuController;
import Framework.Model.MenuModel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The main view of the client.
 */
public class MenuView extends View<MenuController, MenuModel>{

    private static final String name = "menu";
    private Label connectionLabel;
    private Stage dialog = new Stage();

    public MenuView(MenuController controller, MenuModel model){
        super(controller, model);
        System.out.println("creating view: " + name);

        //dialog.initOwner();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.showAndWait();

        connectionLabel = new Label("no connection");
        Label serverHostLabel = new Label("Server host");
        TextField serverHostField = new TextField("localhost");
        Label serverPortLabel = new Label("Server port");
        TextField serverPortField = new TextField("7789");
        Button connectBtn = new Button("Connect");
        Button disconnectBtn = new Button("Disconnect");

        Label loginLabel = new Label("Login");
        TextField loginField = new TextField("Sinterklaas");
        Button loginBtn = new Button("Login");

        Label challengeLabel = new Label("Challenge");
        TextField challengeField = new TextField("Sinterklaas"); // TODO : Vervang dit door een select van de playerlist.
        Button challengeBtn = new Button("Challenge");

        Label testLabel = new Label("Server test");
        TextField testField = new TextField("help login");
        Button testBtn = new Button("Send test string");

        Button gameBtn = new Button("Go to game");
        Button tttBtn = new Button("Play Tic-tac-toe");
        Button reversiBtn = new Button("Play Reversi");

        // TODO : pop-up toevoegen waarmee de user kan kiezen of hij een uitdaging accepteert of niet.

        // TODO : Wouter gamelist en playerlist.

        connectBtn.setOnAction(e ->{
            controller.Connect(serverHostField.getText(), Integer.valueOf(serverPortField.getText()));
        });

        disconnectBtn.setOnAction(e ->{
            controller.Disconnect();
        });

        testBtn.setOnAction( e->{
            controller.SendToServer(testField.getText());
        });

        loginBtn.setOnAction( e->{
            controller.loginToServer(loginField.getText());
        });

        challengeBtn.setOnAction( e->{
            controller.sendChallenge(challengeField.getText(), "Tic-tac-toe");
        });

        gameBtn.setOnAction( e->{
            controller.goToGame("");
        });

        tttBtn.setOnAction( e->{
            controller.goToGame("ttt");
        });

        reversiBtn.setOnAction( e->{
            controller.goToGame("reversi");
        });

        this.getChildren().addAll(
                connectionLabel, serverHostLabel, serverHostField, serverPortLabel, serverPortField, connectBtn,
                disconnectBtn, testLabel, testField, testBtn, loginField, loginBtn, challengeBtn, gameBtn, tttBtn, reversiBtn
        );
        model.addView(this);
        this.setPrefSize(500, 500);
    }

    /**
     * Returns the name of this view.
     * @return The name of this view
     */
    public String getName() {
        return name;
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
