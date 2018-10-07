import java.util.HashMap;

public class Connect4Board {
    private static int rows = 4;
    private static int columns = 5;
    private Connect4Board parentBoard = null; //oh it will be
    private String[][] board = new String[columns][rows];
    private HashMap<String, Connect4Board> children = new HashMap<>(); //oh it will be too

    private void setBoard(String[][] board) {
        this.board = board;
    }

    private void setParent(Connect4Board parent) {
        this.parentBoard = parent;
    }


    private void addChild(Connect4Board child) {
        this.children.put(child.toString(), child);
    }

    private void printBoard() {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                if (!this.board[x][y].equals(".")) {
                    System.out.print("[" + this.board[x][y] + "]");
                } else {
                    System.out.print("[.]");
                }
            }
            System.out.println(); //next row
        }
    }

    private boolean isWinner() {
        return isHorizotalWin() | isVerticleWin() | isDiagonalWin();
    }


    private boolean isVerticleWin() {
        int Xcount = 0;
        int Ocount = 0;

        for (int x = 0; x < columns - 1; x++) {
            for (int y = rows - 1; y >= 0; y--) {
                //if space is occupied by current player's game piece, increment counter
                if (!this.board[y][x].equals(".") && this.board[y][x].equals("X")) {
                    Xcount++;
                    Ocount = 0;
                }
                if (!this.board[y][x].equals(".") && this.board[y][x].equals("O")) {
                    Ocount++;
                    Xcount = 0;
                }
                if (this.board[y][x].equals(".")) {
                    Xcount = 0;
                    Ocount = 0;
                }
                if (Xcount == 3) { //3 of given kind found, return player winner
                    System.out.println("Player X Wins Vertically!");
                    return true;
                } else if (Ocount == 3) { //3 of given kind found, return player winner
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

        for (int x = rows - 1; x >= 0; x--) {
            for (int y = 0; y < columns - 1; y++) {
                //if space is occupied by current player's game piece, increment counter
                if (!this.board[x][y].equals(".") && this.board[x][y].equals("X")) {
                    Xcount++;
                    Ocount = 0;
                }
                if (!this.board[x][y].equals(".") && this.board[x][y].equals("O")) {
                    Ocount++;
                    Xcount = 0;
                }
                if (this.board[x][y].equals(".")) {
                    Xcount = 0;
                    Ocount = 0;
                }
                if (Xcount == 3) { //3 of given kind found, return player winner
                    System.out.println("Player X Wins Horizontally!");
                    return true;
                } else if (Ocount == 3) { //3 of given kind found, return player winner
                    System.out.println("Player O Wins Horizontally!");
                    return true;
                }
            }
        }
        return false;
    }

    //checks if a player has won diagonally
    private boolean isDiagonalWin() {
        int countX = 0;
        int countO = 0;

        // ascendingDiagonalCheck
        //sides
        // TOO MANY MAGIC NUMBER FOR LOOPS FU@#!*^$d;fldlmbs!!!
        int jSides = 0;
        for (int i = 2; i < 4; i++) { //this checks all 3 diagonal squares even though after one missing piece its impossible to win
            for (int k = 0; k < 3; k++) {
                //System.out.println(i + " - " + k + ", " + jSides + " + " + k + " = " + (i - k) + ", " + (jSides + k));
                if (!this.board[i - k][jSides + k].equals(".") && this.board[i - k][jSides + k].equals("X")) {
                    countX++;
                    countO = 0;
                }
                if (!this.board[i - k][jSides + k].equals(".") && this.board[i - k][jSides + k].equals("O")) {
                    countO++;
                    countX = 0;
                }
                if (this.board[i - k][jSides + k].equals(".")) {
                    countX = 0;
                    countO = 0;
                }
                if (countX == 3) { //3 of given kind found, return player winner
                    System.out.println("Player X Wins Diagonally Ascending");
                    return true;
                } else if (countO == 3) { //3 of given kind found, return player winner
                    System.out.println("Player O Wins Diagonally Ascending!");
                    return true;
                }
            }
            jSides += 2;
        }

        //Center
        countX = 0; //reset counters
        countO = 0;
        for (int i = 0; i <= 1; i++) {
            for (int k = 0; k < 4; k++) {
                //System.out.println(3 + " - " + k + ", " + i + " + " + k + " = " + (3 - k) + ", " + (i+k));
                if (!this.board[3 - k][i + k].equals(".") && this.board[3 - k][i + k].equals("X")) {
                    countX++;
                    countO = 0;
                }
                if (!this.board[3 - k][i + k].equals(".") && this.board[3 - k][i + k].equals("O")) {
                    countO++;
                    countX = 0;
                }
                if (this.board[3 - k][i + k].equals(".")) {
                    countX = 0;
                    countO = 0;
                }
                if (countX == 3) { //3 of given kind found, return player winner
                    System.out.println("Player X Wins Diagonally Ascending!");
                    return true;
                } else if (countO == 3) { //3 of given kind found, return player winner
                    System.out.println("Player O Wins Diagonally Ascending!");
                    return true;
                }
            }
        }

        // descendingDiagonalCheck
        //sides
        jSides = 2;
        for (int i = 3; i > 1; i--) { //this checks all 3 diagonal squares even though after one missing piece its impossible to win
            for (int k = 0; k < 3; k++) {
                //System.out.println(i + " - " + k + ", " + jSides + " + " + k + " = " + (i - k) + ", " + (jSides - k));
                if (!this.board[i - k][jSides - k].equals(".") && this.board[i - k][jSides - k].equals("X")) {
                    countX++;
                    countO = 0;
                }
                if (!this.board[i - k][jSides - k].equals(".") && this.board[i - k][jSides - k].equals("O")) {
                    countO++;
                    countX = 0;
                }
                if (this.board[i - k][jSides - k].equals(".")) {
                    countX = 0;
                    countO = 0;
                }
                if (countX == 3) { //3 of given kind found, return player winner
                    System.out.println("Player X Wins Diagonally Descending");
                    return true;
                } else if (countO == 3) { //3 of given kind found, return player winner
                    System.out.println("Player O Wins Diagonally Descending!");
                    return true;
                }
            }
            jSides += 2;
        }

        // Center
         jSides = 3;
        for (int i = 4; i > 2; i--) {
                for (int k = 0; k < 4; k++) {
                    //System.out.println(jSides + " - " + k + ", " + i + " - " + k + " = " + (jSides - k) + ", " + (i - k));
                    if (!this.board[jSides - k][i - k].equals(".") && this.board[jSides - k][i - k].equals("X")) {
                        countX++;
                        countO = 0;
                    }
                    if (!this.board[jSides - k][i - k].equals(".") && this.board[jSides - k][i - k].equals("O")) {
                        countO++;
                        countX = 0;
                    }
                    if (this.board[jSides - k][i - k].equals(".")) {
                        countX = 0;
                        countO = 0;
                    }
                    if (countX == 3) { //3 of given kind found, return player winner
                        System.out.println("Player X Wins Diagonally Descending!");
                        return true;
                    } else if (countO == 3) { //3 of given kind found, return player winner
                        System.out.println("Player O Wins Diagonally Descending!");
                        return true;
                    }
            }
        }
        return false;
    }

    //Board move
    public boolean dropCoin(String player, int column) {
        Connect4Board childBoard = new Connect4Board();
        String[][] boardCopy = this.board;

        //make board move
        for (int y = columns - 1; y >= 0; y--) {
            if (boardCopy[y][column].equals(".")) {
                boardCopy[y][column] = player;

                //add new board to children
                childBoard.setBoard(boardCopy);
                childBoard.setParent(this);
                this.addChild(childBoard);

                return true;
            }
            if (y == 0) {
                System.out.println("Column is full, pick a different column");
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Connect4Board newGame = new Connect4Board();
        IOHandler reader = new IOHandler();
        reader.readFile("Files/board.txt");
        String[][] readBoard = reader.boardState;

        /*String[][] existingBoard = new String[][]{
                {null, null, "O", null, null},
                {"O", null, null, "O", null},
                {null, "O", null, null, "O"},
                {null, null, "O", null, null}
        };
    */

        newGame.setBoard(readBoard);
        newGame.printBoard();
        newGame.isWinner();
        //System.out.println("is Winner: " + isWinner);
        //newGame.dropCoin("X", 0);
    }
}
