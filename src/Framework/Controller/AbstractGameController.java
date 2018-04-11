package Framework.Controller;

import Framework.Model.MenuModel;
import Framework.StageManager;

public class AbstractGameController extends Controller<MenuModel> {
    public int[][] getBoard(){
        return model.getCurrentGame().getBoard();
    }

    public AbstractGameController(MenuModel model) {
        super(model);
    }

    public void setPlayer(int x, int y, int player){
        model.setPlayer(x, y, player);
//        r.printBoard(1);
    }

//    public void backButton (Action event){
//        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        ControllerReversi reversiController= new ControllerReversi(model);
//        ViewReversi reversiView = new ViewReversi(reversiController, model);
//        Scene s=new Scene(reversiView);
//        stage.setScene(s);
//        stage.show();
//    }


}
