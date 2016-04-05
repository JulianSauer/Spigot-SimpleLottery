package de.gmx.endermansend.interfaces;

import de.gmx.endermansend.main.SimpleLottery;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Should be used for chat interaction to keep message formatting consistent.
 */
public abstract class ChatHandlerInterface {

    protected String pluginTag;

    protected String errorTag;

    protected String listTag;

    protected HashMap<String, String> messages;

    protected HashMap<String, String> adminMessages;

    protected Logger logger;

    protected ChatHandlerInterface(SimpleLottery main) {
        messages = main.getConfigHandler().getMessages();
        adminMessages = new HashMap<String, String>();

        logger = main.getLogger();
    }

    protected void broadcastMessage(String msg) {
        Bukkit.broadcastMessage(pluginTag + " " + msg);
    }

    protected void broadcastListEntry(String msg) {
        Bukkit.broadcastMessage(listTag + " " + msg);
    }

    protected void sendMessage(CommandSender receiver, String msg) {
        receiver.sendMessage(pluginTag + " " + msg);
    }

    protected void sendErrorMessage(CommandSender receiver, String msg) {
        receiver.sendMessage(errorTag + " " + msg);
    }

    protected void sendListEntry(CommandSender receiver, String msg) {
        receiver.sendMessage(listTag + " " + msg);
    }

    public abstract void broadcastRoundStart(int roundNumber);

    public abstract void broadcastRoundEnd(int roundNumber);

    public abstract void broadcastStatus(RoundInterface round);

    public abstract void broadcastWinners(int roundNumber, int winningNumber, Collection<String> winners);

    public abstract void broadcastBoughtTickets(RoundInterface round);

    public abstract void sendOnlyPlayersCanDoThat(CommandSender receiver);

    public abstract void sendPermissionError(CommandSender receiver);

    public abstract void sendStatus(CommandSender receiver, RoundInterface round);

    public abstract void sendTicketBought(Player receiver, int ticketNumber);

    public abstract void sendTicketFailure(Player receiver, int ticketNumber);

    public abstract void sendAlreadyExists(Player receiver, int ticketNumber);

    public abstract void sendTooManyTickets(Player receiver);

    public abstract void sendRoundOver(Player receiver);

    public abstract void sendToPoor(Player receiver, ItemStack bet);

    public abstract void sendRewardError(Player receiver);

    public abstract void sendBoughtTickets(CommandSender receiver, RoundInterface round);

    public abstract void sendRoundNotRunning(CommandSender receiver, RoundInterface round);

    public abstract void sendRoundNotStarted(CommandSender receiver);

    public abstract void sendRoundNotFinished(CommandSender receiver, RoundInterface round);

    public abstract void logPluginEnabled();

    public abstract void logPluginDisabled();

}
