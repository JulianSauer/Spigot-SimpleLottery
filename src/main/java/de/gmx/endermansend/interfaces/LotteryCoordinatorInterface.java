package de.gmx.endermansend.interfaces;

import de.gmx.endermansend.game.RoundWithDefaultSettings;
import de.gmx.endermansend.handlers.InventoryHandler;
import de.gmx.endermansend.main.SimpleLottery;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public abstract class LotteryCoordinatorInterface {

    private int roundNumber = 0;

    private SimpleLottery main;

    private ConfigHandlerInterface config;

    private RoundInterface round;

    private LotteryCalculatorInterface calc;

    private ChatHandlerInterface chat;

    protected LotteryCoordinatorInterface(SimpleLottery main) {
        this.main = main;
        this.config = main.getConfigHandler();
        this.calc = main.getCalc();
        this.chat = main.getChat();
    }

    public void startNewGame(CommandSender sender) {

        if (round != null) {
            if (round.getStatus() != RoundInterface.Status.GAME_HAS_FINISHED) {
                chat.sendErrorMessage(sender, "Can't start new game. Game #" + roundNumber + " is not finished!");
                return;
            }
        }
        round = new RoundWithDefaultSettings(main, ++roundNumber);
        chat.broadcastRoundStart(roundNumber);

    }

    public void finishGame(CommandSender sender) {

        Collection<String> winners;
        int winningNumber = calc.getWinningNumber();

        if (round.getStatus() == RoundInterface.Status.GAME_HAS_FINISHED) {
            chat.sendErrorMessage(sender, "Game #" + roundNumber + " has already stopped.");
            return;
        }
        winners = round.finishRound(winningNumber);

        chat.broadcastWinners(roundNumber, winningNumber, winners);

    }

    public void addPlayer(Player player, int ticketNumber, String material, int amount) {

        ItemStack bet = InventoryHandler.getBetFromPlayer(material, amount, config.getAllowedMaterials());
        if (bet == null) {
            chat.sendTicketFailure(player, ticketNumber);
            return;
        }

        if (round.addLotteryEntry(player, ticketNumber, bet)) {
            chat.sendTicketBought(player, ticketNumber);
        } else {
            chat.sendTicketFailure(player, ticketNumber);
        }

    }

    public void listTicketsPublic(CommandSender sender) {

        if (round == null) {
            chat.sendErrorMessage(sender, "No round was started yet!");
            return;
        }
        chat.broadcastBoughtTickets(round);
    }

    public void listTicketsPrivate(CommandSender sender) {
        if (round == null) {
            chat.sendErrorMessage(sender, "No round was started yet!");
            return;
        }
        chat.sendBoughtTickets(sender, round);
    }

}

