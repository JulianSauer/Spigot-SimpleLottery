package de.gmx.endermansend.interfaces;

import de.gmx.endermansend.game.Ticket;

public interface LotteryCalculatorInterface {

    Ticket getWinnerTicket();

    int getRoundNumber();

    int[] getRange();

    void setRoundNumber(int roundNumber);

    boolean setRange(int[] range);

    boolean setRange(int min, int max);

}
