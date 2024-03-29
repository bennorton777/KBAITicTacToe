package agents;

import engine.Board;
import engine.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class description here
 * Package: agents
 */
public abstract class Agent {
    private String _symbol;
    private Agent opponent;
    private static final Random random = new Random();
    private List<String> trace;
    private List<String> moveList;
    private List<String> strategyList;
    String whatMove;
    String whyMove;

    public Agent() {
        trace = new ArrayList<String>();
        moveList = new ArrayList<String>();
        strategyList = new ArrayList<String>();
    }
    public void printTrace() {
        for (String s : trace) {
            System.out.println(s);
        }
    }
    public abstract String getAgentType();

    public State takeTurn(Board board) {
        return chooseRandomSpace(board);
    }

    public State chooseRandomSpace(Board board) {
        List<State> suc = board.getState().getSuccessors(this);
        return suc.get(random.nextInt(suc.size()));
    }

    public String getSymbol() {
        return _symbol;
    }

    public void setSymbol(String symbol) {
        _symbol = symbol;
    }

    public String getOpSymbol() {
        return opponent.getSymbol();
    }
    public double scoreForAgent(Board board, State state, Agent agent) {
        if (board.checkForWins(state)) {
            if (state.getLastMoved() == agent) {
                return 1.0;
            }
            else {
                return -1.0;
            }
        }
        else if (state.isDraw()) {
            return 0.0;
        }
        double score = 0.0;
        List<State> suc = state.getSuccessors(state.getLastMoved().getOpponent());
        for (State successorState : suc) {
            score += scoreForAgent(board, successorState, agent);
        }
        score /= suc.size();
        return score;
    }

    public void setOpponent(Agent opponent) {
        this.opponent = opponent;
    }
    public Agent getOpponent() {
        return opponent;
    }

    public List<String> getTrace() {
        return trace;
    }

    public List<String> getMoveList() {
        return moveList;
    }
    public List<String> getStrategyList() {
        return strategyList;
    }

    public void setTrace(List<String> trace) {
        this.trace = trace;
    }
    public void setMoveList(List<String> list) {
        moveList = list;
    }
    public void setStrategyList(List<String> list) {
        strategyList = list;
    }
    public String identifyMove(int turn){
        return getMoveList().get(--turn);
    }

    public String justifyMove(int turn) {
        return getTrace().get(--turn);
    }

    public String strategy(int turn) {
        return getStrategyList().get(--turn);
    }

    public String domain() {
        return "Not implemented";
    }

    public String isWinning(int turn) {
        return "I don't have a way of gauging that -- I don't know!";
    }
}
