package de.gmx.endermansend.game;

import de.gmx.endermansend.Config.ConfigHandler;
import de.gmx.endermansend.chat.ChatHandler;
import de.gmx.endermansend.helper.InventoryHandler;
import de.gmx.endermansend.helper.LotteryCalculatorInterface;
import de.gmx.endermansend.main.SimpleLottery;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

/**
 * Executes the necessary steps to realise commands from CommandExecutor.
 */
public abstract class LotteryCoordinatorInterface {

    protected int roundNumber = 0;

    protected SimpleLottery main;

    protected ConfigHandler config;

    protected RoundInterface round;

    protected LotteryCalculatorInterface calc;

    protected ChatHandler chat;

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
                if (sender != null)
                    chat.message.sendRoundStatusError(sender, round);
                return;
            }
        }
        round = new RoundWithDefaultSettings(main, ++roundNumber);
        chat.message.broadcastRoundStart(roundNumber);

    }

    /**
     * Finishes the current round if it is not already finished.
     *
     * @param sender Needed to inform initiator about mistakes
     */
    public void finishGame(CommandSender sender) {

        if (gameIsNotRunning()) {
            if (sender != null)
                chat.message.sendRoundStatusError(sender, round);
            return;
        }

        Collection<String> winners;
        int winningNumber = calc.getWinningNumber();

        winners = round.finishRound(winningNumber);

        chat.message.broadcastWinners(roundNumber, winningNumber, winners);

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

        if (gameIsNotRunning()) {
            chat.message.sendRoundStatusError(player, round);
            return;
        }

        ItemStack bet = InventoryHandler.getBetFromPlayer(player, material, amount, config.get.allowedMaterials(), chat);
        if (bet == null) {
            chat.message.sendTicketFailure(player, ticketNumber);
            return;
        }

        if (round.addLotteryEntry(player, ticketNumber, bet)) {
            chat.message.sendTicketBought(player, ticketNumber);
        } else {
            chat.message.sendTicketFailure(player, ticketNumber);
        }

    }

    /**
     * Passes the request to list all bought tickets on to a ChatHandler if the round status allows it.
     *
     * @param sender Needed to inform initiator about mistakes
     */
    public void listTicketsPublic(CommandSender sender) {

        if (round == null) {
            chat.message.sendRoundStatusError(sender, round);
            return;
        }
        chat.message.broadcastBoughtTickets(round);
    }

    /**
     * Passes the request to list all bought tickets on to a ChatHandler if the round status allows it.
     *
     * @param sender Needed to inform initiator about mistakes
     */
    public void listTicketsPrivate(CommandSender sender) {
        if (round == null) {
            chat.message.sendRoundStatusError(sender, round);
            return;
        }
        chat.message.sendBoughtTickets(sender, round);
    }

    /**
     * Checks the state of the current round.
     *
     * @return True if the round is finished or doesn't exist
     */
    protected boolean gameIsNotRunning() {
        if (round == null)
            return true;
        if (round.getStatus() == RoundInterface.Status.GAME_HAS_FINISHED)
            return true;
        return false;
    }

}

