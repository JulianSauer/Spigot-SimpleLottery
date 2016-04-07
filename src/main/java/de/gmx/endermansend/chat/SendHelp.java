package de.gmx.endermansend.chat;

import org.bukkit.command.CommandSender;

import java.util.HashMap;

/**
 * Prints the sendHelp for commands.
 */
public class SendHelp {

    private ChatHandler chat;

    HashMap<String, String[]> messages;

    protected SendHelp(ChatHandler chat) {

        this.chat = chat;

        messages = new HashMap<String, String[]>();

        messages.put("general",
                new String[]{
                        "All commands for SimpleLottery",
                        "Usage: /lottery <arguments>",
                        "Arguments: start, stop, halt, resume, list, sendHelp, buy, allowedItems"
                });
        messages.put("start",
                new String[]{
                        "Starts a new round if no round is currently active",
                        "Usage: /lottery start"
                });
        messages.put("stop",
                new String[]{
                        "Stops the currently active round",
                        "Usage: /lottery stop"
                });
        messages.put("halt",
                new String[]{
                        "Halts the currently active round",
                        "Usage: /lottery halt"
                });
        messages.put("resume",
                new String[]{
                        "Resumes the currently halted round",
                        "Usage: /lottery resume"
                });
        messages.put("status",
                new String[]{
                        "Prints the status of the current round",
                        "Usage: /lottery status"
                });
        messages.put("list",
                new String[]{
                        "Broadcasts all bought tickets of the current round",
                        "Usage: /lottery list <arguments>",
                        "Arguments: public, private"
                });
        messages.put("listPublic",
                new String[]{
                        "Broadcasts all bought tickets of the current round",
                        "Usage: /lottery list public"
                });
        messages.put("listPrivate",
                new String[]{
                        "Shows you all bought tickets of the current round",
                        "/lottery list private"
                });
        messages.put("sendHelp",
                new String[]{
                        "Prints sendHelp",
                        "Usage: /lottery sendHelp"
                });
        messages.put("buy",
                new String[]{
                        "Buys a new ticket with the specified ticket number for the amount of items",
                        "Usage: /lottery buy <ticketNumber> <amount> <item>"
                });
        messages.put("allowedItems",
                new String[]{
                        "Prints the allowed items when buying a ticket",
                        "Usage: /lottery allowedItems"
                });

    }

    public void general(CommandSender sender) {
        printHelpFor(sender, "general");
    }

    public void start(CommandSender sender) {
        printHelpFor(sender, "start");
    }

    public void stop(CommandSender sender) {
        printHelpFor(sender, "stop");
    }

    public void halt(CommandSender sender) {
        printHelpFor(sender, "halt");
    }

    public void resume(CommandSender sender) {
        printHelpFor(sender, "resume");
    }

    public void status(CommandSender sender) {
        printHelpFor(sender, "status");
    }

    public void list(CommandSender sender) {
        printHelpFor(sender, "list");
    }

    public void listPublic(CommandSender sender) {
        printHelpFor(sender, "listPublic");
    }

    public void listPrivate(CommandSender sender) {
        printHelpFor(sender, "listPrivate");
    }

    public void help(CommandSender sender) {
        printHelpFor(sender, "sendHelp");
    }

    public void buy(CommandSender sender) {
        printHelpFor(sender, "buy");
    }

    public void allowedItems(CommandSender sender) {
        printHelpFor(sender, "allowedItems");
    }

    private void printHelpFor(CommandSender sender, String key) {
        for (String s : messages.get(key))
            chat.sendMessage(sender, s);
    }

}
