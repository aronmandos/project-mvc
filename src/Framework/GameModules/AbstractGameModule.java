package Framework.GameModules;

import Framework.Controller.GameController;
import Framework.Model.GameModel;
import Framework.View.GameView;

/**
 * A module that contains a game for the framework.
 */
public abstract class AbstractGameModule {

    /**
     * Returns the model of this module.
     * @return The model of this module.
     */
    public abstract GameModel getModel();

    /**
     * Returns the controller of this module.
     * @return The controller of this module.
     */
    public abstract GameController getController();

    /**
     * Returns the view of this module.
     * @return The view of this module.
     */
    public abstract GameView getView();



}
