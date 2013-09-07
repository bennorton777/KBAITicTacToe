package engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class description here
 * Package: engine
 */
public class Board {
    private String[] _places;
    public final static int COLUMN_LENGTH = 3;
    public final static int ROW_WIDTH = 3;
    private final Random rand = new Random();


    public Board() {
        _places = new String[COLUMN_LENGTH * ROW_WIDTH];
        for (int i=0; i<_places.length; i++) {
            _places[i] = "_";
        }
    }

    public boolean checkForWins() {
        List<List<String>> horizontals = getHorizontals();
        List<List<String>> verticals = getVerticals();
        List<List<String>> diagonals = getDiagonals();
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
        if (!_places[index].equals("_")) {
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
            _places[index] = symbol;
            return true;
        }
        return false;
    }
    public void removeSymbolAtIndex(int index) {
        _places[index] = "_";
    }

    public boolean isDraw() {
        for (int i = 0; i<_places.length; i++) {
            if (_places[i].equals("_")) {
                return false;
            }
        }
        return true;
    }

    public void putSymbolRandom(String symbol) {
        for (int i = rand.nextInt(((ROW_WIDTH * COLUMN_LENGTH) - 1)); i<_places.length; i++) {
            if (isOpen(i)) {
                putSymbolAtIndex(i, symbol);
                return;
            }
        }
        for (int i = 0; i<_places.length; i++) {
            if (isOpen(i)) {
                putSymbolAtIndex(i, symbol);
                return;
            }
        }
    }

    public List<List<String>> getHorizontals() {
        List<List<String>> horizontals = new ArrayList<List<String>>();
        for (int row=0; row<COLUMN_LENGTH; row++) {
            List<String> horizontal = new ArrayList<String>();
            for (int column=0; column<ROW_WIDTH; column++) {
                int index = (row * ROW_WIDTH) + column;
                horizontal.add(_places[index]);
            }
            horizontals.add(horizontal);
        }
        return horizontals;
    }
    public List<List<String>> getVerticals() {
        List<List<String>> verticals = new ArrayList<List<String>>();
        for (int column=0; column<ROW_WIDTH; column++) {
            List<String> vertical = new ArrayList<String>();
            for (int row=0; row<COLUMN_LENGTH; row++) {
                int index = (row * ROW_WIDTH) + column;
                vertical.add(_places[index]);
            }
            verticals.add(vertical);
        }
        return verticals;
    }
    public List<List<String>> getDiagonals() {
        List<List<String>> diagonals = new ArrayList<List<String>>();
        List<String> equalDiagonal = new ArrayList<String>();
        List<String> opposingDiagonal = new ArrayList<String>();
        for (int row=0; row<COLUMN_LENGTH; row++) {
            for (int column=0; column<ROW_WIDTH; column++) {
                int index = (row * ROW_WIDTH) + column;
                if (row == column) {
                    equalDiagonal.add(_places[index]);
                }
                if ((row + column) == (ROW_WIDTH - 1)) {
                    opposingDiagonal.add(_places[index]);
                }
            }
        }
        System.out.println("opposing diagonal has " + opposingDiagonal.size() + " elements.");
        diagonals.add(equalDiagonal);
        diagonals.add(opposingDiagonal);
        return diagonals;
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
                sb.append(" "+_places[(row*3)+column]);
            }
            sb.append(" |\n");
        }
        for (int i=0; i< (ROW_WIDTH+2); i++) {
            sb.append("--");
        }
        sb.append("\n");
        return sb.toString();
    }
    public String[] getPlaces() {
        return _places;
    }

    public void setPlaces(String[] places) {
        _places = places;
    }


}
