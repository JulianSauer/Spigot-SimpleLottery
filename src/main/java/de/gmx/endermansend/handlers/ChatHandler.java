package de.gmx.endermansend.handlers;

import de.gmx.endermansend.game.Ticket;
import de.gmx.endermansend.interfaces.ChatHandlerInterface;
import de.gmx.endermansend.interfaces.ConfigHandlerInterface;
import de.gmx.endermansend.interfaces.RoundInterface;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Implements the messages that are displayed via the ChatHandlerInterface. Therefore messages from config.yml are used.
 */
public class ChatHandler extends ChatHandlerInterface {

    public ChatHandler(ConfigHandlerInterface config) {

        super(config);

        pluginTag = ChatColor.GOLD + "[Simple Lottery]" + ChatColor.RESET;
        errorTag = ChatColor.RED + "[Simple Lottery]" + ChatColor.RESET;
        listTag = "                     -";

    }

    public void broadcastRoundStart(int roundNumber) {
        broadcastMessage(messages.get("round.start").replace("<<roundNumber>>", "" + roundNumber));
    }

    public void broadcastRoundEnd(int roundNumber) {
        broadcastMessage(messages.get("round.end").replace("<<roundNumber>>", "" + roundNumber));
    }

    /**
     * Displays the status of a round object.
     *
     * @param round Interface of the round object which's status should be displayed
     */
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

    /**
     * Displays the winning number as well as every player that has bought a ticket in the round with that number. If
     * nobody won, an alternative message will be displayed instead.
     *
     * @param roundNumber   Number of the finished round
     * @param winningNumber Number that won the lottery
     * @param winners       Collection of winners
     */
    public void broadcastWinners(int roundNumber, int winningNumber, Collection<String> winners) {

        broadcastRoundEnd(roundNumber);
        broadcastMessage(messages.get("round.winningNumber").replace("<<winningNumber>>", "" + winningNumber));

        if (!winners.isEmpty()) {

            if (winners.size() == 1) {
                broadcastMessage(messages.get("round.winners") + " " + winners.toArray()[0]);
            } else {
                broadcastMessage(messages.get("round.winners"));
                for (String player : winners)
                    broadcastListEntry(player);
            }
        } else {
            broadcastMessage(messages.get("round.noWinners"));
        }

    }

    /**
     * Displays all players, that bought tickets as well as their numbers.
     *
     * @param round Round object containing the ticket list
     */
    public void broadcastBoughtTickets(RoundInterface round) {

        ArrayList<Ticket> tickets = (ArrayList<Ticket>) round.getTickets();
        broadcastMessage("Bought tickets:");
        for (Ticket ticket : tickets)
            broadcastListEntry(ticket.getOwner() + ": " + ticket.getTicketNumber());
    }

    public void sendListEntry(CommandSender sender, String msg) {
        // TODO: idk why there's no implementation here
    }

    public void sendHaveToBePlayerError(CommandSender sender) {
        sendErrorMessage(sender, "Only players can perform this command.");
    }

    public void sendPermissionError(CommandSender sender) {
        sendErrorMessage(sender, "You do not have permission to perform this command.");
    }

    public void sendTicketBought(Player receiver, int ticketNumber) {
        sendMessage(receiver, messages.get("ticket.bought").replace("<<ticketNumber>>", "" + ticketNumber));
    }

    public void sendTicketFailure(Player receiver, int ticketNumber) {
        sendMessage(receiver, messages.get("ticket.failure").replace("<<ticketNumber>>", "" + ticketNumber));
    }

    /**
     * Shows the sender all players, that bought tickets as well as their numbers.
     *
     * @param sender Initiator of the command
     * @param round  Round object containing the ticket list
     */
    public void sendBoughtTickets(CommandSender sender, RoundInterface round) {
        ArrayList<Ticket> tickets = (ArrayList<Ticket>) round.getTickets();
        sendMessage(sender, "Bought tickets:");
        for (Ticket ticket : tickets)
            sendListEntry(sender, ticket.getOwner() + ": " + ticket.getTicketNumber());
    }

    public void sendRewardError(Player receiver) {
        sendErrorMessage(receiver, messages.get("ticket.rewardFailure"));
    }

}
