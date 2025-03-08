package models;

import java.util.concurrent.ThreadLocalRandom;

public class Ladder extends BoardElement {

    public Ladder(int limit, int startPosition) {
        super(limit, startPosition);
    }

    @Override
    protected int calculateEndPosition(int limit, int position) {
        return ThreadLocalRandom.current().nextInt(position + 1, limit);
    }
}
