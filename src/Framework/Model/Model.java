package Framework.Model;

import Framework.View.View;

import java.util.ArrayList;

abstract public class Model {

    private ArrayList<View> views=new ArrayList<View>();

    public void addView(View view) {
        views.add(view);
    }

    public void notifyViews() {
        for(View v: views) v.updateView();
    }
}