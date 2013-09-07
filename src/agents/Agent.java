package agents;

import engine.Board;

/**
 * Class description here
 * Package: agents
 */
public abstract class Agent {
    private String _symbol;

    public abstract String getAgentType();

    public void takeTurn(Board board) {
        chooseRandomSpace(board);
    }

    private void chooseRandomSpace(Board board) {
        board.putSymbolRandom(_symbol);
    }

    public String getSymbol() {
        return _symbol;
    }

    public void setSymbol(String symbol) {
        _symbol = symbol;
    }


}
