import java.util.ArrayList;
import java.util.HashMap;

public class Connect4Board {
    private static int rows = 4;
    private static int columns = 5;
    private String[][] board = new String[columns][rows];

    private Connect4Board parentBoard = null;
    public HashMap<Integer, Connect4Board> children = new HashMap<>();

    public String playerTurn;
    public boolean isTerminal = false;
    public int utility;


    public void setBoard(String[][] board) {
        this.board = board;
    }

    public void setParent(Connect4Board parent) {
        this.parentBoard = parent;
    }

    public void setNextPlayerTurn() {
        if(this.playerTurn.equals("X")) {
            this.playerTurn = "O";
        } else {
            this.playerTurn = "X";
        }
    }

    public String getOppositePlayer(String player) {
        if(player == "X") {
            return "O";
        } else {
            return "X";
        }
    }

    //determines what player goes next
    public String getCurrentPlayerTurn() {
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
            return "X";
        }
        else if (piecesX == piecesO) {
            this.playerTurn = "X";
            return "X";
        } else {
            this.playerTurn = "O";
            return "O";
        }
    }

    public void expand() { //drops a coin in all columns and adds new boards to children
        for(int x = 0; x < columns; x++) {
            dropCoin(this.playerTurn, x);
        }
    }

    public void addChild(int column, Connect4Board child) {
        this.children.put(column, child);
    }

    public void copyBoard(String[][] original, String[][] copy) {
        for(int x = 0; x < rows; x++) {
            for(int y = 0; y < columns; y++) {
                copy[x][y] = original[x][y];
            }
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


    //Utility Function
    public int getUtility() {
        return this.utility;
    }

    public boolean isTerminal() {
        isGoal();
        return this.isTerminal;
    }

    private int isGoal() {
        //Horizontal Check
        int countX = 0;
        int countO = 0;

        for (int x = rows - 1; x >= 0; x--) {
            countX = 0;
            countO = 0;
            for (int y = 0; y <= columns - 1; y++) {
                //if space is occupied by current player's game piece, increment counter
                if (this.board[x][y].equals("X")) {
                    countX++;
                    countO = 0;
                }
                if (this.board[x][y].equals("O")) {
                    countO++;
                    countX = 0;
                }
                if (this.board[x][y].equals(".")) {
                    countX = 0;
                    countO = 0;
                }
                if (countX == 3) { //3 of given kind found, return player winner
                    //System.out.println("Player X Wins Horizontally!");
                    this.isTerminal = true;
                    this.utility = 1;
                    return 1;
                }
                if (countO == 3) { //3 of given kind found, return player winner
                    //System.out.println("Player O Wins Horizontally!");
                    this.isTerminal = true;
                    this.utility = -1;
                    return 1;
                }
            }
        }

        //vertical Check
        countX = 0;
        countO = 0;
        for (int x = 0; x <= columns - 1; x++) {
            countX = 0;
            countO = 0;
            for (int y = rows - 1; y >= 0; y--) {
                //if space is occupied by current player's game piece, increment counter
                if (this.board[y][x].equals("X")) {
                    countX++;
                    countO = 0;
                }
                if (this.board[y][x].equals("O")) {
                    countO++;
                    countX = 0;
                }
                if (this.board[y][x].equals(".")) {
                    countX = 0;
                    countO = 0;
                }
                if (countX == 3) { //3 of given kind found, return player winner
                    //System.out.println("Player X Wins Vertically!");
                    this.isTerminal = true;
                    this.utility = 1;
                    return 1;
                }
                if (countO == 3) { //3 of given kind found, return player winner
                    //System.out.println("Player O Wins Vertically!");
                    this.isTerminal = true;
                    this.utility = -1;
                    return 1;
                }
            }
        }

        //Diagonal Check
        //sides - ascendingDiagonalCheck
        int jSides = 0;
        countX = 0;
        countO = 0;

        for (int i = 2; i < 4; i++) { //this checks all 3 diagonal squares even though after one missing piece its impossible to win
            countX = 0;
            countO = 0;
            for (int k = 0; k < 3; k++) {
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
                    //System.out.println("Player X Wins Diagonally Ascending Side!");
                    this.isTerminal = true;
                    this.utility = 1;
                    return 1;
                }
                if (countO == 3) { //3 of given kind found, return player winner
                    //System.out.println("Player O Wins Diagonally Ascending Side!");
                    this.isTerminal = true;
                    this.utility = -1;
                    return 1;
                }
            }
            jSides += 2;
        }

        //Center
        countX = 0;
        countO = 0;
        for (int i = 0; i <= 1; i++) {
            countX = 0;
            countO = 0;
            for (int k = 0; k < 4; k++) {
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
                    //System.out.println("Player X Wins Diagonally Ascending Center!");
                    this.isTerminal = true;
                    this.utility = 1;
                    return 1;
                }
                if (countO == 3) { //3 of given kind found, return player winner
                    //System.out.println("Player O Wins Diagonally Ascending Center!");
                    this.isTerminal = true;
                    this.utility = -1;
                    return 1;
                }
            }
        }

        // descendingDiagonalCheck
        //sides
        jSides = 2;
        countX = 0;
        countO = 0;
        for (int i = 3; i > 1; i--) { //this checks all 3 diagonal squares even though after one missing piece its impossible to win
            countX = 0;
            countO = 0;
            for (int k = 0; k < 3; k++) {
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
                    //System.out.println("Player X Wins Diagonally Descending Side!");
                    this.isTerminal = true;
                    this.utility = 1;
                    return 1;
                }
                if (countO == 3) { //3 of given kind found, return player winner
                    //System.out.println("Player O Wins Diagonally Descending Side!");
                    this.isTerminal = true;
                    this.utility = -1;
                    return 1;
                }
            }
            jSides += 2;
        }

        // Center
        jSides = 3;
        countX = 0;
        countO = 0;
        for (int i = 4; i > 2; i--) {
            countX = 0;
            countO = 0;
            for (int k = 0; k < 4; k++) {
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
                    //System.out.println("Player X Wins Diagonally Descending Center!");
                    this.isTerminal = true;
                    this.utility = 1;
                    return 1;
                }
                if (countO == 3) { //3 of given kind found, return player winner
                    //System.out.println("Player O Wins Diagonally Descending Center!");
                    this.isTerminal = true;
                    this.utility = -1;
                    return 1;
                }
            }
        }

        //Draw Check
        int filledBoard = columns * rows;
        countX = 0;
        countO = 0;

        for(int x = 0; x <= rows - 1; x++) {
            for(int y = 0; y <= columns - 1; y++) {
                if(this.board[x][y].equals("X")) {
                    countX++;
                }
                if(this.board[x][y].equals("O")) {
                    countO++;
                }
            }
        }

        if((countX + countO) == filledBoard) {
            //System.out.println("Player X and O Draw");
            this.isTerminal = true;
            this.utility = 0;
            return 1;
        }
        return 0;
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
                this.addChild(column, childBoard);
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
