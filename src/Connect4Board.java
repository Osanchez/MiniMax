import java.util.HashMap;

public class Connect4Board {
    private static int rows = 5;
    private static int columns = 4;
    private Connect4Board parentBoard = null;
    private String[][] board = new String[columns][rows]; //values int to null by default
    private HashMap<String, Connect4Board> children = new HashMap<>();

    public void setBoard(String[][] board) {
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
                if(this.board[x][y] != null) {
                    System.out.print("[" + this.board[x][y] + "]");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println(); //next row
        }
    }

    private boolean isWinner() {
        boolean isWinner = false;

        return isWinner;
    }


    private boolean isVerticleWin(String player) {
        //String Player - Player Piece (X or O)
        int count = 0;
        for(int x = 0; x < rows - 1; x++) {
            for (int y = columns - 1; y >= 0; y--) {
                //if space is occupied by current player's game piece, increment counter
                if (this.board[y][x] != null && this.board[y][x].equals(player)) {
                    count++;
                    if (count == 3) { //3 of given kind found, return player winner
                        return true;
                    }
                }
                //if space is occupied by other player's game piece reset counter
                else if (this.board[y][x] != null) {
                    count = 0;
                }
            }
        }
        return false;
    }

    private boolean isHorizotalWin(String player) {
        return true;
    }

    //Board move
    public void dropCoin(String player, int column) {
        Connect4Board childBoard = new Connect4Board();
        String[][] boardCopy = this.board;

        //make board move
        for(int y = columns - 1; y >= 0; y--) {
            if(boardCopy[y][column] == null) {
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
        String[][] existingBoard = new String[][] {
                {null, null, null, "X", null},
                {null, null, null, "X", null},
                {null, null, null, "X", null},
                {null, "O", "O", "O", null}
    };

        newGame.setBoard(existingBoard);
        newGame.printBoard();
        boolean xWinsVertically = newGame.isVerticleWin("X");
        System.out.println(xWinsVertically);
        //newGame.dropCoin("X", 0);
    }
}
