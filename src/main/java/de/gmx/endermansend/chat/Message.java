package de.gmx.endermansend.chat;

import de.gmx.endermansend.game.Ticket;
import de.gmx.endermansend.interfaces.RoundInterface;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Implements the allowed I/O-methods
 */
public class Message implements MessageInterface {

    private ChatHandler chat;
    private HashMap<String, String> messages;
    private HashMap<String, String> adminMessages;
    private Logger logger;

    protected Message(ChatHandler chat) {
        this.chat = chat;
        this.messages = chat.messages;
        this.adminMessages = chat.adminMessages;
        this.logger = chat.logger;

        chat.pluginTag = ChatColor.GOLD + "[Simple Lottery]" + ChatColor.RESET;
        chat.errorTag = ChatColor.RED + "[Simple Lottery]" + ChatColor.RESET;
        chat.listTag = "                     -";

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
        chat.broadcastMessage(msg);
    }

    public void broadcastRoundEnd(int roundNumber) {
        String msg = messages.get("round.end").replace("<<roundNumber>>", "" + roundNumber);
        chat.broadcastMessage(msg);
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

        String msg = messages.get("round.status".replace("<<roundNumber>>", "" + round.getRoundNumber()).replace("<<status>>", statusText));
        chat.broadcastMessage(msg);

    }

    public void broadcastWinners(int roundNumber, int winningNumber, Collection<String> winners) {

        broadcastRoundEnd(roundNumber);
        String msg = messages.get("round.winningNumber").replace("<<winningNumber>>", "" + winningNumber);
        chat.broadcastMessage(msg);

        if (!winners.isEmpty()) {

            if (winners.size() == 1) {
                chat.broadcastMessage(messages.get("round.winners") + " " + winners.toArray()[0]);
            } else {
                chat.broadcastMessage(messages.get("round.winners"));
                for (String player : winners)
                    chat.broadcastListEntry(player);
            }
        } else {
            chat.broadcastMessage(messages.get("round.noWinners"));
        }

    }

    public void broadcastBoughtTickets(RoundInterface round) {

        ArrayList<Ticket> tickets = (ArrayList<Ticket>) round.getTickets();
        chat.broadcastMessage(messages.get("ticket.list"));
        for (Ticket ticket : tickets)
            chat.broadcastListEntry(ticket.toString());
    }

    // Private messages
    public void sendOnlyPlayersCanDoThat(CommandSender sender) {
        chat.sendErrorMessage(sender, adminMessages.get("error.noPlayer"));
    }

    public void sendPermissionError(CommandSender sender) {
        chat.sendErrorMessage(sender, messages.get("permissionError"));
    }

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
        chat.sendMessage(receiver, msg);
    }

    public void sendTicketBought(Player receiver, int ticketNumber) {
        String msg = messages.get("ticket.bought").replace("<<ticketNumber>>", "" + ticketNumber);
        chat.sendMessage(receiver, msg);
    }

    public void sendTicketFailure(Player receiver, int ticketNumber) {
        String msg = messages.get("ticket.error.general").replace("<<ticketNumber>>", "" + ticketNumber);
        chat.sendErrorMessage(receiver, msg);
    }

    public void sendAlreadyExists(Player receiver, int ticketNumber) {
        String msg = messages.get("ticket.error.alreadyExists").replace("<<ticketNumber>>", "" + ticketNumber);
        chat.sendErrorMessage(receiver, msg);
    }

    public void sendTooManyTickets(Player receiver) {
        chat.sendErrorMessage(receiver, messages.get("ticket.error.tooMany"));
    }

    public void sendRoundOver(Player receiver) {
        chat.sendErrorMessage(receiver, messages.get("ticket.error.roundOver"));
    }

    public void sendToPoor(Player receiver, ItemStack bet) {
        String msg = messages.get("ticket.error.tooPoor").replace("<<amount>>", "" + bet.getAmount()).replace("<<material>>", bet.getType().name());
        chat.sendErrorMessage(receiver, msg);
    }

    public void sendRewardError(Player receiver) {
        chat.sendErrorMessage(receiver, messages.get("ticket.rewardFailure"));
    }

    public void sendBoughtTickets(CommandSender sender, RoundInterface round) {
        ArrayList<Ticket> tickets = (ArrayList<Ticket>) round.getTickets();
        chat.sendMessage(sender, messages.get("ticket.list"));
        for (Ticket ticket : tickets)
            chat.sendListEntry(sender, ticket.toString());
    }

    public void sendRoundNotRunning(CommandSender receiver, RoundInterface round) {
        String msg = chat.adminMessages.get("round.notRunning").replace("<<roundNumber>>", "" + round.getRoundNumber());
        receiver.sendMessage(msg);
    }

    public void sendRoundNotStarted(CommandSender receiver) {
        receiver.sendMessage(chat.adminMessages.get("round.notStarted"));
    }

    public void sendRoundNotFinished(CommandSender receiver, RoundInterface round) {
        String msg = chat.adminMessages.get("round.notFinished").replace("<<roundNumber>>", "" + round.getRoundNumber());
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
