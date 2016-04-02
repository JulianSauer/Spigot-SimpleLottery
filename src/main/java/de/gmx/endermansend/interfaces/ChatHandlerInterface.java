package de.gmx.endermansend.interfaces;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;

public abstract class ChatHandlerInterface {

    protected String pluginTag;

    protected String errorTag;

    protected String noTag;

    protected HashMap<String, String> messages;

    public ChatHandlerInterface(ConfigHandlerInterface config) {
        messages = config.getMessages();
    }

    public void broadcastMessage(String msg) {
        Bukkit.broadcastMessage(pluginTag + " " + msg);
    }

    public void broadcastUntaggedMessage(String msg) {
        Bukkit.broadcastMessage(noTag + " " + msg);
    }

    public void sendMessage(CommandSender receiver, String msg) {
        receiver.sendMessage(pluginTag + " " + msg);
    }

    public void sendErrorMessage(CommandSender receiver, String msg) {
        receiver.sendMessage(errorTag + " " + msg);
    }

    public abstract void broadcastRoundStart(int roundNumber);

    public abstract void broadcastRoundEnd(int roundNumber);

    public abstract void broadcastStatus(RoundInterface round);

    public abstract void broadcastWinners(int roundNumber, int winningNumber, Collection<String> winners);

    public abstract void sendTicketBought(Player receiver, int luckyNumber);

    public abstract void sendTicketFailure(Player receiver, int luckyNumber);

}
