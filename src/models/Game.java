package models;

import java.util.Deque;
import java.util.LinkedList;

public class Game {
    public static volatile Game game;
    private Deque<Player> players;
    private Board board;
    private Dice dice;

    private Game() {}
    private Game(int boardSize, int noOfSnakes, int noOfLadders, int diceCount) {
        this.players = new LinkedList<>();
        this.board = Board.getInstance(boardSize, noOfSnakes, noOfLadders);
        this.dice = Dice.getInstance(diceCount);
    }

    public static Game getInstance(int boardSize, int noOfSnakes, int noOfLadders, int diceCount) {
        if(game == null) {
            synchronized (Game.class) {
                if(game == null) {
                    game = new Game(boardSize, noOfSnakes, noOfLadders, diceCount);
                }
            }
        }
        return game;
    }

    public void addPlayers(int id, String name) {
        this.players.add(new Player(id, name));
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
