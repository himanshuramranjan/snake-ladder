package models;

import java.util.concurrent.ThreadLocalRandom;

import static constants.GameConstants.INITIAL_BOARD_POS;

public class Board {
    public volatile static Board board;
    private Board() {}
    private int boardSize;
    private int noOfSnakes;
    private int noOfLadders;
    private BoardElement[][] cells;

    private Board(int boardSize, int noOfSnakes, int noOfLadders) {
        this.boardSize = boardSize;
        this.noOfSnakes = noOfSnakes;
        this.noOfLadders = noOfLadders;

        initializeBoard();
    }

    public static Board getInstance(int boardSize, int noOfSnakes, int noOfLadders) {
        if(board == null) {
            synchronized (Board.class) {
                if(board == null) {
                    board = new Board(boardSize, noOfSnakes, noOfLadders);
                }
            }
        }
        return board;
    }

    private void initializeBoard() {
        cells = new BoardElement[getBoardSize()][getBoardSize()];

        initializeSnakesOnBoard();
        initializeLaddersOnBoard();

    }

    private void initializeLaddersOnBoard() {
        int count = getNoOfLadders();

        while(count > 0) {
            // generating numbers from 2 as we dont want to put ladders at starting point
            int cellNum = ThreadLocalRandom.current().nextInt(INITIAL_BOARD_POS + 1, getBoardSize());
            int row = getRowNumber(cellNum);
            int col = getColNumber(cellNum);

            if(cells[row][col] == null) {
                cells[row][col] = new Ladder(getBoardSize(), cellNum);
                count--;
            }
        }
    }

    private void initializeSnakesOnBoard() {
        int count = getNoOfSnakes();

        while(count > 0) {
            // generating numbers from 2 as we dont want to put snakes at starting point
            int cellNum = ThreadLocalRandom.current().nextInt(INITIAL_BOARD_POS + 1, getBoardSize());
            int row = getRowNumber(cellNum);
            int col = getColNumber(cellNum);

            if(cells[row][col] == null) {
                cells[row][col] = new Snake(INITIAL_BOARD_POS, cellNum);
                count--;
            }
        }
    }

    public int getFinalPosition(int currPosition, int moves) {
        if(moves + currPosition > boardSize) return currPosition;

        currPosition += moves;
        int row = getRowNumber(currPosition);
        int col = getColNumber(currPosition);
        int finalPosition = currPosition;

        if(cells[row][col] != null) {
            if(cells[row][col] instanceof Snake)
                System.out.println("Snake bit at the position :" + currPosition);
            else
                System.out.println("Climbed the ladder at position : " + currPosition);

            finalPosition = cells[row][col].getEndPosition();
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
