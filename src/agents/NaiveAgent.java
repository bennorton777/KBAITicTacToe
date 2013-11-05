package agents;

import engine.Board;
import engine.State;

import java.util.List;
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

    public State takeTurn(Board board) {
        List<State> suc = board.getState().getSuccessors(this);
        for (State state : suc) {
            if (board.checkForWins(state) && rand.nextBoolean()) {
                // 50% chance to choose a winning move
                addTrace(board, state, 0.0);
                return state;
            }
        }
        // Choose a random next move as a fallback.\
        State retVal = suc.get(rand.nextInt(suc.size()));
        addTrace(board, retVal, 0.0);
        getMoveList().add(retVal.progressFrom(board.getState()));
        return retVal;
    }


    private void addTrace(Board board, State state, double score) {
        if (board.checkForWins(state)) {
            getTrace().add("I chose this state because it is a winning move.");
        }
        else {
            getTrace().add("There was no clear winning move, so I chose a random legal move.");
        }
    }
}
