package de.gmx.endermansend.main;

import de.gmx.endermansend.chat.ChatHandler;
import de.gmx.endermansend.game.LotteryCoordinatorInterface;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Evaluates /lottery commands
 */
public class SimpleLotteryCommandExecutor implements CommandExecutor {

    private LotteryCoordinatorInterface lottery;
    private ChatHandler chat;

    public SimpleLotteryCommandExecutor(SimpleLottery main) {
        this.lottery = main.getLottery();
        this.chat = main.getChat();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("lottery")) {

            if (args.length == 0) {
                // Command sendHelp
                chat.sendHelp.general(sender);
                return true;
            } else if (args.length == 1) {

                // Commands
                if (args[0].equalsIgnoreCase("start"))
                    return start(sender);
                else if (args[0].equalsIgnoreCase("stop"))
                    return stop(sender);
                else if (args[0].equalsIgnoreCase("halt"))
                    return halt(sender);
                else if (args[0].equalsIgnoreCase("resume"))
                    return resume(sender);
                else if (args[0].equalsIgnoreCase("status"))
                    return status(sender);
                else if (args[0].equalsIgnoreCase("list"))
                    return listTicketsPrivate(sender);
                else if (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?")) {
                    chat.sendHelp.help(sender);
                    return true;
                } else if (args[0].equalsIgnoreCase("allowedItems")) {
                    chat.send.allowedItems(sender);
                    return true;
                }

            } else if (args.length == 2) {

                // Command sendHelp
                if (args[1].equalsIgnoreCase("help") || args[1].equalsIgnoreCase("?")) {

                    if (args[0].equalsIgnoreCase("start")) {
                        chat.sendHelp.start(sender);
                        return true;
                    } else if (args[0].equalsIgnoreCase("stop")) {
                        chat.sendHelp.stop(sender);
                        return true;
                    } else if (args[0].equalsIgnoreCase("halt")) {
                        chat.sendHelp.halt(sender);
                        return true;
                    } else if (args[0].equalsIgnoreCase("resume")) {
                        chat.sendHelp.resume(sender);
                        return true;
                    } else if (args[0].equalsIgnoreCase("status")) {
                        chat.sendHelp.status(sender);
                        return true;
                    } else if (args[0].equalsIgnoreCase("list")) {
                        chat.sendHelp.list(sender);
                        return true;
                    } else if (args[0].equalsIgnoreCase("allowedItems")) {
                        chat.sendHelp.allowedItems(sender);
                        return true;
                    } else if (args[0].equalsIgnoreCase("buy")) {
                        chat.sendHelp.buy(sender);
                        return true;
                    }


                }

                // Commands
                else if (args[0].equalsIgnoreCase("list"))
                    if (args[1].equalsIgnoreCase("public"))
                        return listTicketsPublic(sender);
                    else if (args[1].equalsIgnoreCase("private"))
                        return listTicketsPrivate(sender);

            } else if (args.length == 3) {

                // Command sendHelp
                if (args[2].equalsIgnoreCase("help") || args[2].equalsIgnoreCase("?")) {

                    if (args[0].equalsIgnoreCase("list")) {
                        if (args[1].equalsIgnoreCase("public")) {
                            chat.sendHelp.listPublic(sender);
                            return true;
                        } else if (args[1].equalsIgnoreCase("private")) {
                            chat.sendHelp.listPrivate(sender);
                            return true;
                        }
                    }

                }

            } else if (args.length == 4) {

                // Commands
                if (args[0].equalsIgnoreCase("buy"))
                    return buyTicket(sender, args);

            }

        }

        return false;
    }

    /**
     * Tries to start a new round.
     * Requires permission 'Control'
     *
     * @param sender Initiator of the command
     * @return Always returns true because Bukkit's command handling is retarded for commands with arguments
     */
    private boolean start(CommandSender sender) {
        if (sender.hasPermission("SimpleLottery.Control")) {
            lottery.startNewGame(sender);
        } else {
            chat.send.permissionError(sender);
        }
        return true;
    }

    /**
     * Tries to halt the current round.
     *
     * @param sender Initiator of the command
     * @return Always returns true because Bukkit's command handling is retarded for commands with arguments
     */
    private boolean halt(CommandSender sender) {

        if (sender.hasPermission("SimpleLottery.Control"))
            lottery.haltGame(sender);
        else
            chat.send.permissionError(sender);

        return true;
    }

    private boolean resume(CommandSender sender) {

        if (sender.hasPermission("SimpleLottery.Control"))
            lottery.resume(sender);
        else
            chat.send.permissionError(sender);
        return true;
    }

    /**
     * Prints the status of the current round
     *
     * @param sender Initiator of the command
     * @return Always returns true because Bukkit's command handling is retarded for commands with arguments
     */
    private boolean status(CommandSender sender) {
        if (sender.hasPermission("SimpleLottery.Round.Status"))
            lottery.showStatus(sender);
        else
            chat.send.permissionError(sender);
        return true;
    }

    /**
     * Tries to stop the current round.
     * Requires permission 'Control'
     *
     * @param sender Initiator of the command
     * @return Always returns true because Bukkit's command handling is retarded for commands with arguments
     */
    private boolean stop(CommandSender sender) {
        if (sender.hasPermission("SimpleLottery.Control"))
            lottery.finishGame(sender);
        else
            chat.send.permissionError(sender);
        return true;
    }

    /**
     * Sends a list of bought tickets with player names and ticket numbers to initiator.
     * Requires permission 'Round.ListTickets.Private'
     *
     * @param sender Initiator of the command
     * @return Always returns true because Bukkit's command handling is retarded for commands with arguments
     */
    private boolean listTicketsPrivate(CommandSender sender) {
        if (sender.hasPermission("SimpleLottery.Round.ListTickets.Private"))
            lottery.listTicketsPrivate(sender);
        else
            chat.send.permissionError(sender);
        return true;
    }

    /**
     * Broadcasts a list of bought tickets with player names and ticket numbers.
     * Requires permission 'Round.ListTickets.Public'
     *
     * @param sender Initiator of the command
     * @return Always returns true because Bukkit's command handling is retarded for commands with arguments
     */
    private boolean listTicketsPublic(CommandSender sender) {
        if (sender.hasPermission("SimpleLottery.Round.ListTickets.Public"))
            lottery.listTicketsPublic(sender);
        else
            chat.send.permissionError(sender);
        return true;
    }

    /**
     * Lets the sender buy a ticket.
     * Requires permission 'Round.BuyTickets'
     *
     * @param sender Initiator will receive an error if he/she isn't a player
     * @param args   {"buy", "<ticketNumber>", "<amount>", "<material>"}
     * @return Always returns true because Bukkit's command handling is retarded for commands with arguments
     */
    private boolean buyTicket(CommandSender sender, String[] args) {

        if (sender instanceof Player) {

            if (sender.hasPermission("SimpleLottery.Round.BuyTickets")) {
                Player player = (Player) sender;

                int amount;
                int ticketNumber;
                try {
                    ticketNumber = Integer.parseInt(args[1]);
                    amount = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    chat.sendHelp.buy(sender);
                    return true;
                }
                String material = args[3];

                lottery.addPlayer(player, ticketNumber, material, amount);
            } else
                chat.send.permissionError(sender);

        } else {
            chat.send.onlyPlayersCanDoThatError(sender);
        }
        return true;

    }

}
