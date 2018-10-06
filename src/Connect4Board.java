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
        if(isHorizotalWin()| isVerticleWin()) {
            return true;
        } else {
            return false;
        }
    }


    private boolean isVerticleWin() {
        int Xcount = 0;
        int Ocount = 0;

        for(int x = 0; x < rows - 1; x++) {
            for (int y = columns - 1; y >= 0; y--) {
                //if space is occupied by current player's game piece, increment counter
                if (this.board[y][x] != null && this.board[y][x].equals("X")) {
                    Xcount++;
                    Ocount = 0;
                }
                if (this.board[y][x] != null && this.board[y][x].equals("O")) {
                    Ocount++;
                    Xcount = 0;
                }
                if(this.board[y][x] == null) {
                    Xcount = 0;
                    Ocount = 0;
                }
                if (Xcount == 3) { //3 of given kind found, return player winner
                    System.out.println("Player X Wins Vertically!");
                    return true;
                }
                else if (Ocount == 3) { //3 of given kind found, return player winner
                    System.out.println("Player O Wins Vertically!");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isHorizotalWin() {
        int Xcount = 0;
        int Ocount = 0;

        for(int x = columns - 1; x >= 0; x--) {
            for (int y = 0; y < rows; y++) {
                //if space is occupied by current player's game piece, increment counter
                if (this.board[x][y] != null && this.board[x][y].equals("X")) {
                    Xcount++;
                    Ocount = 0;
                }
                if (this.board[x][y] != null && this.board[x][y].equals("O")) {
                    Ocount++;
                    Xcount = 0;
                }
                if(this.board[x][y] == null) {
                    Xcount = 0;
                    Ocount = 0;
                }
                if (Xcount == 3) { //3 of given kind found, return player winner
                    System.out.println("Player X Wins Horizontally!");
                    return true;
                }
                else if (Ocount == 3) { //3 of given kind found, return player winner
                    System.out.println("Player O Wins Horizontally!");
                    return true;
                }
            }
        }
        return false;
    }

    //TODO: create method for checking diagonal wins!
    private boolean isDiagonalWin(String player) {
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
                {null, null, null, null, null},
                {null, null, null, "O", null},
                {null, null, "O", "X", null},
                {null, "O", "O", "X", "X"}
    };

        newGame.setBoard(existingBoard);
        newGame.printBoard();
        boolean isWinner = newGame.isWinner();
        System.out.println("is Winner: " + isWinner);
        //newGame.dropCoin("X", 0);
    }
}
