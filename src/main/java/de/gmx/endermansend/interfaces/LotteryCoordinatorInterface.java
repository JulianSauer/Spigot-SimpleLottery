package de.gmx.endermansend.interfaces;

import de.gmx.endermansend.game.RoundWithDefaultSettings;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.logging.Logger;

public abstract class LotteryCoordinatorInterface {

    private int roundNumber = 0;

    private RoundInterface round;

    private LotteryCalculatorInterface calc;

    private Logger logger;

    public LotteryCoordinatorInterface(LotteryCalculatorInterface calc, Logger logger) {
        this.calc = calc;
        this.logger = logger;
    }

    public void startNewGame() {

        round = new RoundWithDefaultSettings(roundNumber++);

    }

    public void finishGame() {

        Collection<String> winners;
        int winningNumber = calc.getWinnerTicket().getLuckyNumber();

        winners = round.finishRound(winningNumber);

        logger.info("Round #" + roundNumber + " is over");
        logger.info("Winning number is " + winningNumber);
        if(winners.size() != 0) {

            logger.info("Winning player(s):");
            for(String player : winners)
                logger.info(player);

        } else {
            logger.info("Nobody won :(");
        }

    }

    public void addPlayer(Player player, int luckyNumber) {

        if (round.addLotteryEntry(player, luckyNumber)) {
            player.sendMessage("You were added to this round!");
        } else {
            player.sendMessage("Could not create entry!");
        }

    }

}

