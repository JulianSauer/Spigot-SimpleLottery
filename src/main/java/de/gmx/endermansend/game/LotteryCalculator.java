package de.gmx.endermansend.game;

import de.gmx.endermansend.interfaces.LotteryCalculatorInterface;

public class LotteryCalculator implements LotteryCalculatorInterface {

    private int[] range;

    public LotteryCalculator() {
        range = new int[]{1, 100};
    }

    public LotteryCalculator(int[] range) {
        setRange(range);
    }

    public LotteryCalculator(int min, int max) {
        this();
        setRange(min, max);
    }

    public int getWinningNumber() {
        return range[0] + (int) (Math.random() * ((range[1] - range[0]) + 1));
    }

    public int[] getRange() {
        return new int[0];
    }

    public boolean setRange(int range[]) {
        return range.length == 2 && setRange(range[0], range[1]);
    }

    public boolean setRange(int min, int max) {

        if (max <= min ||
                max < 0 ||
                min < 0)
            return false;

        range = new int[2];
        range[0] = min;
        range[1] = max;

        return true;

    }

}
