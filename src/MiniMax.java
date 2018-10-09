import java.util.ArrayList;
import java.util.LinkedList;

public class MiniMax {

    public Connect4Board miniMaxDecision(Connect4Board state) {
       return state;
    }

    public int maxValue(Connect4Board state) {
        if(state.isTerminal()) {
            return state.getUtility();
        }

        int v = Integer.MIN_VALUE;

        state.expand();

        for(Connect4Board a: state.children) {
            v = Math.max(v, minValue(a));
        }
        return v;
    }

    public int minValue(Connect4Board state) {
        if(state.isTerminal()) {
            return state.getUtility();
        }

        int v = Integer.MAX_VALUE;

        state.expand();

        for(Connect4Board a: state.children) {
            v = Math.min(v, maxValue(a));
        }
        return v;
    }

    public static void main(String[] args) {
        IOHandler reader = new IOHandler();
        reader.readFile("Files/board.txt");
        String[][] readBoard = reader.boardState;

        Connect4Board initialBoard = new Connect4Board();
        initialBoard.setBoard(readBoard);
        initialBoard.getCurrentPlayerTurn();

        MiniMax miniMax = new MiniMax();

        System.out.println(miniMax.maxValue(initialBoard));
        System.out.println(miniMax.minValue(initialBoard));
    }
}
