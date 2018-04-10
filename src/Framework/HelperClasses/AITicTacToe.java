package Framework.HelperClasses;

import Framework.Model.ModelClient;

import java.util.ArrayList;

import static Framework.HelperClasses.BoardTicTacToe.*;

public class AITicTacToe extends ModelClient {
    private int turn;
    private BoardTicTacToe BT;
    private int[][] board;

    public AITicTacToe(BoardTicTacToe BT, int[][] board) {
        this.BT = BT;
        this.board = board;
        turn = 1;
        AI();
        AI2();
    }

    public void AI() {
        if (turn == PLAYER1) {
            BT.setPlayer(1, 1, PLAYER1);
            turn = PLAYER2;
        } else {
            BT.setPlayer(2, 2, PLAYER2);
        }
    }

    public void AI2() {
        if (turn == PLAYER2) {
            BT.setPlayer(2, 2, PLAYER2);
        }
    }

    public ArrayList<Move> validMovesList() {
        ArrayList<Move> validMoves = new ArrayList<Move>();

        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < ROWS; y++) {
                if (board[x][y] == EMPTY) {
                    validMoves.add(new Move(x, y));
                }
            }
        }
        return validMoves;
    }
}
