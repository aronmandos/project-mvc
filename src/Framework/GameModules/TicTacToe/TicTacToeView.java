package Framework.GameModules.TicTacToe;

import Framework.HelperClasses.board2d.Board2d;
import Framework.HelperClasses.board2d.Board2dListener;
import Framework.HelperClasses.board2d.Board2dPane;
import Framework.View.GameView;
import javafx.scene.control.Label;

/**
 * The GUI for a game of Tic Tac Toe.
 */
public class TicTacToeView extends GameView<TicTacToeController, TicTacToeModel> implements Board2dListener {

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

    /**
     * Updates this view
     */
    @Override
    public void updateView() {
        board2dPane.updateSquares();
    }

    /**
     * Returns the name of this view.
     * @return The name of this view.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Handles a mouseclick.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param primary primary mouse button or secondary
     */
    @Override
    public void squareWasClicked(int x, int y, boolean primary) {
        controller.handleSquareClick(x, y, primary);
    }
}
