package de.gmx.endermansend.game;

import de.gmx.endermansend.main.SimpleLottery;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RoundWithUniqueParticipants extends RoundWithDefaultSettings {

    public RoundWithUniqueParticipants(SimpleLottery main, int roundNumber) {
        super(main, roundNumber);
    }

    /**
     * Allows only one entry per player per round.
     *
     * @param player       Initiator of the lottery entry
     * @param ticketNumber Lottery number the player chose
     * @param bet          Bet the player has made
     * @return true if entry could be added
     */
    @Override
    public boolean addLotteryEntry(Player player, int ticketNumber, ItemStack bet) {

        for (Ticket t : tickets) {
            if (t.getOwner().equals(player))
                return false;
        }

        return super.addLotteryEntry(player, ticketNumber, bet);

    }

}
