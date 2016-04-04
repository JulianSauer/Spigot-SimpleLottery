package de.gmx.endermansend.interfaces;

public interface LotteryCalculatorInterface {

    int getWinningNumber();

    int[] getRange();

    boolean setRange(int[] range);

    boolean setRange(int min, int max);

}
