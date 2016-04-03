package de.gmx.endermansend.handlers;

import de.gmx.endermansend.game.Ticket;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class InventoryHandler {

    /**
     * Gives a paper item to the player
     *
     * @param player      Ticket buyer
     * @param ticket      Metadata for the paper
     * @param roundNumber Metadata for the paper
     * @return false if player inventory is full
     */
    public static boolean giveTicketToPlayer(Player player, Ticket ticket, int roundNumber) {

        if (player.getInventory().firstEmpty() == -1) {
            return false;
        }
        player.getInventory().addItem(createTicketItem(ticket, roundNumber));
        return true;

    }

    private static ItemStack createTicketItem(Ticket ticket, int roundNumber) {

        ItemStack ticketItem = new ItemStack(Material.PAPER, 1);
        ItemMeta meta = ticketItem.getItemMeta();
        meta.setDisplayName("Ticket Number: " + ticket.getTicketNumber());
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("Round #" + roundNumber);
        meta.setLore(lore);
        ticketItem.setItemMeta(meta);
        return ticketItem;

    }

}
