package Framework.HelperClasses;

import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Board2dPane  {

    private Board2d board;
    private Square[][] squares;

    public Board2dPane(Board2d board) {
        this.board = board;
        this.squares = new Square[this.board.getColumns()][this.board.getRows()];
        System.out.println("test1: ");
    }

    public Parent createElement() {
        System.out.println("test2 ");
        Pane root = new Pane();
        root.setPrefSize(board.getColumns() * 80, board.getRows() * 80);
        System.out.println("test3: ");
        for (int x = 0; x < board.getColumns(); x++) {
            for (int y = 0; y < board.getRows(); y++) {
                Square square = new Square(x, y);
                squares[x][y] = square;
                System.out.println("test6");
                square.setTranslateX(x * 80);
                square.setTranslateY(y * 80);
                root.getChildren().add(square);
            }
        }
        System.out.println("test4 ");
        return root;
    }

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

    private void squareClick(int x, int y, boolean primary){
        System.out.println(" "+ x + y + primary);
        //controller.setPlayer(x, y, 1);
    }


    public class Square extends StackPane {

        private Text text = new Text();
        private int x;
        private int y;


        public Square(int x, int y) {
            System.out.println("test5 ");
            this.x = x;
            this.y = y;


            Rectangle square = new Rectangle(80, 80);
            square.setFill(Color.WHITE);
            square.setStroke(Color.BLACK);
            System.out.println("test7 ");
            square.setOnMouseClicked(event -> {
                if(event.getButton() == MouseButton.PRIMARY) {
                    squareClick(x, y, true);
                }

                if(event.getButton() == MouseButton.SECONDARY) {
                    squareClick(x, y, false);
                }

            });
            System.out.println("test8 ");
            getChildren().addAll(square, text);
        }
        public void drawEmpty() { text.setText(" "); }
        public void drawNought() { text.setText("O"); }
        public void drawCross() { text.setText("X"); }
    }
}
