package agents;

import engine.Board;

import java.util.Scanner;

/**
 * Class description here
 * Package: agents
 */
public class ManualAgent extends Agent {

    private final String agentType = "Manual Agent";

    private final Scanner scan = new Scanner(System.in);

    public void takeTurn(Board board) {
        System.out.println("Player " + getSymbol() + ", what row would you like to play on? ");
        int row = scan.nextInt();
        System.out.println("Player " + getSymbol() + ", what column would you like to play on? ");
        int column = scan.nextInt();
        if (!board.putSymbol(row, column, getSymbol())) {
            System.out.println("Illegal move, please select again.\n");
            takeTurn(board);
        }
    }

    public String getAgentType() {
        return agentType;
    }
}
