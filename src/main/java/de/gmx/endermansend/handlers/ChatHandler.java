package de.gmx.endermansend.handlers;

import de.gmx.endermansend.game.Ticket;
import de.gmx.endermansend.interfaces.ChatHandlerInterface;
import de.gmx.endermansend.interfaces.ConfigHandlerInterface;
import de.gmx.endermansend.interfaces.RoundInterface;
import de.gmx.endermansend.main.SimpleLottery;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Implements the messages that are displayed via the ChatHandlerInterface. Therefore messages from config.yml are used.
 */
public class ChatHandler extends ChatHandlerInterface {

    public ChatHandler(SimpleLottery main) {

        super(main);

        pluginTag = ChatColor.GOLD + "[Simple Lottery]" + ChatColor.RESET;
        errorTag = ChatColor.RED + "[Simple Lottery]" + ChatColor.RESET;
        listTag = "                     -";

        adminMessages.put("plugin.enabled", "SimpleLottery enabled");
        adminMessages.put("plugin.disabled", "SimpleLottery disabled");
        adminMessages.put("round.notRunning", "Game #<<roundNumber>> has already stopped.");
        adminMessages.put("round.notStarted", "No round has started yet!");
        adminMessages.put("round.notFinished", "Can't start new game. Round #<<roundNumber>> is not over!");
        adminMessages.put("error.noPlayer", "Only players can perform this command.");

    }

    // Public messages
    public void broadcastRoundStart(int roundNumber) {
        String msg = messages.get("round.start").replace("<<roundNumber>>", "" + roundNumber);
        broadcastMessage(msg);
    }

    public void broadcastRoundEnd(int roundNumber) {
        String msg = messages.get("round.end").replace("<<roundNumber>>", "" + roundNumber);
        broadcastMessage(msg);
    }

    /**
     * Displays the status of the current/last round.
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

        String msg = messages.get("round.status".replace("<<roundNumber>>", "" + round.getRoundNumber()).replace("<<status>>", statusText));
        broadcastMessage(msg);

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
        String msg = messages.get("round.winningNumber").replace("<<winningNumber>>", "" + winningNumber);
        broadcastMessage(msg);

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
        broadcastMessage(messages.get("ticket.list"));
        for (Ticket ticket : tickets)
            broadcastListEntry(ticket.toString());
    }

    // Private messages
    public void sendOnlyPlayersCanDoThat(CommandSender sender) {
        sendErrorMessage(sender, adminMessages.get("error.noPlayer"));
    }

    public void sendPermissionError(CommandSender sender) {
        sendErrorMessage(sender, messages.get("permissionError"));
    }

    /**
     * Sends the status of the current/last round.
     *
     * @param receiver Initiator of the command
     * @param round    Interface of the round object which's status should be displayed
     */
    public void sendStatus(CommandSender receiver, RoundInterface round) {
        String statusText;
        RoundInterface.Status status = round.getStatus();

        if (status == RoundInterface.Status.GAME_IS_RUNNING) {
            statusText = messages.get("round.status.running");
        } else if (status == RoundInterface.Status.GAME_IS_RUNNING) {
            statusText = messages.get("round.status.halted");
        } else {
            statusText = messages.get("round.status.ended");
        }

        String msg = messages.get("round.status".replace("<<roundNumber>>", "" + round.getRoundNumber()).replace("<<status>>", statusText));
        sendMessage(receiver, msg);
    }

    public void sendTicketBought(Player receiver, int ticketNumber) {
        String msg = messages.get("ticket.bought").replace("<<ticketNumber>>", "" + ticketNumber);
        sendMessage(receiver, msg);
    }

    public void sendTicketFailure(Player receiver, int ticketNumber) {
        String msg = messages.get("ticket.error.general").replace("<<ticketNumber>>", "" + ticketNumber);
        sendErrorMessage(receiver, msg);
    }

    public void sendAlreadyExists(Player receiver, int ticketNumber) {
        String msg = messages.get("ticket.error.alreadyExists").replace("<<ticketNumber>>", "" + ticketNumber);
        sendErrorMessage(receiver, msg);
    }

    public void sendTooManyTickets(Player receiver) {
        sendErrorMessage(receiver, messages.get("ticket.error.tooMany"));
    }

    public void sendRoundOver(Player receiver) {
        sendErrorMessage(receiver, messages.get("ticket.error.roundOver"));
    }

    public void sendToPoor(Player receiver, ItemStack bet) {
        String msg = messages.get("ticket.error.tooPoor").replace("<<amount>>", "" + bet.getAmount()).replace("<<material>>", bet.getType().name());
        sendErrorMessage(receiver, msg);
    }

    public void sendRewardError(Player receiver) {
        sendErrorMessage(receiver, messages.get("ticket.rewardFailure"));
    }

    /**
     * Shows the sender all players, that bought tickets as well as their numbers.
     *
     * @param sender Initiator of the command
     * @param round  Round object containing the ticket list
     */
    public void sendBoughtTickets(CommandSender sender, RoundInterface round) {
        ArrayList<Ticket> tickets = (ArrayList<Ticket>) round.getTickets();
        sendMessage(sender, messages.get("ticket.list"));
        for (Ticket ticket : tickets)
            sendListEntry(sender, ticket.toString());
    }

    public void sendRoundNotRunning(CommandSender receiver, RoundInterface round) {
        String msg = adminMessages.get("round.notRunning").replace("<<roundNumber>>", "" + round.getRoundNumber());
        receiver.sendMessage(msg);
    }

    public void sendRoundNotStarted(CommandSender receiver) {
        receiver.sendMessage(adminMessages.get("round.notStarted"));
    }

    public void sendRoundNotFinished(CommandSender receiver, RoundInterface round) {
        String msg = adminMessages.get("round.notFinished").replace("<<roundNumber>>", "" + round.getRoundNumber());
        receiver.sendMessage(msg);
    }

    // Log messages
    public void logPluginEnabled() {
        logger.info(adminMessages.get("plugin.enabled"));
    }

    public void logPluginDisabled() {
        logger.info(adminMessages.get("plugin.disabled"));
    }

}