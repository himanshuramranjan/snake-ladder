import models.Board;
import models.Dice;
import models.Game;
import models.Player;

import java.util.ArrayDeque;
import java.util.Deque;

public class SnakeLadderApplication {
    public static void main(String[] args) {

        Deque<Player> players = new ArrayDeque<>();
        players.add(new Player(1, "Alice"));
        players.add(new Player(2, "Bob"));

        Board board = new Board(100, 4, 3);
        Dice dice = Dice.getInstance();

        // Initialize once
        Game.initialize(players, board, dice);

        // Access anywhere
        Game game = Game.getInstance();
        game.startGame();

    }
}