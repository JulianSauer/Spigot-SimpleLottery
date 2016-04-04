package de.gmx.endermansend.game;

import de.gmx.endermansend.main.SimpleLottery;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RoundWithUniqueEntries extends RoundWithDefaultSettings {

    public RoundWithUniqueEntries(SimpleLottery main, int roundNumber) {
        super(main, roundNumber);
    }

    /**
     * Allows only one entry per player and ticketNumber
     *
     * @param player       Initiator of the lottery entry
     * @param ticketNumber Lottery number the player chose
     * @param bet          Bet the player has made
     * @return true if entry could be added
     */
    public boolean addLotteryEntry(Player player, int ticketNumber, ItemStack bet) {

        for (Ticket t : tickets) {
            if (ticketNumber == t.getTicketNumber() ||
                    t.getOwner().equals(player))
                return false;
        }

        return super.addLotteryEntry(player, ticketNumber, bet);

    }

}
