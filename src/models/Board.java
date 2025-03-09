package models;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    private final int boardSize;
    private final int noOfSnakes;
    private final int noOfLadders;
    private Cell[][] cells;

    public Board(int boardSize, int noOfSnakes, int noOfLadders) {
        this.boardSize = boardSize;
        this.noOfSnakes = noOfSnakes;
        this.noOfLadders = noOfLadders;

        initializeBoard();
    }

    private void initializeBoard() {
        cells = new Cell[getBoardSize()][getBoardSize()];

        for(int i = 1; i <= getBoardSize(); i++) {
            cells[getRowNumber(i)][getColNumber(i)] = new Cell();
        }

        initializeSnakesOnBoard();
        initializeLaddersOnBoard();

    }

    private void initializeLaddersOnBoard() {
        int count = getNoOfLadders();

        while(count > 0) {
            // generating numbers from 2 as we dont want to put ladders at starting point
            int cellNum = ThreadLocalRandom.current().nextInt(2, getBoardSize());
            int row = getRowNumber(cellNum);
            int col = getColNumber(cellNum);

            if(cells[row][col] == null) {
                Ladder ladder = new Ladder(getBoardSize(), cellNum);
                cells[row][col].setBoardElement(ladder);
                count--;
            }
        }
    }

    private void initializeSnakesOnBoard() {
        int count = getNoOfSnakes();

        while(count > 0) {
            // generating numbers from 2 as we dont want to put snakes at starting point
            int cellNum = ThreadLocalRandom.current().nextInt(2, getBoardSize());
            int row = getRowNumber(cellNum);
            int col = getColNumber(cellNum);

            if(cells[row][col] == null) {
                BoardElement snake = new Snake(1, cellNum);
                cells[row][col].setBoardElement(snake);
                count--;
            }
        }
    }

    public int getFinalPosition(int currPosition) {
        int row = getRowNumber(currPosition);
        int col = getColNumber(currPosition);
        int finalPosition = currPosition;

        if(cells[row][col] != null) {
            if(cells[row][col].getBoardElement() instanceof Snake)
                System.out.println("Snake bit at the position :" + currPosition);
            else
                System.out.println("Climbed the ladder at position : " + currPosition);

            finalPosition = cells[row][col].getBoardElement().getEndPosition();
        }

        return finalPosition;
    }

    private int getRowNumber(int num)
    {
        return (int) (num-1/Math.sqrt(getBoardSize()));
    }
    private int getColNumber(int num)
    {
        return (int) (num-1%Math.sqrt(getBoardSize()));
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getNoOfSnakes() {
        return noOfSnakes;
    }

    public int getNoOfLadders() {
        return noOfLadders;
    }
}
