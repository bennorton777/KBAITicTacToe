package agents;

import engine.Board;

import java.util.Random;

/**
 * Class description here
 * Package: agents
 */
public class NaiveAgent extends Agent{

    private final String agentType = "Naive Agent";

    private final Random rand = new Random();


    public String getAgentType() {
        return agentType;
    }

    public void takeTurn(Board board) {
        //Don't block the opponent
        for (int i = 0; i < board.getPlaces().length; i++) {
            if (board.isOpen(i)) {
                board.putSymbolAtIndex(i, getOpSymbol());
                if (!board.checkForWins() && rand.nextBoolean()) {
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
