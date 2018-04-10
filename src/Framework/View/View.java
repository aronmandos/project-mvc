package Framework.View;

import Framework.Controller.Controller;
import Framework.Model.Model;
import javafx.scene.layout.VBox;

abstract public class View<C extends Controller, M extends Model> extends VBox {

    protected C controller;
    protected M model;

    abstract public void updateView();

    public View(C controller, M model) {
        this.controller = controller;
        this.model = model;
    }
}
