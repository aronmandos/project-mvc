package Framework;

import Framework.Enums.ScreenTypes;
import Framework.View.View;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class StageManager {
    private Stage stage;
    private HashMap<String, Scene> scenes = new HashMap<>();

    public StageManager(Stage primaryStage){
        this.stage = primaryStage;
        stage.setTitle("Game Client");
    }

    public void addView(View view){
        if (scenes.containsKey(view.getName())) {
            System.out.println("view already exists: " + view.getName());
            return;
        }
        scenes.put(view.getName(), new Scene(view));
    }

    public void showScene(String name){
        stage.setScene(this.getScene(name));
    }

    public Scene getScene(String name) {
        return scenes.get(name);
    }
}
