package de.gmx.endermansend.game;

import org.bukkit.entity.Player;

public class RoundWithUniqueEntries extends RoundWithDefaultSettings {

    public RoundWithUniqueEntries(int roundNumber) {
        super(roundNumber);
    }

    /**
     * Allows only one entry per player and ticketNumber
     *
     * @param player       Initiator of the lottery entry
     * @param ticketNumber Lottery number the player chose
     * @return true if entry could be added
     */
    public boolean addLotteryEntry(Player player, int ticketNumber) {

        for (Ticket t : tickets) {
            if (ticketNumber == t.getTicketNumber() ||
                    t.getOwner().equalsIgnoreCase(player.getName()))
                return false;
        }

        return super.addLotteryEntry(player, ticketNumber);

    }

}
