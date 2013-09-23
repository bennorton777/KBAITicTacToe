package engine;

import agents.Agent;
import agents.NaiveAgent;
import agents.ThoughtfulAgent;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class description here
 * Package: engine
 */
public class Driver {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Board board = new Board();

        System.out.println("Welcome to TicTacToe!  How many games would you like to see played? ");
        int numGames = scan.nextInt();
        System.out.println();

        // Select Agents
        int agent1id, agent2id;
        agent1id = getAgentId(scan, "X");
        agent2id = getAgentId(scan, "O");

        Agent agent1 = getAgentFromId(agent1id);
        Agent agent2 = getAgentFromId(agent2id);

        agent1.setSymbol("X");
        agent1.setOpponent(agent2);
        agent2.setSymbol("O");
        agent2.setOpponent(agent1);

        System.out.println(board);

        int gamesPlayed = 0;
        int agent1Won = 0;
        int agent2Won = 0;
        int numDraws = 0;
        while (gamesPlayed < numGames) {
            System.out.println("Agent " + agent1.getSymbol() + " takes a turn.");
            board.setState(agent1.takeTurn(board));
            System.out.println(board);
            if (board.checkForWins(board.getState())) {
                System.out.println("\n" + agent1.getAgentType() + " has won!");
                agent1Won++;
                gamesPlayed++;
                board = new Board();
                traces(agent1, agent2);
                continue;
            }
            if (board.getState().isDraw()) {
                System.out.println("\nThis game is a draw!");
                gamesPlayed++;
                numDraws++;
                board = new Board();
                traces(agent1, agent2);
                continue;
            }
            System.out.println("Agent " + agent2.getSymbol() + " takes a turn.");
            board.setState(agent2.takeTurn(board));
            System.out.println(board);
            if (board.checkForWins(board.getState())) {
                System.out.println("\n" + agent2.getAgentType() + " has won!");
                traces(agent1, agent2);
                agent2Won++;
                gamesPlayed++;
                board = new Board();
                continue;
            }
            if (board.getState().isDraw()) {
                System.out.println("\nThis game is a draw!");
                traces(agent1, agent2);
                gamesPlayed++;
                numDraws++;
                board = new Board();
                continue;
            }
        }

        System.out.println("\n\n\nSUMMARY:\nGames played:\t"+numGames+"\nGames won by agent1 ("+agent1.getAgentType()+"):\t"+agent1Won+"\nGames won by agent2 ("+agent2.getAgentType()+"):\t"+agent2Won+"\nGames drawn:\t"+numDraws+"\n");
    }

    private static int getAgentId(Scanner scan, String symbol) {
        System.out.println("Select 1 or 2 to be the agent for the " + symbol + " team!");
        System.out.println("1: Naive Agent");
        System.out.println("2: Thoughtful Agent");
        System.out.println("For the " + symbol + " team, I choose agent number: ");
        int id = scan.nextInt();
        System.out.println();
        return id;
    }

    private static Agent getAgentFromId(int agentId) {
        switch (agentId) {
            case 1:
                return new NaiveAgent();
            case 2:
                return new ThoughtfulAgent();
        }
        return null;
    }
    public static void traces(Agent agent1, Agent agent2) {
        System.out.println("Agent 1 ("+agent1.getAgentType()+") maintains the following trace of why he chose the actions he did:");
        agent1.printTrace();
        System.out.println("Agent 2 ("+agent2.getAgentType()+") maintains the following trace of why he chose the actions he did:");
        agent2.printTrace();
        agent1.setTrace(new ArrayList<String>());
        agent2.setTrace(new ArrayList<String>());
    }
}
