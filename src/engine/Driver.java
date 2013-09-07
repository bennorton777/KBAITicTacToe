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
        agent1.setOpSymbol("O");
        agent2.setSymbol("O");
        agent2.setOpSymbol("X");

        System.out.println(board);

        int gamesPlayed = 0;
        int agent1Won = 0;
        int agent2Won = 0;
        int numDraws = 0;
        while (gamesPlayed < numGames) {
            System.out.println("Agent " + agent1.getSymbol() + " takes a turn.");
            agent1.takeTurn(board);
            System.out.println(board);
            if (board.checkForWins()) {
                System.out.println("\n" + agent1.getAgentType() + " has won!");
                agent1Won++;
                gamesPlayed++;
                board = new Board();
                continue;
            }
            if (board.isDraw()) {
                System.out.println("\nThis game is a draw!");
                gamesPlayed++;
                numDraws++;
                continue;
            }
            System.out.println("Agent " + agent2.getSymbol() + " takes a turn.");
            agent2.takeTurn(board);
            System.out.println(board);
            if (board.checkForWins()) {
                System.out.println("\n" + agent2.getAgentType() + " has won!");
                agent2Won++;
                gamesPlayed++;
                board = new Board();
                continue;
            }
            if (board.isDraw()) {
                System.out.println("\nThis game is a draw!");
                gamesPlayed++;
                numDraws++;
                continue;
            }
        }

        System.out.println("\n\n\nSUMMARY:\nGames played:\t"+numGames+"\nGames won by agent1 ("+agent1.getAgentType()+"):\t"+agent1Won+"\nGames won by agent2 ("+agent2.getAgentType()+"):\t"+agent2Won+"\nGames drawn:\t"+numDraws+"\n");
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
