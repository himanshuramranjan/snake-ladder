package models;

import java.util.Deque;

public class Game {
    private static Game instance;
    private final Deque<Player> players;
    private final Board board;
    private final Dice dice;

    // Private constructor to prevent direct instantiation
    private Game(Deque<Player> players, Board board, Dice dice) {
        this.players = players;
        this.board = board;
        this.dice = dice;
    }

    // Thread-safe one-time initialization
    public static synchronized void initialize(Deque<Player> players, Board board, Dice dice) {
        if (instance == null) {
            instance = new Game(players, board, dice);
        } else {
            throw new IllegalStateException("Game is already initialized!");
        }
    }

    // Get the singleton instance
    public static Game getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Game is not initialized yet! Please initialize first");
        }
        return instance;
    }

    public void startGame() {
        if(players.size() <= 1) {
            System.out.println("Please add more players to start the game");
            return;
        }
        // play the game until last 2 players
        while(players.size() > 1) {
            // get the player
            Player player = players.pollFirst();
            System.out.println(player.getName() + " turn to roll dice");

            // roll the dice to get the count
            int diceValue = dice.rollDice();
            System.out.println("Dice value : " + diceValue);

            // move the player on the board
            int finalPosition = board.getFinalPosition(player.getCurPosition(), diceValue);

            // check if player won the game
            if(finalPosition == board.getBoardSize()) {
                System.out.println(player.getName() + " has won the game");
            }
            else {
                player.setCurPosition(finalPosition);
                players.add(player);
            }
        }
        System.out.println(players.getFirst().getName() + " lost the game");
    }


}
