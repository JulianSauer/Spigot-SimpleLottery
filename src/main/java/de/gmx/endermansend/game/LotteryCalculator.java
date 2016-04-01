package de.gmx.endermansend.game;

import de.gmx.endermansend.interfaces.LotteryCalculatorInterface;

public class LotteryCalculator implements LotteryCalculatorInterface {

    private int roundNumber;
    private int[] range;

    public LotteryCalculator() {
        roundNumber = 1;
        range = new int[]{1, 100};
    }

    public LotteryCalculator(int roundNumber) {
        this();
        setRoundNumber(roundNumber);
    }

    public LotteryCalculator(int[] range) {
        this();
        setRange(range);
    }

    public LotteryCalculator(int min, int max) {
        this();
        setRange(min, max);
    }

    public LotteryCalculator(int roundNumber, int[] range) {
        setRoundNumber(roundNumber);
        setRange(range);
    }

    public LotteryCalculator(int roundNumber, int min, int max) {
        setRoundNumber(roundNumber);
        setRange(min, max);
    }

    public Ticket getWinnerTicket() {
        int winnerNumber = range[0] + (int)(Math.random() * ((range[1] - range[0]) + 1));
        Ticket winnerTicket = new Ticket(roundNumber, winnerNumber);
        return winnerTicket;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public int[] getRange() {
        return new int[0];
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public boolean setRange(int range[]) {

        if(range.length != 2)
            return false;

        return setRange(range[0], range[1]);

    }

    public boolean setRange(int min, int max) {

        if(max <= min ||
                max < 0 ||
                min < 0)
            return false;

        range = new int[2];
        range[0] = min;
        range[1] = max;

        return true;

    }

}
