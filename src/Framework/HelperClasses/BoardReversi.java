package Framework.HelperClasses;

public class BoardReversi extends BoardAbstract {
    private static int p1 = 1;
    private static int p2 = 2;
    private static int row = 8;
    private static int column = 8;

    public BoardReversi() {
        super(row, column);
        initializeBoard();
        legalMoves(1);
        printBoard();
        clearLegalMoves();
    }

    public void initializeBoard() {
        board[3][3] = p1;
        board[3][4] = p2;
        board[4][4] = p1;
        board[4][3] = p2;
    }

    public void setPlayer(int x, int y, int player) {
        if (board[x][y]==3) {
            board[x][y] = player;
            moveEffects(x, y, player);
            printBoard();
            clearLegalMoves();
        } else {
            System.out.println("You can't place it here");
            printBoard();
            clearLegalMoves();
        }
    }

    @Override
    public void updateBoard() {
                clearLegalMoves();
    }

    public void moveEffects(int row, int col, int player){
        for(int k = -1; k <=1; k++) {
            for (int l = -1; l <= 1; l++) {
                if((k+row)<0 || (k+row)>7 || (l+col)>7 || (l+col)<0) {}else{
                    if (!(k == 0 && l == 0)) {
                        //iterates over every field adjacent to current field
                        if (board[row][col] == player) {
                            performEffects(player, row, col, k, l);
                            //performs this method only when current tile belongs to player 1
                        }
                    }
                }
            }
        }
    }

    public void performEffects(int player, int row, int col, int xOffset, int yOffset){
        int adjacentXPos = row + xOffset;
        int adjacentYPos = col + yOffset;
        int nextXPos;
        int nextYPos;
        int opponent;
        if(player == 1){opponent=2;}else {opponent =1;}

        for (int distance = 1; distance <= 8; distance++) {
            // model == how many tiles away from current field
            if (board[adjacentXPos][adjacentYPos] == opponent) {
                // if adjacent field == opponent, check next field
                nextXPos = xOffset * distance;
                nextYPos = yOffset * distance;
                if((nextXPos+row)<0 || (nextXPos+row)>7 || (nextYPos+col)>7 || (nextYPos+col)<0){break;}
                // checks if adjacent field is within the range of 0 through 7, else it breaks out of the loop

                if (nextXPos < -1 || nextXPos > 1 || nextYPos < -1 || nextYPos > 1) {
                    // checks if next position is more than one tile away

                    if (board[row + nextXPos][col + nextYPos] == player) {
                        for (int num = distance; num > 0; num--){
                            board[row+num*xOffset][col+num*yOffset] = player;
                        }
//                        System.out.println(" [" + (row + nextXPos) + ", " + (col + nextYPos) + "] ");
//                        board[(row+nextXPos)][(col+nextYPos)]= 3;
                        break;
                    }
                }
            }
        }
    }

    public boolean positionValid(int x, int y, int player){
        legalMoves(player);
        if (board[x][y] == 3){
            return true;
        }
        else return false;
    }


    public void printBoard() {
        for (int x = 0; x < board[0].length; x++) {
            for (int y = 0; y < board.length; y++) {
                System.out.print(board[y][x]);
            }
            System.out.println();
        }
    }

    public void clearLegalMoves() {
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 3){
                    board[i][j] = 0;
                }
            }
        }
    }

    public void legalMoves(int player) {
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                adjacentTiles(player, j, i);
            }
        }
    }

    public void adjacentTiles(int player, int row, int col){
        for(int k = -1; k <=1; k++) {
            for (int l = -1; l <= 1; l++) {
                if((k+row)<0 || (k+row)>7 || (l+col)>7 || (l+col)<0) {}else{
                    if (!(k == 0 && l == 0)) {
                        //iterates over every field adjacent to current field
                        if (board[row][col] == player) {
                            determineLegalMoves(player, row, col, k, l);
                            //performs this method only when current tile belongs to player 1
                        }
                    }
                }
            }
        }
    }

    public void determineLegalMoves(int player, int row, int col, int xOffset, int yOffset) {
        int adjacentXPos = row + xOffset;
        int adjacentYPos = col + yOffset;
        int nextXPos;
        int nextYPos;
        int opponent;
        if(player==1){opponent=2;}else{opponent=1;}

        for (int distance = 1; distance <= 8; distance++) {
            // model == how many tiles away from current field
            if (board[adjacentXPos][adjacentYPos] == opponent) {
                // if adjacent field == opponent, check next field
                nextXPos = xOffset * distance;
                nextYPos = yOffset * distance;
                if((nextXPos+row)<0 || (nextXPos+row)>7 || (nextYPos+col)>7 || (nextYPos+col)<0){break;}
                // checks if adjacent field is within the range of 0 through 7, else it breaks out of the loop

                if (nextXPos < -1 || nextXPos > 1 || nextYPos < -1 || nextYPos > 1) {
                    // checks if next position is more than one tile away
                    if (board[row + nextXPos][col + nextYPos] == 0) {
                        System.out.println(" [" + (row + nextXPos) + ", " + (col + nextYPos) + "] ");
                        board[(row+nextXPos)][(col+nextYPos)]= 3;
                        break;
                    }
                }
            }
        }
    }
}
