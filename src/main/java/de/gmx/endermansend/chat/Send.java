package de.gmx.endermansend.chat;

import de.gmx.endermansend.game.RoundInterface;
import de.gmx.endermansend.game.Ticket;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Implements methods for sending info messages to players.
 */
public class Send {

    private ChatHandler chat;
    private HashMap<String, String> messages;
    private HashMap<String, String> adminMessages;

    protected Send(ChatHandler chat) {
        this.chat = chat;
        this.messages = chat.messages;
        this.adminMessages = chat.adminMessages;
    }

    public void onlyPlayersCanDoThatError(CommandSender sender) {
        chat.sendErrorMessage(sender, adminMessages.get("error.noPlayer"));
    }

    public void permissionError(CommandSender sender) {
        chat.sendErrorMessage(sender, messages.get("permissionError"));
    }

    /**
     * Sends the status of the current/last round.
     *
     * @param receiver Initiator of the command
     * @param round    Interface of the round object which's status should be displayed
     */
    public void status(CommandSender receiver, RoundInterface round) {
        chat.sendMessage(receiver, chat.getRoundStatus(round));
    }

    public void roundStatusError(CommandSender receiver, RoundInterface round) {
        chat.sendErrorMessage(receiver, chat.getRoundStatus(round));
    }

    public void ticketBought(Player receiver, int ticketNumber) {
        String msg = messages.get("ticket.bought").replace("<<ticketNumber>>", "" + ticketNumber);
        chat.sendMessage(receiver, msg);
    }

    public void ticketError(Player receiver) {
        String msg = messages.get("ticket.error.general");
        chat.sendErrorMessage(receiver, msg);
    }

    public void wrongNumberRangeError(Player receiver, int ticketNumber, int min, int max) {
        String msg = messages.get("ticket.error.wrongNumberRange").replace("<<ticketNumber>>", "" + ticketNumber).replace("<<min>>", "" + min).replace("<<max>>", "" + max);
        chat.sendErrorMessage(receiver, msg);
    }

    public void alreadyExistsError(Player receiver, int ticketNumber) {
        String msg = messages.get("ticket.error.alreadyExists").replace("<<ticketNumber>>", "" + ticketNumber);
        chat.sendErrorMessage(receiver, msg);
    }

    public void tooManyTicketsError(Player receiver) {
        chat.sendErrorMessage(receiver, messages.get("ticket.error.tooMany"));
    }

    public void tooPoorError(Player receiver, ItemStack bet) {
        String msg = messages.get("ticket.error.tooPoor").replace("<<amount>>", "" + bet.getAmount()).replace("<<material>>", bet.getType().name());
        chat.sendErrorMessage(receiver, msg);
    }

    public void rewardError(Player receiver) {
        chat.sendErrorMessage(receiver, messages.get("ticket.rewardFailure"));
    }

    /**
     * Shows the sender all players, that bought tickets as well as their numbers.
     *
     * @param receiver Initiator of the command
     * @param round    Round object containing the ticket list
     */
    public void boughtTickets(CommandSender receiver, RoundInterface round) {
        ArrayList<Ticket> tickets = (ArrayList<Ticket>) round.getTickets();
        chat.sendMessage(receiver, messages.get("ticket.list"));
        for (Ticket ticket : tickets)
            chat.sendListEntry(receiver, ticket.toString());
    }

    public void allowedItems(CommandSender receiver) {
        List<String> allowedMaterials = chat.config.get.allowedMaterials();
        for (String material : allowedMaterials)
            chat.sendMessage(receiver, material);
    }

}
