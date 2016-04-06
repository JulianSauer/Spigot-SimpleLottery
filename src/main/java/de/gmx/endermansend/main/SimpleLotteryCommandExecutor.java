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

            if (args.length == 1) {

                if (args[0].equalsIgnoreCase("start"))
                    return start(sender);
                else if (args[0].equalsIgnoreCase("stop"))
                    return stop(sender);
                else if (args[0].equalsIgnoreCase("list"))
                    return listTicketsPrivate(sender);
                // TODO: Add command to pause the game

            } else if (args.length == 2) {

                if (args[0].equalsIgnoreCase("list"))
                    if (args[1].equalsIgnoreCase("public"))
                        return listTicketsPublic(sender);
                    else if (args[1].equalsIgnoreCase("private"))
                        return listTicketsPrivate(sender);

            } else if (args.length == 4) {

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
     * @param args   {"buy", "<ticketNumber>", "<material>", "<amount>"}
     * @return Always returns true because Bukkit's command handling is retarded for commands with arguments
     */
    private boolean buyTicket(CommandSender sender, String[] args) {

        if (sender instanceof Player) {

            if (sender.hasPermission("SimpleLottery.Round.BuyTickets")) {
                Player player = (Player) sender;
                int ticketNumber = Integer.parseInt(args[1]);
                String material = args[2];
                int amount = Integer.parseInt(args[3]);
                lottery.addPlayer(player, ticketNumber, material, amount);
            } else
                chat.send.permissionError(sender);

        } else {
            chat.send.onlyPlayersCanDoThatError(sender);
        }
        return true;

    }

}
