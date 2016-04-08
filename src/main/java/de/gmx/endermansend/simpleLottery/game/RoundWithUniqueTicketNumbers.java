package de.gmx.endermansend.simpleLottery.game;

import de.gmx.endermansend.simpleLottery.chat.ChatHandler;
import de.gmx.endermansend.simpleLottery.main.SimpleLottery;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RoundWithUniqueTicketNumbers extends RoundWithDefaultSettings {

    public RoundWithUniqueTicketNumbers(SimpleLottery main, int roundNumber) {
        super(main, roundNumber);
    }

    /**
     * Allows only one entry per ticketNumber per round.
     *
     * @param player       Initiator of the lottery entry
     * @param ticketNumber Lottery number the player chose
     * @param bet          Bet the player has made
     * @param chat         Used to inform the player if the purchase was successful
     */
    @Override
    public void addLotteryEntry(Player player, int ticketNumber, ItemStack bet, ChatHandler chat) {

        for (Ticket t : tickets) {
            if (ticketNumber == t.getTicketNumber()) {
                chat.send.alreadyExistsError(player, ticketNumber);
                return;
            }
        }

        super.addLotteryEntry(player, ticketNumber, bet, chat);

    }

}
