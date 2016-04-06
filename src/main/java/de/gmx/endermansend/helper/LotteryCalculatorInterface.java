package de.gmx.endermansend.helper;

/**
 * Calculates a winning number based on the given range.
 */
public interface LotteryCalculatorInterface {

    int getWinningNumber();

    int[] getRange();

    int getMin();

    int getMax();

    boolean setRange(int[] range);

    boolean setRange(int min, int max);

}
