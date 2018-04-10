package Framework.Controller;

import Framework.Model.Model;

abstract public class Controller<M extends Model> {

    protected M model;

    public Controller(M model) {
        this.model = model;
    }
}
