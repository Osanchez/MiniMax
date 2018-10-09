import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class MiniMax {

    public void miniMaxDecision(Connect4Board state) {
        ArrayList<Integer> possibleActions = new ArrayList<>();
        state.expand();

        for(Connect4Board action: state.children) {
           possibleActions.add(minValue(action));
       }

       for(int i = 0; i < possibleActions.size(); i++) {
           if(state.getCurrentPlayerTurn() == "X") {
               if(possibleActions.get(i) == 1) {
                   System.out.println(Integer.toString(possibleActions.get(i)) + "  X" + i);
                   break;
               }
               if(possibleActions.get(i) == 0) {
                   System.out.println(Integer.toString(possibleActions.get(i)) + "  X" + i);
                   break;
               }
           } else {
               if(possibleActions.get(i) == -1) {
                   System.out.println(Integer.toString(possibleActions.get(i)) + "  O" + i);
                   break;
               }
               if(possibleActions.get(i) == 0) {
                   System.out.println(Integer.toString(possibleActions.get(i)) + "  O" + i);
                   break;
               }
           }
       }
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
        initialBoard.printBoard();
        System.out.println();
        miniMax.miniMaxDecision(initialBoard);
    }
}
