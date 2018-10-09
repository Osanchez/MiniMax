import java.lang.reflect.Array;
import java.util.*;


public class MiniMax {

    public void miniMaxDecision(Connect4Board state) {
        ArrayList<Integer> miniMaxValues = new ArrayList<>();
        ArrayList<Integer> columnValue = new ArrayList<>();
        state.expand();

        if(state.getCurrentPlayerTurn() == "X") {
            for (Map.Entry action : state.children.entrySet()) {
                columnValue.add((Integer) action.getKey());
                miniMaxValues.add(minValue((Connect4Board) action.getValue()));
            }
        } else {
            for (Map.Entry action : state.children.entrySet()) {
                columnValue.add((Integer) action.getKey());
                miniMaxValues.add(maxValue((Connect4Board) action.getValue()));
            }
        }

        //System.out.println(miniMaxValues);
        //System.out.println(columnValue);

       for(int i = 0; i < miniMaxValues.size(); i++) {
           if(state.getCurrentPlayerTurn() == "X") {
               if(miniMaxValues.get(i) == 1) {
                   System.out.println(Integer.toString(miniMaxValues.get(i)) + "  X" + columnValue.get(i));
                   break;
               }
               if(miniMaxValues.get(i) == 0) {
                   System.out.println(Integer.toString(miniMaxValues.get(i)) + "  X" + columnValue.get(i));
                   break;
               }
               if(i == miniMaxValues.size() - 1) { //def lose
                   System.out.println(Integer.toString(miniMaxValues.get(i)) + "  X" + columnValue.get(0));
               }
           } else {
               if(miniMaxValues.get(i) == -1) {
                   System.out.println(Integer.toString(miniMaxValues.get(i)) + "  O" + columnValue.get(i));
                   break;
               }
               if(miniMaxValues.get(i) == 0) {
                   System.out.println(Integer.toString(miniMaxValues.get(i)) + "  O" + columnValue.get(i));
                   break;
               }
               if(i == miniMaxValues.size() - 1) { //def lose
                   System.out.println(Integer.toString(miniMaxValues.get(i)) + "  O" + columnValue.get(0));
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

        for(Connect4Board a: state.children.values()) {
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

        for(Connect4Board a: state.children.values()) {
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
