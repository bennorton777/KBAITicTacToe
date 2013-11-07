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

    public String domain() {
        return "\nFor this move, I used the following domain knowledge:\n\nMoves consist of placing an " + getSymbol() + " onto a board with a finite number of spaces.\nI win the game by having three " + getSymbol() + "'s in a row.\nI cannot place an " + getSymbol() + " if there is already a " + getOpSymbol() + " in that space.\nI may only place one " + getSymbol() + " per turn.\nIf at any point there are three " + getOpSymbol() + "'s in a row in the game, I lose.";
    }

    //TODO Naive agent should know if it was winning or not
}
