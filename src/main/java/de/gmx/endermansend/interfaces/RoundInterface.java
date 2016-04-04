package de.gmx.endermansend.interfaces;

import de.gmx.endermansend.game.Ticket;
import de.gmx.endermansend.main.SimpleLottery;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public abstract class RoundInterface {

    protected Collection<Ticket> tickets;

    private SimpleLottery main;

    protected ChatHandlerInterface chat;

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
        this.multiplier = main.getConfigHandler().getRoundMultiplier();

        this.roundNumber = roundNumber;
        status = Status.GAME_IS_RUNNING;

    }

    public boolean resumeGame() {
        return changeStatusTo(Status.GAME_IS_RUNNING);
    }

    public boolean pauseGame() {
        return changeStatusTo(Status.GAME_IS_HALTED);
    }

    public boolean quitGame() {
        return changeStatusTo(Status.GAME_HAS_FINISHED);
    }

    public abstract boolean addLotteryEntry(Player player, int ticketNumber, ItemStack bet);

    /**
     * Finishes the round and returns the owners of tickets with matching ticketNumbers
     *
     * @param ticketNumber Number which won the round
     * @return List of ticket owners
     */
    public Collection<String> finishRound(int ticketNumber) {
        status = Status.GAME_HAS_FINISHED;
        return getOwnersOf(ticketNumber);
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public abstract Collection<Ticket> getTickets();

    public abstract Collection<Integer> getTicketsOf(Player player);

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

    private boolean changeStatusTo(Status status) {
        if (this.status == Status.GAME_HAS_FINISHED ||
                this.status == status)
            return false;
        this.status = status;
        return true;
    }

}
