package models;

public class Cell {
    private Snake snake;
    private Ladder ladder;

    public Cell(Snake snake, Ladder ladder) {
        this.snake = snake;
        this.ladder = ladder;
    }

    public Snake getSnake() {
        return snake;
    }

    public Ladder getLadder() {
        return ladder;
    }
}
