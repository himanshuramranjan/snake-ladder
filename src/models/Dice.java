package models;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    private int diceCount;

    public Dice(int diceCount) {
        this.diceCount = diceCount;
    }

    public int getDiceCount() {
        return diceCount;
    }

    public void setDiceCount(int diceCount) {
        this.diceCount = diceCount;
    }

    public int rollDice() {
        int count = this.diceCount;
        int total = 0;

        while (count-- > 0) {
            total += ThreadLocalRandom.current().nextInt(1,7);
        }
        return total;
    }
}
