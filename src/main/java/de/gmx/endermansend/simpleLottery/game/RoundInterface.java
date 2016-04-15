package de.gmx.endermansend.simpleLottery.game;

import de.gmx.endermansend.simpleLottery.chat.ChatHandler;
import de.gmx.endermansend.simpleLottery.main.SimpleLottery;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.UUID;

/**
 * Represents a basic round in the game. Handles a list of tickets.
 */
public abstract class RoundInterface {

    protected Collection<Ticket> tickets;

    private SimpleLottery main;

    protected ChatHandler chat;

    protected int multiplier = 0;

    public enum Status {
        GAME_IS_RUNNING,
        GAME_IS_HALTED,
        GAME_HAS_FINISHED
    }

    protected int roundNumber;
    private Status status;

    protected RoundInterface(SimpleLottery main, int roundNumber) {

        this.main = main;
        this.chat = main.getChat();
        this.multiplier = main.getConfigHandler().get.roundMultiplier();

        this.roundNumber = roundNumber;
        status = Status.GAME_IS_RUNNING;

    }

    public boolean resumeGame() {
        return changeStatusTo(Status.GAME_IS_RUNNING);
    }

    public boolean haltGame() {
        return changeStatusTo(Status.GAME_IS_HALTED);
    }

    public boolean quitGame() {
        return changeStatusTo(Status.GAME_HAS_FINISHED);
    }

    public abstract void addLotteryEntry(Player player, int ticketNumber, ItemStack bet, ChatHandler chat);

    /**
     * Finishes the round and returns the owners of tickets that won.
     *
     * @param ticketNumber Number which won the round
     * @return List of ticket owners
     */
    public Collection<String> finishRound(int ticketNumber) {
        quitGame();
        return handOutRewards(ticketNumber);
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public abstract Collection<Ticket> getTickets();

    public abstract Collection<Integer> getTicketsOf(UUID player);

    public abstract Collection<Ticket> getWinningTickets(int winningNumber);

    public abstract Collection<String> getOwnersOf(int ticketNumber);

    /**
     * Gives the reward to players who have the winning number
     *
     * @return Names of the winners
     */
    public abstract Collection<String> handOutRewards(int winningNumber);

    public Status getStatus() {
        return status;
    }

    /**
     * Changes the status if the round hasn't finished.
     * Also doesn't work if there's nothing to change.
     *
     * @param status Status the game should be changed to
     * @return True if the status could be changed
     */
    private boolean changeStatusTo(Status status) {
        if (this.status == Status.GAME_HAS_FINISHED ||
                this.status == status)
            return false;
        this.status = status;
        return true;
    }

}
