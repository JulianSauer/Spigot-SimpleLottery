package de.gmx.endermansend.chat;

import de.gmx.endermansend.game.RoundInterface;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public interface MessageInterface {

    // Public messages
    void broadcastRoundStart(int roundNumber);

    void broadcastRoundEnd(int roundNumber);

    /**
     * Displays the status of the current/last round.
     *
     * @param round Interface of the round object which's status should be displayed
     */
    void broadcastStatus(RoundInterface round);

    /**
     * Displays the winning number as well as every player that has bought a ticket in the round with that number. If
     * nobody won, an alternative message will be displayed instead.
     *
     * @param roundNumber   Number of the finished round
     * @param winningNumber Number that won the lottery
     * @param winners       Collection of winners
     */
    void broadcastWinners(int roundNumber, int winningNumber, Collection<String> winners);

    /**
     * Displays all players, that bought tickets as well as their numbers.
     *
     * @param round Round object containing the ticket list
     */
    void broadcastBoughtTickets(RoundInterface round);

    // Private messages
    void sendOnlyPlayersCanDoThat(CommandSender sender);

    void sendPermissionError(CommandSender sender);

    /**
     * Sends the status of the current/last round.
     *
     * @param receiver Initiator of the command
     * @param round    Interface of the round object which's status should be displayed
     */
    void sendStatus(CommandSender receiver, RoundInterface round);

    void sendTicketBought(Player receiver, int ticketNumber);

    void sendTicketFailure(Player receiver, int ticketNumber);

    void sendAlreadyExists(Player receiver, int ticketNumber);

    void sendTooManyTickets(Player receiver);

    void sendRoundOver(Player receiver);

    void sendToPoor(Player receiver, ItemStack bet);

    void sendRewardError(Player receiver);

    /**
     * Shows the sender all players, that bought tickets as well as their numbers.
     *
     * @param sender Initiator of the command
     * @param round  Round object containing the ticket list
     */
    void sendBoughtTickets(CommandSender sender, RoundInterface round);

    void sendRoundNotRunning(CommandSender receiver, RoundInterface round);

    void sendRoundNotStarted(CommandSender receiver);

    void sendRoundNotFinished(CommandSender receiver, RoundInterface round);

    // Log messages
    void logPluginEnabled();

    void logPluginDisabled();

}
