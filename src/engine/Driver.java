package engine;

import agents.Agent;
import agents.ManualAgent;
import agents.NaiveAgent;
import agents.ThoughtfulAgent;

import java.util.List;
import java.util.Scanner;

/**
 * Class description here
 * Package: engine
 */
public class Driver {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Board board = new Board();

        // Select Agents
        int agent1id, agent2id;
        agent1id = getAgentId(scan, "X");
        agent2id = getAgentId(scan, "O");

        Agent agent1 = getAgentFromId(agent1id);
        Agent agent2 = getAgentFromId(agent2id);

        agent1.setSymbol("X");
        agent2.setSymbol("O");

        System.out.println(board);

        while (true) {
            System.out.println("Agent " + agent1.getSymbol() + " takes a turn.");
            agent1.takeTurn(board);
            System.out.println(board);
            if (board.checkForWins()) {
                System.out.println("\n" + agent1.getAgentType() + " has won!");
                break;
            }
            if (board.isDraw()) {
                System.out.println("\nThis game is a draw!");
                break;
            }
            System.out.println("Agent " + agent2.getSymbol() + " takes a turn.");
            agent2.takeTurn(board);
            System.out.println(board);
            if (board.checkForWins()) {
                System.out.println("\n" + agent2.getAgentType() + " has won!");
                break;
            }
            if (board.isDraw()) {
                System.out.println("\nThis game is a draw!");
                break;
            }
        }
    }

    private static int getAgentId(Scanner scan, String symbol) {
        System.out.println("Select 1, 2, or 3 to be the agent for the " + symbol + " team!");
        System.out.println("1: Manual Agent (Human plays!)");
        System.out.println("2: Naive Agent");
        System.out.println("3: Thoughtful Agent");
        System.out.println("For the " + symbol + " team, I choose agent number: ");
        int id = scan.nextInt();
        System.out.println();
        return id;
    }

    private static Agent getAgentFromId(int agentId) {
        switch (agentId) {
            case 1:
                return new ManualAgent();
            case 2:
                return new NaiveAgent();
            case 3:
                return new ThoughtfulAgent();
        }
        return null;
    }
}
