package Framework.Controller;

import Framework.Model.ModelClient;

public class ControllerGameAbstract extends Controller<ModelClient> {
    public int[][] getBoard(){
        return model.getCurrentGame().getBoard();
    }

    public ControllerGameAbstract(ModelClient model) {
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
