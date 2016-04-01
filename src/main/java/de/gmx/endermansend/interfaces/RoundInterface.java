package de.gmx.endermansend.interfaces;

import de.gmx.endermansend.game.Ticket;
import org.bukkit.entity.Player;

import java.util.Collection;

public abstract class RoundInterface {

    public enum Status {
        GAME_IS_RUNNING,
        GAME_IS_HALTED,
        GAME_HAS_FINISHED
    }

    protected int roundNumber;
    protected Status status;

    public RoundInterface(int roundNumber) {
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

    public abstract boolean addLotteryEntry(Player player, int luckyNumber);

    /**
     * Finishes the round and returns the owners of tickets with matching luckyNumbers
     *
     * @param luckyNumber Number which won the round
     * @return List of ticket owners
     */
    public Collection<String> finishRound(int luckyNumber) {
        status = Status.GAME_HAS_FINISHED;
        return getOwnersOf(luckyNumber);
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public abstract Collection<Ticket> getTickets();

    public abstract Collection<Integer> getTicketsOf(Player player);

    public abstract Collection<String> getOwnersOf(int luckyNumber);

    protected boolean changeStatusTo(Status status) {
        if (this.status == Status.GAME_HAS_FINISHED ||
                this.status == status)
            return false;
        this.status = status;
        return true;
    }

}
