import java.util.LinkedList;

public class MiniMax {

    public static void main(String[] args) {
        Connect4Board initialBoard = new Connect4Board();
        IOHandler reader = new IOHandler();
        reader.readFile("Files/board.txt");
        String[][] readBoard = reader.boardState;

        initialBoard.setBoard(readBoard);
        initialBoard.printBoard();
        initialBoard.getCurrentPlayerTurn();
        System.out.println();

        //minimax algorithm
        LinkedList<Connect4Board> boardStates = new LinkedList<>();
        LinkedList<Connect4Board> leafNodes = new LinkedList<>();
        boardStates.add(initialBoard);

        while(!boardStates.isEmpty()) {
            Connect4Board current = boardStates.remove();
            if(!current.isTerminal()) {
                current.expand();
                for (Connect4Board child : current.children) {
                    boardStates.add(child);
                }
            } else {
                leafNodes.add(current);
            }
        }
        System.out.println("Winning Boards");
        for(int x = 0; x < 22767; x++) {
            System.out.println();
            leafNodes.get(x).printBoard();
            leafNodes.get(x).isTerminal();
        }
    }
}
