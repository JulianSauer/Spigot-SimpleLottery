package de.gmx.endermansend.simpleLottery.helper;

/**
 * Implements the calculation methods for determining a winner.
 */
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

    /**
     * @return A winning number based on the given range
     */
    public int getWinningNumber() {
        return range[0] + (int) (Math.random() * ((range[1] - range[0]) + 1));
    }

    public int[] getRange() {
        return new int[0];
    }

    public int getMin() {
        return range[0];
    }

    public int getMax() {
        return range[1];
    }

    /**
     * Sets the range for the lottery numbers. Takes care that the value for max is bigger than min.
     *
     * @param range Has to be an array with the following pattern: {min, max}
     * @return False if the range is too long/short or the values in it are pointless
     */
    public boolean setRange(int range[]) {
        return range.length == 2 && setRange(range[0], range[1]);
    }

    /**
     * Sets the range for the lottery numbers. Takes care that the value for max is bigger than min.
     *
     * @param min Smaller value for the range
     * @param max Bigger value for the range
     * @return false if the values are pointless
     */
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
