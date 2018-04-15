package Framework.HelperClasses.board2d;

import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * The view element for a Board2d.
 */
public class Board2dPane {

    private Board2d board;
    private Square[][] squares;
    private ArrayList<Board2dListener> listeners;

    public Board2dPane(Board2d board) {
        this.board = board;
        this.squares = new Square[this.board.getColumns()][this.board.getRows()];
        this.listeners = new ArrayList<>();
    }

    /**
     * creates the view elements
     * @return
     */
    public Pane createElement() {
        Pane root = new Pane();
        root.setPrefSize(board.getColumns() * 80, board.getRows() * 80);
        for (int x = 0; x < board.getColumns(); x++) {
            for (int y = 0; y < board.getRows(); y++) {
                Square square = new Square(x, y);
                squares[x][y] = square;
                square.setTranslateX(x * 80);
                square.setTranslateY(y * 80);
                root.getChildren().add(square);
            }
        }
        return root;
    }

    /**
     * Updates the symbols of the squares on this board
     */
    public void updateSquares() {
        for (int x = 0; x < board.getColumns(); x++) {
            for (int y = 0; y < board.getRows(); y++) {
                Square square = squares[x][y];
                int position = board.getPlayerAtPosition(x, y);
                if ((position==0)) {
                    square.drawEmpty();
                } else if ((position==1)) {
                    square.drawNought();
                }else if ((position==2)) {
                    square.drawCross();
                }
            }
        }
    }

    /**
     * Notifies listeners that a square was clicked.
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param primary primary mouse button or secondary
     */
    private void squareClick(int x, int y, int player, boolean primary){
        for(Board2dListener listener: this.listeners){
            listener.squareWasClicked(x, y, player, primary);
        }
    }

    /**
     * Adds a listener to this board
     * @param listener A listener to be added.
     */
    public void addListener(Board2dListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Removes a listener from this board
     * @param listener A listener to be removed.
     */
    public void removeListener(Board2dListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * clears all listeners
     */
    public void clearListeners() {
        this.listeners.clear();
    }

    /**
     * A Square on the board
     */
    public class Square extends StackPane {

        private Text text = new Text();
        private int x;
        private int y;

        /**
         * Creates a square for a Board2d
         * @param x
         * @param y
         */
        public Square(int x, int y) {
            this.x = x;
            this.y = y;


            Rectangle square = new Rectangle(80, 80);
            square.setFill(Color.WHITE);
            square.setStroke(Color.BLACK);
            square.setOnMouseClicked(event -> {
                if(event.getButton() == MouseButton.PRIMARY) {
                    squareClick(x, y, 1, true);
                } else if(event.getButton() == MouseButton.SECONDARY) {
                    squareClick(x, y, 2, false);
                }
            });
            getChildren().addAll(square, text);
        }

        /**
         * Clears the text on this square.
         */
        public void drawEmpty() { text.setText(" "); }

        /**
         * Draws a circle on this square.
         */
        public void drawNought() { text.setText("O"); }

        /**
         * Draws a X on this square.
         */
        public void drawCross() { text.setText("X"); }
    }
}
