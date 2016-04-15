package de.gmx.endermansend.simpleLottery.game;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a basic lottery ticket and can be used to store the number, owner and bet of a ticket as well as
 * calculate the reward if the owner won with this number.
 */
public class Ticket {

    private int ticketNumber;

    private UUID owner;

    private ItemStack bet;

    public Ticket(UUID owner, int ticketNumber, ItemStack bet) {
        this.owner = owner;
        this.ticketNumber = ticketNumber;
        this.bet = bet;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public UUID getOwner() {
        return owner;
    }

    public String getOwnerName() {
        return Bukkit.getOfflinePlayer(owner).getName();
    }

    public ItemStack getBet() {
        return bet;
    }

    /**
     * Calculates the reward the winner of this ticket receives based on the item type and amount of their bet as well
     * as the multiplier of the current round.
     *
     * @param multiplier Can be changed in config.yml
     * @return List of items for the winner to receive
     */
    public List<ItemStack> getReward(int multiplier) {

        int reward = bet.getAmount() * multiplier;
        int max = bet.getMaxStackSize();

        List<ItemStack> rewardItems = new ArrayList<ItemStack>();

        while (true) {

            if (reward <= max) {
                rewardItems.add(new ItemStack(bet.getType(), reward));
                break;
            } else {
                rewardItems.add(new ItemStack(bet.getType(), max));
                reward -= max;
            }

        }

        return rewardItems;

    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Ticket) {
            Ticket t = (Ticket) o;
            if (this.owner.equals(t))
                return this.ticketNumber == t.ticketNumber;
        }

        return false;

    }

    @Override
    public String toString() {
        return Bukkit.getOfflinePlayer(owner).getName() + ": " + ticketNumber;
    }
}
