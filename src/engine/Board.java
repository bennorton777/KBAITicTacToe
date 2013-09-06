package engine;

import java.util.List;

/**
 * Class description here
 * Package: engine
 */
public class Board {
    private String[] _places;
    public final static int COLUMN_WIDTH = 3;
    public final static int ROW_WIDTH = 3;

    public Board() {
        _places = new String[COLUMN_WIDTH * ROW_WIDTH];
        for (int i=0; i<_places.length; i++) {
            _places[i] = "_";
        }
    }

    public void putX(int row, int column) {
        int index = ((row-1) * ROW_WIDTH) + column;
        if (!_places[index].equals("_")) {

        }
    }
}
