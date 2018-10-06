import java.util.HashMap;

public class Connect4Board {
    private static int rows = 5;
    private static int columns = 4;
    private Connect4Board parentBoard = null;
    private int[][] board = new int[columns][rows]; //values int to null by default
    private HashMap<String, Connect4Board> children = new HashMap<>();

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setParent(Connect4Board parent) {
        this.parentBoard = parent;
    }


    public void addChild(Connect4Board child) {
        this.children.put(child.toString(), child);
    }

    private void printBoard() {
        for(int x = 0; x < columns; x++) {
            for(int y = 0; y < rows; y++) {
                System.out.print("[" + this.board[x][y] + "]");
            }
            System.out.println(); //next row
        }
    }

    //Board move
    public void dropCoin(int player, int column) {
        Connect4Board childBoard = new Connect4Board();
        int[][] boardCopy = this.board;

        //make board move
        for(int y = columns - 1; y >= 0; y--) {
            if(boardCopy[y][column] == 0) {
                boardCopy[y][column] = player;
                break; //prevents multiple calls
            }
        }

        //add new board to children
        childBoard.setBoard(boardCopy);
        childBoard.setParent(this);
        this.addChild(childBoard);

        //debug
        childBoard.printBoard();
    }

    public static void main (String[] args) {
        Connect4Board newGame = new Connect4Board();
        newGame.dropCoin(1, 0);
    }
}
