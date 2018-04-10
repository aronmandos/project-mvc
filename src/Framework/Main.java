package Framework;

import Framework.Controller.ControllerClient;
import Framework.Model.ModelClient;
import Framework.View.ViewClient;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override


	public void start(Stage primaryStage) throws Exception {
		Central central = new Central();
		primaryStage.setTitle("Game Client");

		ModelClient model = new ModelClient();
		ControllerClient controller = new ControllerClient(model);
		ViewClient view = new ViewClient(controller, model);

		Scene s=new Scene(view);
		primaryStage.setScene(s);
		primaryStage.show();
	}
}
