package Framework.View;

import Framework.Controller.MenuController;
import Framework.Enums.ScreenTypes;
import Framework.Model.MenuModel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The main view of the client.
 */
public class MenuView extends View<MenuController, MenuModel>{

    private static final String name = "menu";
    private Label connectionLabel;

    public MenuView(MenuController controller, MenuModel model){
        super(controller, model, ScreenTypes.MENU);
        System.out.println("creating view: " + name);

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

        Button gameBtn = new Button("Go to game");
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
                disconnectBtn, testLabel, testField, testBtn, gameBtn, tttBtn, reversiBtn
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
