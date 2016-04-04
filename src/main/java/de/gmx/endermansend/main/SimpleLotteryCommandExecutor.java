package de.gmx.endermansend.main;

import de.gmx.endermansend.handlers.ChatHandler;
import de.gmx.endermansend.interfaces.LotteryCoordinatorInterface;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

            } else if (args.length == 2) {

                if (args[0].equalsIgnoreCase("list"))
                    if (args[1].equalsIgnoreCase("public"))
                        listTicketsPublic(sender);

            } else if (args.length == 4) {

                if (args[0].equalsIgnoreCase("buy"))
                    return buyTicket(sender, args);

            }

        }

        return false;
    }

    private boolean start(CommandSender sender) {
        if (sender.hasPermission("SimpleLottery.Control")) {
            lottery.startNewGame(sender);
        } else {
            chat.sendPermissionError(sender);
        }
        return true;
    }

    private boolean stop(CommandSender sender) {
        if (sender.hasPermission("SimpleLottery.Control"))
            lottery.finishGame(sender);
        else
            chat.sendPermissionError(sender);
        return true;
    }

    private boolean listTicketsPrivate(CommandSender sender) {
        if (sender.hasPermission("SimpleLottery.Round.ListTickets.Private"))
            lottery.listTicketsPrivate(sender);
        else
            chat.sendPermissionError(sender);
        return true;
    }

    private boolean listTicketsPublic(CommandSender sender) {
        if (sender.hasPermission("SimpleLottery.Round.ListTickets.Public"))
            lottery.listTicketsPublic(sender);
        else
            chat.sendPermissionError(sender);
        return true;
    }

    private boolean buyTicket(CommandSender sender, String[] args) {

        if (sender instanceof Player) {

            if (sender.hasPermission("SimpleLottery.Round.BuyTickets")) {
                Player player = (Player) sender;
                int ticketNumber = Integer.parseInt(args[1]);
                String material = args[2];
                int amount = Integer.parseInt(args[3]);
                lottery.addPlayer(player, ticketNumber, material, amount);
            } else
                chat.sendPermissionError(sender);

        } else {
            chat.sendHaveToBePlayerError(sender);
        }
        return true;

    }

}
