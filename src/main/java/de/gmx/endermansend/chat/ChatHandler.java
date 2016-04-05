package de.gmx.endermansend.chat;

import de.gmx.endermansend.main.SimpleLottery;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Should be used for chat interaction to keep message formatting consistent. Defines some basic I/O-methods. More
 * specific methods are defined in a separate message class.
 */
public class ChatHandler {

    public MessageInterface message;

    protected String pluginTag;

    protected String errorTag;

    protected String listTag;

    protected HashMap<String, String> messages;

    protected HashMap<String, String> adminMessages;

    protected Logger logger;

    public ChatHandler(SimpleLottery main) {

        logger = main.getLogger();
        messages = main.getConfigHandler().getMessages();
        adminMessages = new HashMap<String, String>();
        message = new Message(this);

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

}
