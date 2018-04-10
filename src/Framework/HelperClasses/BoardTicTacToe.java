package Framework.HelperClasses;

import java.util.ArrayList;

public class BoardTicTacToe extends BoardAbstract {
    protected static final int ROWS = 3;
    protected static final int COLUMNS = 3;
    protected static final int PLAYER1 = 1;
    protected static final int PLAYER2 = 2;
    protected static final int EMPTY = 0;


    public BoardTicTacToe() {
        super(ROWS, COLUMNS);
    }

    public void AIGive() {

    }
}

