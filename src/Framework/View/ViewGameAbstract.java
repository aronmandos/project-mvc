package Framework.View;

import Framework.Controller.ControllerGameAbstract;
//import Framework.Controller.ControllerReversi;
import Framework.Model.ModelClient;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ViewGameAbstract extends View<ControllerGameAbstract, ModelClient> {

    protected int[][] board = controller.getBoard();
    private int COLUMNS = board[0].length;
    private int ROWS = board.length;
    protected Object[][] objArray = new Object[8][8];
    protected boolean yourTurn = false;


    public ViewGameAbstract(ControllerGameAbstract controller, ModelClient model) {
        super(controller, model);
        this.getChildren().addAll(createBoard(COLUMNS, ROWS));
    }

    @Override
    public void updateView() {
        this.board = controller.getBoard();
        squareUpdate();
        yourTurn = true;
    }

    public Parent createBoard(int cols, int rows) {
        Pane root = new Pane();
        root.setPrefSize(cols * 80, rows * 80);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Square square = new Square(i, j);
                objArray[i][j] = square;
                System.out.print(board[i][j]);
                square.setTranslateX(i * 80);
                square.setTranslateY(j * 80);
                if(board[i][j] == 1) {square.drawNought();}
                if(board[i][j] == 2) {square.drawCross();}

                root.getChildren().add(square);
            }
            System.out.println();
        }
        return root;
    }

    public void squareUpdate() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Square square = (Square) objArray[i][j];
                if ((board[i][j]==1)) {
                    square.drawNought();
                }
                if ((board[i][j]==2)) {
                    square.drawCross();
                }
            }
        }
    }

    public class Square extends StackPane {

        Text text = new Text();
        int x;
        int y;

        public Square(int x, int y) {

            this.x = x;
            this.y = y;


            Rectangle square = new Rectangle(80, 80);
            square.setFill(Color.WHITE);
            square.setStroke(Color.BLACK);

            square.setOnMouseClicked(event -> {
                if(event.getButton() == MouseButton.PRIMARY) {
                    controller.setPlayer(x, y, 1);
                    System.out.println(" "+ x + y);
                }

                if(event.getButton() == MouseButton.SECONDARY) {
                    controller.setPlayer(x, y, 2);
                    System.out.println(" "+ x +y);
                }

            });

            getChildren().addAll(square, text);
        }

        public void drawNought() { text.setText("O"); }
        public void drawCross() { text.setText("X"); }
    }
}
