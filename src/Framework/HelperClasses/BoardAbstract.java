package Framework.HelperClasses;

abstract public class BoardAbstract {
    protected int[][] board;
    protected int row;
    protected int column;

    BoardAbstract(int row, int column){
        createBoard(row, column);
    }

    public int[][] getBoard(){
        return board;
    }

    public void createBoard(int row, int column) {
        this.row = row;
        this.column = column;
        board = new int[this.row][this.column];
    }

    public void clearBoard() {
        for (int x = 0; x < column; x++) {
            for (int y = 0; y < row; y++){
                board[x][y] = 0;
            }
        }
    }

    public boolean positionValid(int x, int y) {
        if (x < column && x >= 0 && y < row && y >= 0) {
            System.out.println("position valid");
            return true;
        } else {
            return false;
        }
    }

    public boolean positionValid(int x, int y, int player){return true;};

    public void setPlayer(int x, int y, int player) {
        if (positionValid(x, y)) {
            if (board[x][y]==0) {
                board[x][y] = player;
            } else {
                System.out.println("Field already taken");
            }
        }
    }

    public int[][] NumToTwoDimArray(int num){
        // input a value
        int xAxis = num%row;  //determines x axis position
        int yAxis = num/column; // determines y axis position
        System.out.println(xAxis + "" + yAxis); // for testing, remove later
        return new int[xAxis][yAxis];
    }

    public static int ArraytoNum(int x, int y, int column){
        return y*column+x;
    }

    public int coordinatesToInt (int x, int y){
        return y*column+x;
        }

    public void updateBoard(){}
}


