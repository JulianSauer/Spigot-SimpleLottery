package de.gmx.endermansend.handlers;

import de.gmx.endermansend.game.Ticket;
import de.gmx.endermansend.interfaces.ChatHandlerInterface;
import de.gmx.endermansend.interfaces.ConfigHandlerInterface;
import de.gmx.endermansend.interfaces.RoundInterface;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;

public class ChatHandler extends ChatHandlerInterface {

    public ChatHandler(ConfigHandlerInterface config) {

        super(config);

        pluginTag = ChatColor.GOLD + "[Simple Lottery]" + ChatColor.RESET;
        errorTag = ChatColor.RED + "[Simple Lottery]" + ChatColor.RESET;
        noTag = "                     - ";

    }

    public void broadcastRoundStart(int roundNumber) {
        broadcastMessage(messages.get("round.start").replace("<<roundNumber>>", "" + roundNumber));
    }

    public void broadcastRoundEnd(int roundNumber) {
        broadcastMessage(messages.get("round.end").replace("<<roundNumber>>", "" + roundNumber));
    }

    public void broadcastStatus(RoundInterface round) {

        String statusText;
        RoundInterface.Status status = round.getStatus();

        if (status == RoundInterface.Status.GAME_IS_RUNNING) {
            statusText = messages.get("round.status.running");
        } else if (status == RoundInterface.Status.GAME_IS_RUNNING) {
            statusText = messages.get("round.status.halted");
        } else {
            statusText = messages.get("round.status.ended");
        }

        broadcastMessage(messages.get("round.status".replace("<<roundNumber>>", "" + round.getRoundNumber()).replace("<<status>>", statusText)));

    }

    public void broadcastWinners(int roundNumber, int winningNumber, Collection<String> winners) {

        broadcastRoundEnd(roundNumber);
        broadcastMessage(messages.get("round.winningNumber").replace("<<winningNumber>>", "" + winningNumber));

        if (!winners.isEmpty()) {

            if (winners.size() == 1) {
                broadcastMessage(messages.get("round.winners") + " " + winners.toArray()[0]);
            } else {
                broadcastMessage(messages.get("round.winners"));
                for (String player : winners)
                    broadcastUntaggedMessage(player);
            }
        } else {
            broadcastMessage(messages.get("round.noWinners"));
        }

    }

    public void broadcastPlayersOfRound(RoundInterface round) {

        ArrayList<Ticket> tickets = (ArrayList<Ticket>) round.getTickets();
        broadcastMessage("Bought tickets:");
        for(Ticket ticket : tickets)
            broadcastUntaggedMessage(ticket.getOwner() + ": " + ticket.getLuckyNumber());
    }

    public void sendTicketBought(Player receiver, int luckyNumber) {
        sendMessage(receiver, messages.get("ticket.bought").replace("<<luckyNumber>>", "" + luckyNumber));
    }

    public void sendTicketFailure(Player receiver, int luckyNumber) {
        sendMessage(receiver, messages.get("ticket.failure").replace("<<luckyNumber>>", "" + luckyNumber));
    }

}
