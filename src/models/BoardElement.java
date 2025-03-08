package models;

public abstract class BoardElement {
    protected final int startPosition;
    protected final int endPosition;

    protected BoardElement(int limit, int startPosition) {
        this.startPosition = startPosition;
        this.endPosition = calculateEndPosition(limit, startPosition);
    }
    protected abstract int calculateEndPosition(int limit, int startPosition);

    public int getStartPosition() {
        return startPosition;
    }

    public int getEndPosition() {
        return endPosition;
    }
}
