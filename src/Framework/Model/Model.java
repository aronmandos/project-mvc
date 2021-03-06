package Framework.Model;

import Framework.View.View;

import java.util.ArrayList;

abstract public class Model {

    private ArrayList<View> views=new ArrayList<View>();

    /**
     * adds a view for notification.
     * @param view
     */
    public void addView(View view) {
        views.add(view);
    }

    /**
     * Notifies the added views.
     */
    public void notifyViews() {
        for(View v: views) v.updateView();
    }
}