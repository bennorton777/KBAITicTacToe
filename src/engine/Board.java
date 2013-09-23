package engine;

import agents.Agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class description here
 * Package: engine
 */
public class Board {
    private State  _state;
    public final static int COLUMN_LENGTH = 3;
    public final static int ROW_WIDTH = 3;
    private final Random rand = new Random();


    public Board() {
        _state = new State(ROW_WIDTH, COLUMN_LENGTH);
    }

    public boolean checkForWins(State state) {
        List<List<String>> horizontals = state.getHorizontals();
        List<List<String>> verticals = state.getVerticals();
        List<List<String>> diagonals = state.getDiagonals();
        for (List<String> line : horizontals) {
            if (checkWinLine(line)) {
                return true;
            }
        }
        for (List<String> line : verticals) {
            if (checkWinLine(line)) {
                return true;
            }
        }
        for (List<String> line : diagonals) {
            if (checkWinLine(line)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkWinLine(List<String> line) {
        if (line.get(0).equals(line.get(1)) && line.get(1).equals(line.get(2)) && !line.get(0).equals("_")) {
            return true;
        }
        return false;
    }

    public boolean isOpen(int index) {
        if (!_state.getPlaces()[index].equals("_")) {
            return false;
        }
        return true;
    }

    public boolean putSymbol(int row, int column, String symbol) {
        int index = ((row-1) * ROW_WIDTH) + column - 1;
        return putSymbolAtIndex(index, symbol);
    }

    //method returns true with successful placement, false if there's a problem
    public boolean putSymbolAtIndex(int index, String symbol) {
        if (isOpen(index)) {
            _state.getPlaces()[index] = symbol;
            return true;
        }
        return false;
    }
    public void removeSymbolAtIndex(int index) {
        _state.getPlaces()[index] = "_";
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i< (ROW_WIDTH+2); i++) {
            sb.append("--");
        }
        sb.append("\n");
        for (int row=0; row< COLUMN_LENGTH; row++) {
            sb.append("|");
            for (int column=0;column<ROW_WIDTH;column++) {
                sb.append(" "+_state.getPlaces()[(row*3)+column]);
            }
            sb.append(" |\n");
        }
        for (int i=0; i< (ROW_WIDTH+2); i++) {
            sb.append("--");
        }
        sb.append("\n");
        return sb.toString();
    }

    public State getState() {
        return _state;
    }

    public void setState(State state) {
        _state = state;
    }

}
