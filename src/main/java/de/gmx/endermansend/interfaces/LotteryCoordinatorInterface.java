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
        round = new RoundWithDefaultSettings(++roundNumber);
        chat.broadcastRoundStart(roundNumber);

    }

    public void finishGame(CommandSender sender) {

        Collection<String> winners;
        int winningNumber = calc.getWinnerTicket().getTicketNumber();

        if (round.getStatus() == RoundInterface.Status.GAME_HAS_FINISHED) {
            chat.sendErrorMessage(sender, "Game #" + roundNumber + " has already stopped.");
            return;
        }
        winners = round.finishRound(winningNumber);

        chat.broadcastWinners(roundNumber, winningNumber, winners);

    }

    public void addPlayer(Player player, int ticketNumber) {

        if (round.addLotteryEntry(player, ticketNumber)) {
            chat.sendTicketBought(player, ticketNumber);
        } else {
            chat.sendTicketFailure(player, ticketNumber);
        }

    }

    public void listTicketsPublic(CommandSender sender) {

        if (round == null) {
            chat.sendErrorMessage(sender, "No round was started yet!");
            return;
        }
        chat.broadcastBoughtTickets(round);
    }

    public void listTicketsPrivate(CommandSender sender) {
        if (round == null) {
            chat.sendErrorMessage(sender, "No round was started yet!");
            return;
        }
        chat.sendBoughtTickets(sender, round);
    }

}

