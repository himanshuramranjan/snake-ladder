package models;

public class Player {
    private final int id;
    private String name;
    private int curPosition;

    public Player(int id, String name, int curPosition) {
        this.id = id;
        this.name = name;
        this.curPosition = curPosition;
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
