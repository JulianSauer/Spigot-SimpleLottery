package de.gmx.endermansend.interfaces;

import de.gmx.endermansend.game.RoundWithDefaultSettings;
import de.gmx.endermansend.handlers.ChatHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public abstract class LotteryCoordinatorInterface {

    private int roundNumber = 0;

    private RoundInterface round;

    private LotteryCalculatorInterface calc;

    private ChatHandlerInterface chat;

    public LotteryCoordinatorInterface(ConfigHandlerInterface config, LotteryCalculatorInterface calc) {
        this.calc = calc;
        chat = new ChatHandler(config);
    }

    public void startNewGame(CommandSender sender) {

        if (round != null) {
            if (round.getStatus() != RoundInterface.Status.GAME_HAS_FINISHED) {
                chat.sendErrorMessage(sender, "Can't start new game. Game #" + roundNumber + " is not finished!");
                return;
            }
        }
        round = new RoundWithDefaultSettings(roundNumber++);
        chat.broadcastRoundStart(roundNumber);

    }

    public void finishGame(CommandSender sender) {

        Collection<String> winners;
        int winningNumber = calc.getWinnerTicket().getLuckyNumber();

        if (round.getStatus() == RoundInterface.Status.GAME_HAS_FINISHED) {
            chat.sendErrorMessage(sender, "Game #" + roundNumber + " has already stopped.");
            return;
        }
        winners = round.finishRound(winningNumber);

        chat.broadcastWinners(roundNumber, winningNumber, winners);

    }

    public void addPlayer(Player player, int luckyNumber) {

        if (round.addLotteryEntry(player, luckyNumber)) {
            chat.sendTicketBought(player, luckyNumber);
        } else {
            chat.sendTicketFailure(player, luckyNumber);
        }

    }

}

