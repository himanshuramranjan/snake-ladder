package models;

import static constants.GameConstants.INITIAL_BOARD_POS;

public class Player {
    private final int id;
    private String name;
    private int curPosition;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.curPosition = INITIAL_BOARD_POS;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurPosition(int curPosition) {
        this.curPosition = curPosition;
    }

    public String getName() {
        return name;
    }

    public int getCurPosition() {
        return curPosition;
    }

    public int getId() {
        return id;
    }
}
