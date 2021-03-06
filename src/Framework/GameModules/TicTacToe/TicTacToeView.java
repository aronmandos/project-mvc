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
    private Board2d displayBoard;

    public TicTacToeView(TicTacToeController controller, TicTacToeModel model) {
        super(controller, model);
        displayBoard = new Board2d(3, 3); //TODO update this board when state changes

        Label label = new Label("TicTacToe");
        System.out.println("tttview: " + displayBoard.getColumns() );
        board2dPane = new Board2dPane(displayBoard);
        board2dPane.addListener(this);
        this.getChildren().addAll(label, board2dPane.createElement());
    }

    /**
     * Updates this view
     */
    @Override
    public void updateView() {
        //TODO weergave updaten werkt nog niet.
        displayBoard.updateBoardWithBoard(model.state.getBoard());
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
    public void squareWasClicked(int x, int y, int player, boolean primary) {
        controller.handleSquareClick(x, y, player, primary);
    }
}
