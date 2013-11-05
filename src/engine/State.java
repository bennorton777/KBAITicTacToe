package engine;

import agents.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Class description here
 * Package: engine
 */
public class State {

    private String[] _places;
    private int height;
    private int width;
    private Agent lastMoved;

    State (int width, int height){
        _places = new String[width * height];
        for (int i=0; i<_places.length; i++) {
            _places[i] = "_";
        }
        this.height = height;
        this.width = width;
    }
    public boolean isDraw() {
        for (int i = 0; i<_places.length; i++) {
            if (_places[i].equals("_")) {
                return false;
            }
        }
        return true;
    }

    State (State state, int newIndex, Agent agent) {
        _places = state.getPlaces().clone();
        _places[newIndex] = agent.getSymbol();
        height = state.getHeight();
        width = state.getWidth();
        lastMoved = agent;
    }

    public String[] getPlaces() {
        return _places;
    }

    public List<State> getSuccessors(Agent agent) {
        List<State> newStates = new ArrayList<State>();
        for (int i = 0; i<_places.length; i++) {
            if (_places[i].equals("_")) {
                newStates.add(new State(this, i, agent));
            }
        }
        return newStates;
    }


    public List<List<String>> getHorizontals() {
        List<List<String>> horizontals = new ArrayList<List<String>>();
        for (int row=0; row< height; row++) {
            List<String> horizontal = new ArrayList<String>();
            for (int column=0; column< width; column++) {
                int index = (row * width) + column;
                horizontal.add(_places[index]);
            }
            horizontals.add(horizontal);
        }
        return horizontals;
    }
    public List<List<String>> getVerticals() {
        List<List<String>> verticals = new ArrayList<List<String>>();
        for (int column=0; column< width; column++) {
            List<String> vertical = new ArrayList<String>();
            for (int row=0; row< height; row++) {
                int index = (row * width) + column;
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
        for (int row=0; row< height; row++) {
            for (int column=0; column< width; column++) {
                int index = (row * width) + column;
                if (row == column) {
                    equalDiagonal.add(_places[index]);
                }
                if ((row + column) == (width - 1)) {
                    opposingDiagonal.add(_places[index]);
                }
            }
        }
        diagonals.add(equalDiagonal);
        diagonals.add(opposingDiagonal);
        return diagonals;
    }
    public String progressFrom(State original){
        int differIndex;
        for (differIndex = 0; differIndex < this._places.length; differIndex++) {
            if ( ! _places[differIndex].equals(original._places[differIndex]) ) {
                break;
            }
        }

        return "I put an "+_places[differIndex]+" at "+differIndex/width+","+differIndex%width+".";
    }

    public int getHeight() {
        return height;
    }

    void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    void setWidth(int width) {
        this.width = width;
    }

    public Agent getLastMoved() {
        return lastMoved;
    }

    public void setLastMoved(Agent lastMoved) {
        this.lastMoved = lastMoved;
    }
}
