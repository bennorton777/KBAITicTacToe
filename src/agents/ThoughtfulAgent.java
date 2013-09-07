package agents;

import engine.Board;

/**
 * Class description here
 * Package: agents
 */
public class ThoughtfulAgent extends Agent {

    private final String agentType = "Thoughtful Agent";


    public String getAgentType() {
        return agentType;
    }

    public void takeTurn(Board board) {
        //try to win
        for (int i = 0; i < board.getPlaces().length; i++) {
            if (board.isOpen(i)) {
                board.putSymbolAtIndex(i, getSymbol());
                if (board.checkForWins()) {
                    return;
                }
                else {
                    board.removeSymbolAtIndex(i);
                }
            }
        }
        //try not to lose
        for (int i = 0; i < board.getPlaces().length; i++) {
            if (board.isOpen(i)) {
                board.putSymbolAtIndex(i, getOpSymbol());
                if (board.checkForWins()) {
                    board.removeSymbolAtIndex(i);
                    board.putSymbolAtIndex(i, getSymbol());
                    return;
                }
                else {
                    board.removeSymbolAtIndex(i);
                }
            }
        }
        //pick randomly
        board.putSymbolRandom(getSymbol());
    }

}
