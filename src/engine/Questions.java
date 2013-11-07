package engine;

import agents.Agent;

/**
 * Created with IntelliJ IDEA.
 * User: ben
 * Date: 11/4/13
 * Time: 4:26 PM
 * To change this template use File | Settings | File Templates.
 */
public enum Questions {
    WHAT, WHY, DOMAIN, STRATEGIC, WINNING;

    public String ask(Agent agent, int turn) {
        switch (this) {
            case WHAT: return agent.identifyMove(turn);
            case WHY: return agent.justifyMove(turn);
            case WINNING: return agent.isWinning(turn);
            case STRATEGIC: return agent.strategy(turn);
            case DOMAIN: return agent.domain();
            default: return "Not implemented.";
        }
    }

    public String identify() {
        switch (this) {
            case WHAT: return "What move did you make?";
            case WHY: return "Why did you choose this move?";
            case DOMAIN: return "What domain knowledge did you use for this move?";
            case STRATEGIC: return "What strategic knowledge did you use for this move?";
            case WINNING: return "Are you winning?";
            default: return "Question Undefined";
        }
    }

}

