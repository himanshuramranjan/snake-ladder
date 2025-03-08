package models;

import java.util.concurrent.ThreadLocalRandom;

public class Snake extends BoardElement{

    public Snake(int limit, int startPosition) {
        super(limit, startPosition);
    }

    @Override
    protected int calculateEndPosition(int limit, int startPosition) {
        return ThreadLocalRandom.current().nextInt(limit, startPosition);
    }

}
