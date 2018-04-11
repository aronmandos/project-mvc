package Framework.View;

import Framework.Controller.Controller;
import Framework.Enums.ScreenTypes;
import Framework.Model.Model;
import javafx.scene.layout.VBox;

abstract public class View<C extends Controller, M extends Model> extends VBox {

    private ScreenTypes screenType;
    protected C controller;
    protected M model;

    abstract public void updateView();

    public View(C controller, M model, ScreenTypes screenType) {

        this.controller = controller;
        this.model = model;
        this.screenType = screenType;
    }

    public ScreenTypes getScreenType() {
        return screenType;
    }

    /**
     * Returns the name of this view.
     * @return The name of this view
     */
    public abstract String getName();

}
