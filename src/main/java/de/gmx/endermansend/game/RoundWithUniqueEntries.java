package de.gmx.endermansend.game;

import de.gmx.endermansend.chat.ChatHandler;
import de.gmx.endermansend.main.SimpleLottery;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RoundWithUniqueEntries extends RoundWithDefaultSettings {

    public RoundWithUniqueEntries(SimpleLottery main, int roundNumber) {
        super(main, roundNumber);
    }

    /**
     * Allows only one entry per player and ticketNumber per round.
     *
     * @param player       Initiator of the lottery entry
     * @param ticketNumber Lottery number the player chose
     * @param bet          Bet the player has made
     * @param chat         Used to inform the player if the purchase was successful
     */
    @Override
    public void addLotteryEntry(Player player, int ticketNumber, ItemStack bet, ChatHandler chat) {

        for (Ticket t : tickets) {
            if (t.getOwner().equals(player)) {
                chat.send.tooManyTicketsError(player);
                return;
            } else if (ticketNumber == t.getTicketNumber()) {
                chat.send.alreadyExistsError(player, ticketNumber);
                return;
            }
        }

        super.addLotteryEntry(player, ticketNumber, bet, chat);

    }

}
