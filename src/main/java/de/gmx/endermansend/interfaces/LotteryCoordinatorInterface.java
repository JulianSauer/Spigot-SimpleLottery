package de.gmx.endermansend.interfaces;

import de.gmx.endermansend.game.RoundWithDefaultSettings;
import de.gmx.endermansend.handlers.InventoryHandler;
import de.gmx.endermansend.main.SimpleLottery;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

/**
 * Executes the necessary steps to realise commands from CommandExecutor.
 */
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

    /**
     * Starts a new round if the last round has finished (or doesn't exist).
     *
     * @param sender Needed to inform initiator about mistakes
     */
    public void startNewGame(CommandSender sender) {

        if (round != null) {
            if (round.getStatus() != RoundInterface.Status.GAME_HAS_FINISHED) {
                chat.sendRoundNotFinished(sender, round);
                return;
            }
        }
        round = new RoundWithDefaultSettings(main, ++roundNumber);
        chat.broadcastRoundStart(roundNumber);

    }

    /**
     * Finishes the current round if it is not already finished.
     *
     * @param sender Needed to inform initiator about mistakes
     */
    public void finishGame(CommandSender sender) {

        if (round.getStatus() == RoundInterface.Status.GAME_HAS_FINISHED) {
            chat.sendRoundNotRunning(sender, round);
            return;
        }

        Collection<String> winners;
        int winningNumber = calc.getWinningNumber();

        winners = round.finishRound(winningNumber);

        chat.broadcastWinners(roundNumber, winningNumber, winners);

    }

    /**
     * Creates a new ticket for a player from the given information.
     *
     * @param player       Player who tries to participate
     * @param ticketNumber Player's desired ticket number
     * @param material     Player's desired material for the bet
     * @param amount       Player's desired amount of bet items
     */
    public void addPlayer(Player player, int ticketNumber, String material, int amount) {

        ItemStack bet = InventoryHandler.getBetFromPlayer(player, material, amount, config.getAllowedMaterials(), chat);
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

    /**
     * Passes the request to list all bought tickets on to a ChatHandler if the round status allows it.
     *
     * @param sender Needed to inform initiator about mistakes
     */
    public void listTicketsPublic(CommandSender sender) {

        if (round == null) {
            chat.sendRoundNotStarted(sender);
            return;
        }
        chat.broadcastBoughtTickets(round);
    }

    /**
     * Passes the request to list all bought tickets on to a ChatHandler if the round status allows it.
     *
     * @param sender Needed to inform initiator about mistakes
     */
    public void listTicketsPrivate(CommandSender sender) {
        if (round == null) {
            chat.sendRoundNotStarted(sender);
            return;
        }
        chat.sendBoughtTickets(sender, round);
    }

}

