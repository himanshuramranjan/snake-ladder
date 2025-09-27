package models;

import java.util.concurrent.ThreadLocalRandom;

import static constants.GameConstants.DICE_LIMIT;

public class Dice {

    private Dice() {}

    private static class DiceHelper {
        private static final Dice INSTANCE = new Dice();
    }

    public static Dice getInstance() {
        return DiceHelper.INSTANCE;
    }

    public int rollDice() {
        return ThreadLocalRandom.current().nextInt(1,DICE_LIMIT + 1);
    }
}
