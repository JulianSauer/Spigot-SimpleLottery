package de.gmx.endermansend.game;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Ticket {

    private int ticketNumber;

    private Player owner;

    private ItemStack bet;

    public Ticket(Player owner, int ticketNumber, ItemStack bet) {
        this.owner = owner;
        this.ticketNumber = ticketNumber;
        this.bet = bet;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public Player getOwner() {
        return owner;
    }

    public ItemStack getBet() {
        return bet;
    }

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
        return "\nOwner: " + owner + " Ticket Number: " + ticketNumber;
    }
}
