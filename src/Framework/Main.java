package Framework;

import Framework.Controller.MenuController;
import Framework.Model.MenuModel;
import Framework.View.MenuView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	/**
	 * Starts the application
	 * @param primaryStage
	 * @throws Exception
	 */
	@Override
	public void start(Stage primaryStage) {
		StageManager stageManager = new StageManager(primaryStage);
		GameManager gameManager = new GameManager(stageManager);

		//menu
		MenuModel menuModel = new MenuModel(stageManager, gameManager);
		MenuController menuController = new MenuController(menuModel);
		System.out.println("menu views");
		MenuView menuView = new MenuView(menuController, menuModel);
		System.out.println("created view: " + menuView.getName());
		stageManager.addView(menuView);

		//TTT
		/*GameModel gameModel = new GameModel();
		GameController gameController = new GameController(gameModel, stageManager);
		GameView gameView = new GameView(gameController, gameModel);
		stageManager.addView(gameView);*/

		stageManager.showScene("menu");
		primaryStage.show();

	}

	/**
	 * Cleans up after application closes.
	 */
	@Override
	public void stop(){
		System.out.println("Stage is closing");
		System.exit(0);
	}
}
