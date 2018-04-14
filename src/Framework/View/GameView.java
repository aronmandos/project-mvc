package Framework.View;

import Framework.Controller.GameController;
import Framework.Model.GameModel;
import javafx.scene.control.Button;

public abstract class GameView<C extends GameController, M extends GameModel> extends View<C, M>{



    private static final String name = "game";

    public GameView(C controller, M model) {
        super(controller, model);
        Button button = new Button("Klik hier om naar het menu te gaan");
        this.getChildren().add(button);

        button.setOnAction(e -> {
            controller.goToMenu();
        });


        model.addView(this);
    }
}

