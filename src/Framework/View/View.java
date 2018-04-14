package Framework.View;

import Framework.Controller.Controller;
import Framework.Model.Model;
import javafx.scene.layout.VBox;

/**
 * A View
 * @param <C> A controller to notify of actions.
 * @param <M> A model for retrieving data.
 */
abstract public class View<C extends Controller, M extends Model> extends VBox {

    protected C controller;
    protected M model;


    public View(C controller, M model) {

        this.controller = controller;
        this.model = model;
    }


    /**
     * Returns the name of this view.
     * @return The name of this view
     */
    public abstract String getName();

    /**
     * Updates this view
     */
    abstract public void updateView();
}
