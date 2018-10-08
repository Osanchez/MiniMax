import java.util.ArrayList;

public class Connect4Board {
    private static int rows = 4;
    private static int columns = 5;
    private String[][] board = new String[columns][rows];
    private Connect4Board parentBoard = null;
    public ArrayList<Connect4Board> children = new ArrayList<>();
    public String playerTurn;
    public boolean isTerminal;
    public int min;
    public int max;

    public Connect4Board() {

    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    public void setParent(Connect4Board parent) {
        this.parentBoard = parent;
    }


    public void addChild(Connect4Board child) {
        this.children.add(child);
    }

    public void setNextPlayerTurn() {
        if(this.playerTurn.equals("X")) {
            this.playerTurn = "O";
        } else {
            this.playerTurn = "X";
        }
    }

    public void copyBoard(String[][] original, String[][] copy) {
        for(int x = 0; x < rows; x++) {
            for(int y = 0; y < columns; y++) {
                copy[x][y] = original[x][y];
            }
        }
    }

    //determines what player goes next
    public void getCurrentPlayerTurn() {
        int piecesX = 0;
        int piecesO = 0;

        for(int x = 0; x < rows; x++) {
            for(int y = 0; y < columns; y++) {
                if(this.board[x][y].equals("X")) {
                    piecesX++;
                }
                else if(this.board[x][y].equals("O")) {
                    piecesO++;
                }
            }
        }

        if(piecesX < piecesO) {
            this.playerTurn =  "X";
        }
        else if (piecesX == piecesO) {
            this.playerTurn = "X";
        } else {
            this.playerTurn = "O";
        }
    }

    public void expand() { //drops a coin in all columns and adds new boards to children
        for(int x = 0; x < columns; x++) {
            dropCoin(this.playerTurn, x);
        }
    }

    public void printBoard() {
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

        //is terminal
    public boolean isTerminal() {
        if(isHorizotalWin() | isVerticleWin() | isDiagonalWin()| isDraw()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isDraw() {
        int filledBoard = columns * rows;
        int spacesOccupied;
        int countX = 0;
        int countY = 0;

        for(int x = 0; x < rows - 1; x++) {
            for(int y = 0; y < columns - 1; y++) {

                if(this.board[x][y].equals("X")) {
                    countX++;
                }
                if(this.board[x][y].equals("O")) {
                    countY++;
                }
            }
        }

        spacesOccupied = countX + countY;

        if(spacesOccupied == filledBoard) {
            System.out.println("Player X and O Draw");
            return true;
        } else {
            return false;
        }
    }


    private boolean isVerticleWin() {
        int Xcount = 0;
        int Ocount = 0;

        for (int x = 0; x <= columns - 1; x++) {
            Xcount = 0;
            Ocount = 0;
            for (int y = rows - 1; y >= 0; y--) {
                //if space is occupied by current player's game piece, increment counter
                if (this.board[y][x].equals("X")) {
                    Xcount++;
                    Ocount = 0;
                }
                if (this.board[y][x].equals("O")) {
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
                }
                if (Ocount == 3) { //3 of given kind found, return player winner
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
            Xcount = 0;
            Ocount = 0;
            for (int y = 0; y <= columns - 1; y++) {
                //if space is occupied by current player's game piece, increment counter
                if (this.board[x][y].equals("X")) {
                    Xcount++;
                    Ocount = 0;
                }
                if (this.board[x][y].equals("O")) {
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
                }
                if (Ocount == 3) { //3 of given kind found, return player winner
                    System.out.println("Player O Wins Horizontally!");
                    return true;
                }
            }
        }
        return false;
    }

    //checks if a player has won diagonally
    private boolean isDiagonalWin() {
        // ascendingDiagonalCheck
        //sides
        int countX = 0;
        int countO = 0;
        int jSides = 0;

        for (int i = 2; i < 4; i++) { //this checks all 3 diagonal squares even though after one missing piece its impossible to win
            countX = 0;
            countO = 0;
            for (int k = 0; k < 3; k++) {
                //System.out.println(i + " - " + k + ", " + jSides + " + " + k + " = " + (i - k) + ", " + (jSides + k));
                if (this.board[i - k][jSides + k].equals("X")) {
                    countX++;
                    countO = 0;
                }
                if (this.board[i - k][jSides + k].equals("O")) {
                    countO++;
                    countX = 0;
                }
                if (this.board[i - k][jSides + k].equals(".")) {
                    countX = 0;
                    countO = 0;
                }
                if (countX == 3) { //3 of given kind found, return player winner
                    System.out.println("Player X Wins Diagonally Ascending Side!");
                    return true;
                }
                if (countO == 3) { //3 of given kind found, return player winner
                    System.out.println("Player O Wins Diagonally Ascending Side!");
                    return true;
                }
            }
            jSides += 2;
        }

        //Center
        countX = 0; //reset counters
        countO = 0;

        for (int i = 0; i <= 1; i++) {
            countX = 0;
            countO = 0;
            for (int k = 0; k < 4; k++) {
                //System.out.println(3 + " - " + k + ", " + i + " + " + k + " = " + (3 - k) + ", " + (i+k));
                if (this.board[3 - k][i + k].equals("X")) {
                    countX++;
                    countO = 0;
                }
                if (this.board[3 - k][i + k].equals("O")) {
                    countO++;
                    countX = 0;
                }
                if (this.board[3 - k][i + k].equals(".")) {
                    countX = 0;
                    countO = 0;
                }
                if (countX == 3) { //3 of given kind found, return player winner
                    System.out.println("Player X Wins Diagonally Ascending Center!");
                    return true;
                }
                if (countO == 3) { //3 of given kind found, return player winner
                    System.out.println("Player O Wins Diagonally Ascending Center!");
                    return true;
                }
            }
        }

        // descendingDiagonalCheck
        //sides
        countX = 0; //reset counters
        countO = 0;

        jSides = 2;
        for (int i = 3; i > 1; i--) { //this checks all 3 diagonal squares even though after one missing piece its impossible to win
            countX = 0;
            countO = 0;
            for (int k = 0; k < 3; k++) {
                //System.out.println(i + " - " + k + ", " + jSides + " + " + k + " = " + (i - k) + ", " + (jSides - k));
                if (this.board[i - k][jSides - k].equals("X")) {
                    countX++;
                    countO = 0;
                }
                if (this.board[i - k][jSides - k].equals("O")) {
                    countO++;
                    countX = 0;
                }
                if (this.board[i - k][jSides - k].equals(".")) {
                    countX = 0;
                    countO = 0;
                }
                if (countX == 3) { //3 of given kind found, return player winner
                    System.out.println("Player X Wins Diagonally Descending Side!");
                    return true;
                }
                if (countO == 3) { //3 of given kind found, return player winner
                    System.out.println("Player O Wins Diagonally Descending Side!");
                    return true;
                }
            }
            jSides += 2;
        }

        // Center
         jSides = 3;
        countX = 0; //reset counters
        countO = 0;

        for (int i = 4; i > 2; i--) {
            countX = 0;
            countO = 0;
                for (int k = 0; k < 4; k++) {
                    //System.out.println(jSides + " - " + k + ", " + i + " - " + k + " = " + (jSides - k) + ", " + (i - k));
                    if (this.board[jSides - k][i - k].equals("X")) {
                        countX++;
                        countO = 0;
                    }
                    if (this.board[jSides - k][i - k].equals("O")) {
                        countO++;
                        countX = 0;
                    }
                    if (this.board[jSides - k][i - k].equals(".")) {
                        countX = 0;
                        countO = 0;
                    }
                    if (countX == 3) { //3 of given kind found, return player winner
                        System.out.println("Player X Wins Diagonally Descending Center!");
                        return true;
                    }
                    if (countO == 3) { //3 of given kind found, return player winner
                        System.out.println("Player O Wins Diagonally Descending Center!");
                        return true;
                    }
            }
        }
        return false;
    }

    //Board move
    public void dropCoin(String player, int column) {
        Connect4Board childBoard = new Connect4Board();
        String[][] boardCopy = new String[rows][columns];
        copyBoard(this.board, boardCopy);

        childBoard.playerTurn = this.playerTurn;
        childBoard.setParent(this);
        childBoard.setNextPlayerTurn();


        //make board move
        for (int x = 3; x >= 0; x--) {
            if (boardCopy[x][column].equals(".")) {
                boardCopy[x][column] = player;

                //add new board to children
                childBoard.setBoard(boardCopy);
                this.addChild(childBoard);
                break;
            }
        }
    }

    public static void main(String[] args) {
        Connect4Board initialBoard = new Connect4Board();
        IOHandler reader = new IOHandler();
        reader.readFile("Files/board.txt");
        String[][] readBoard = reader.boardState;

        initialBoard.setBoard(readBoard);
        initialBoard.printBoard();
        initialBoard.getCurrentPlayerTurn();

        //check win test
        initialBoard.isTerminal();
    }
}
