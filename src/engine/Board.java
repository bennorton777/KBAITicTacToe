package engine;

/**
 * Class description here
 * Package: engine
 */
public class Board {
    private String[] _places;
    public final static int COLUMN_LENGTH = 3;
    public final static int ROW_WIDTH = 3;

    public Board() {
        _places = new String[COLUMN_LENGTH * ROW_WIDTH];
        for (int i=0; i<_places.length; i++) {
            _places[i] = "_";
        }
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

    public void putSymbolRandom(String symbol) {
        for (int i = 0; i<_places.length; i++) {
            if (isOpen(i)) {
                putSymbolAtIndex(i, symbol);
                return;
            }
        }
        //TODO catch failure case?
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
}
