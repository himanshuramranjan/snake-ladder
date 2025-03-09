package models;

import java.util.concurrent.ThreadLocalRandom;

import static constants.GameConstants.DICE_LIMIT;

public class Dice {

    public static volatile Dice dice;
    private int diceCount;

    private Dice() {}

    private Dice(int diceCount) {
        this.diceCount = diceCount;
    }

    public static Dice getInstance(int diceCount) {
        if(dice == null) {
            synchronized (Dice.class) {
                if(dice == null) {
                    dice = new Dice(diceCount);
                }
            }
        }
        return dice;
    }

    public int getDiceCount() {
        return diceCount;
    }

    public int rollDice() {
        int count = this.diceCount;
        int total = 0;

        while (count-- > 0) {
            total += ThreadLocalRandom.current().nextInt(1,DICE_LIMIT + 1);
        }
        return total;
    }
}
