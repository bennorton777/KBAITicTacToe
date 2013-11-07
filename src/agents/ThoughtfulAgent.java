package agents;

import engine.Board;
import engine.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class description here
 * Package: agents
 */
public class ThoughtfulAgent extends Agent {

    private final String agentType = "Thoughtful Agent";
    private List<Boolean> wasWinning;


    public ThoughtfulAgent() {
        wasWinning = new ArrayList<Boolean>();
    }

    public String getAgentType() {
        return agentType;
    }

    public State takeTurn(Board board) {

        List<State> suc = board.getState().getSuccessors(this);

        //try to win
        for (State state : suc) {
            if (board.checkForWins(state)) {
                addTrace(board, state, 0.0);
                return state;
            }
        }

        //try not to lose
        Map<State, Double> viability = new HashMap<State, Double>();
        for (State state : suc) {
            viability.put(state, scoreForAgent(board, state, this));
        }
        double currentScore = Double.NEGATIVE_INFINITY;
        State bestChoice = null;
        for (State state : viability.keySet()) {
            if (viability.get(state) > currentScore ) {
                currentScore = viability.get(state);
                if (currentScore > 0) {
                    wasWinning.add(true);
                }
                else wasWinning.add(false);
                bestChoice = state;
            }
        }
        addTrace(board, bestChoice,currentScore);
        return bestChoice;
    }

    private void addTrace(Board board, State state, double score) {
        getMoveList().add(state.progressFrom(board.getState()));
        if (board.checkForWins(state)) {
            getTrace().add("I chose this state because it is a winning move.");
        }
        else if (state.isDraw()) {
            getTrace().add("I chose this state because a draw is preferable to losing.");
        }
        else {
            getTrace().add("There was no clear win, lose, or draw move at this point, so I chose the best projected outcome based on recursively scoring all of the states.  This state had a score of " + score + " which made it the best option.");
        }
    }

    public String isWinning(int turn) {
        if (wasWinning.get(--turn)) {
            return "I am!  :)\n";
        }
        else return "I am not.  :(\n";
    }

    public String domain() {
        return "\nFor this move, I used the following domain knowledge:\n\nMoves consist of placing an " + getSymbol() + " onto a board with a finite number of spaces.\nI win the game by having three " + getSymbol() + "'s in a row.\nI cannot place an " + getSymbol() + " if there is already a " + getOpSymbol() + " in that space.\nI may only place one " + getSymbol() + " per turn.\nIf at any point there are three " + getOpSymbol() + "'s in a row in the game, I lose.\nI should try to make moves that make it unlikely for my opponent to be able to connect three " + getOpSymbol() + "'s in a row in the future.";
    }
}
