package de.gmx.endermansend.interfaces;

import de.gmx.endermansend.game.Ticket;
import org.bukkit.entity.Player;

import java.util.Collection;

public abstract class RoundInterface {

    protected int roundNumber;

    public RoundInterface(int roundNumber) {
        this.roundNumber = roundNumber;
    }

    public abstract boolean addLotteryEntry(Player player, int luckyNumber);

    public int getRoundNumber() {
        return roundNumber;
    }

    public abstract Collection<Ticket> getTickets();

    public abstract Collection<Integer> getTicketsOf(Player player);

    public abstract Collection<String> getOwnersOf(int luckyNumber);

}
