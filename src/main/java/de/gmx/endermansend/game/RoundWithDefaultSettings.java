package de.gmx.endermansend.game;

import de.gmx.endermansend.interfaces.RoundInterface;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RoundWithDefaultSettings extends RoundInterface {

    protected List<Ticket> tickets;

    public RoundWithDefaultSettings(int roundNumber) {
        super(roundNumber);
        tickets = new ArrayList<Ticket>();
    }

    public boolean addPlayerToRound(Player player, int luckyNumber) {

        String playerName = player.getName();
        Ticket ticket = new Ticket(playerName, luckyNumber);

        if (tickets.contains(ticket))
            return false;

        tickets.add(ticket);
        return true;

    }

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public Collection<Integer> getTicketsOf(Player player) {

        String playerName = player.getName();
        List<Integer> playerTickets = new ArrayList<Integer>();

        String owner;
        for (Ticket t : tickets) {
            owner = t.getOwner();
            if (owner.equalsIgnoreCase(playerName))
                playerTickets.add(t.getLuckyNumber());
        }

        return playerTickets;

    }

    public Collection<String> getOwnersOf(int luckyNumber) {

        List<String> owners = new ArrayList<String>();

        for (Ticket t : tickets) {
            if (luckyNumber == t.getLuckyNumber())
                owners.add(t.getOwner());
        }

        return owners;

    }

}