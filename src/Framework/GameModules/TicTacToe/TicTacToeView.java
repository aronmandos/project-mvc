package Framework.GameModules.TicTacToe;

import Framework.HelperClasses.Board2d;
import Framework.HelperClasses.Board2dPane;
import Framework.View.GameView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TicTacToeView extends GameView {

    private static final String name = "ttt";
    private Board2dPane board2dPane;

    public TicTacToeView(TicTacToeController controller, TicTacToeModel model) {
        super(controller, model);

        Label label = new Label("TicTacToe");
        Board2d x = model.state.getBoard();
        System.out.println("tttview: " + x.getColumns() );
        board2dPane = new Board2dPane(model.state.getBoard());
        board2dPane.addListener(this);
        this.getChildren().addAll(label, board2dPane.createElement());
    }

    @Override
    public void updateView() {
        board2dPane.updateSquares();
    }

    @Override
    public String getName() {
        return name;
    }
}
