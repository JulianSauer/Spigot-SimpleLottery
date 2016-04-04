package de.gmx.endermansend.interfaces;

/**
 * Calculates a winning number based on the given range.
 */
public interface LotteryCalculatorInterface {

    int getWinningNumber();

    int[] getRange();

    boolean setRange(int[] range);

    boolean setRange(int min, int max);

}
