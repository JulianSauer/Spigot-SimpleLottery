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
                    chat.send.roundStatusError(sender, round);
                return;
            }
        }
        boolean allowSameTicketNumbers = config.get.roundAllowSameTicketNumbers();
        boolean allowMoreThanOneEntryPerPlayer = config.get.roundAllowMoreThanOneEntryPerPlayer();

        if (allowSameTicketNumbers && allowMoreThanOneEntryPerPlayer)
            round = new RoundWithDefaultSettings(main, ++roundNumber);
        else if (!allowSameTicketNumbers)
            round = new RoundWithUniqueEntries(main, ++roundNumber);
        else if (!allowMoreThanOneEntryPerPlayer)
            round = new RoundWithUniqueParticipants(main, ++roundNumber);
        else
            round = new RoundWithUniqueEntries(main, ++roundNumber);

        chat.broadcast.roundStart(roundNumber);

    }

    /**
     * Halts the current round if possible.
     *
     * @param sender Needed to inform initiator about mistakes
     */
    public void haltGame(CommandSender sender) {

        if (gameIsNotRunning()) {
            if (sender != null)
                chat.send.roundStatusError(sender, round);
            return;
        }
        round.haltGame();

    }

    /**
     * Restarts the current round if it is halted.
     *
     * @param sender Needed to inform initiator about mistakes
     */
    public void resume(CommandSender sender) {

        if (round == null) {
            chat.send.roundStatusError(sender, round);
            return;
        }

        if (round.getStatus() == RoundInterface.Status.GAME_IS_HALTED)
            round.resumeGame();
        else
            chat.send.roundStatusError(sender, round);

    }

    /**
     * Finishes the current round if it is not already finished.
     *
     * @param sender Needed to inform initiator about mistakes
     */
    public void finishGame(CommandSender sender) {

        if (!gameCanBeFinished()) {
            if (sender != null)
                chat.send.roundStatusError(sender, round);
            return;
        }

        Collection<String> winners;
        int winningNumber = calc.getWinningNumber();

        winners = round.finishRound(winningNumber);

        chat.broadcast.winners(roundNumber, winningNumber, winners);

    }

    /**
     * Prints the status of the current round.
     *
     * @param sender Receives the status message
     */
    public void showStatus(CommandSender sender) {
        if (round != null)
            chat.send.status(sender, round);
        else
            chat.send.roundStatusError(sender, null);
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
            chat.send.roundStatusError(player, round);
            return;
        }

        if (ticketNumber < calc.getMin() || ticketNumber > calc.getMax()) {
            chat.send.wrongNumberRangeError(player, ticketNumber, calc.getMin(), calc.getMax());
            return;
        }

        ItemStack bet = InventoryHandler.getBetFromPlayer(player, material, amount, config.get.allowedMaterials(), chat);
        if (bet == null) {
            chat.send.ticketError(player);
            return;
        }

        round.addLotteryEntry(player, ticketNumber, bet, chat);

    }

    /**
     * Passes the request to list all bought tickets on to a ChatHandler if the round status allows it.
     *
     * @param sender Needed to inform initiator about mistakes
     */
    public void listTicketsPublic(CommandSender sender) {

        if (round == null) {
            chat.send.roundStatusError(sender, round);
            return;
        }
        chat.broadcast.boughtTickets(round);
    }

    /**
     * Passes the request to list all bought tickets on to a ChatHandler if the round status allows it.
     *
     * @param sender Needed to inform initiator about mistakes
     */
    public void listTicketsPrivate(CommandSender sender) {
        if (round == null) {
            chat.send.roundStatusError(sender, round);
            return;
        }
        chat.send.boughtTickets(sender, round);
    }

    /**
     * Checks the state of the current round.
     *
     * @return True if the round is finished or doesn't exist
     */
    protected boolean gameIsNotRunning() {
        if (round == null)
            return true;
        if (round.getStatus() == RoundInterface.Status.GAME_HAS_FINISHED ||
                round.getStatus() == RoundInterface.Status.GAME_IS_HALTED)
            return true;
        return false;
    }

    /**
     * Checks if the game can be finished without errors.
     *
     * @return False if an error free quitting can't be guaranteed
     */
    protected boolean gameCanBeFinished() {
        if (round == null)
            return false;
        if (round.getStatus() != RoundInterface.Status.GAME_HAS_FINISHED)
            return true;
        return false;
    }

}

