package Framework.View;

import Framework.Controller.GameController;
import Framework.Enums.ScreenTypes;
import Framework.Model.GameModel;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public abstract class GameView extends View<GameController, GameModel>{



    private static final String name = "game";

    public GameView(GameController controller, GameModel model) {
        super(controller, model, ScreenTypes.GAME);
        Button button = new Button("Klik hier om naar het menu te gaan");
        this.getChildren().add(button);

        button.setOnAction(e -> {
            controller.goToMenu();
        });


        model.addView(this);
    }
}

