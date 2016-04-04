package de.gmx.endermansend.handlers;

import de.gmx.endermansend.game.Ticket;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles access to the inventory of players. Useful for giving ticket (paper) items and rewards to players as well as
 * getting bet items from them.
 */
public class InventoryHandler {

    /**
     * Gives a paper item to the player.
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

    /**
     * Gives a player items as a reward for winning.
     *
     * @param player  Player who will receive the rewards
     * @param rewards Items that the player has won
     * @return False when there's no more space in the player's inventory
     */
    public static boolean giveRewardToPlayer(Player player, List<ItemStack> rewards) {

        for (ItemStack reward : rewards) {
            if (player.getInventory().firstEmpty() != -1) {
                player.getInventory().addItem(reward);
            } else {
                return false;
            }
        }
        return true;

    }

    /**
     * Parses a material name and amount to an ItemStack.
     * The material is validated via the values in config.yml
     * found under allowedBlocks.
     *
     * @param material         Material name; has to match org.bukkit.Material
     * @param amount           Amount of items payed
     * @param allowedMaterials List of allowed materials from config.yml
     * @return Null if material is not allowed/found in config.yml
     */
    public static ItemStack getBetFromPlayer(String material, int amount, List<String> allowedMaterials) {

        ItemStack bet = null;
        for (String allowedMaterial : allowedMaterials) {
            if (allowedMaterial.equalsIgnoreCase(material)) {
                bet = new ItemStack(Material.getMaterial(material.toUpperCase()), amount);
                break;
            }
        }
        return bet;

    }

    /**
     * Creates a paper item that can be given to a player.
     *
     * @param ticket      Ticket that the paper item will represents
     * @param roundNumber Round number of this ticket
     * @return Paper item
     */
    private static ItemStack createTicketItem(Ticket ticket, int roundNumber) {

        ItemStack ticketItem = new ItemStack(Material.PAPER, 1);
        ItemMeta meta = ticketItem.getItemMeta();
        meta.setDisplayName("Ticket Number: " + ticket.getTicketNumber());

        ArrayList<String> lore = new ArrayList<String>();
        lore.add("Round #" + roundNumber);
        ItemStack bet = ticket.getBet();
        lore.add("Bet: " + bet.getType().name() + " (" + bet.getAmount() + ")");
        meta.setLore(lore);

        ticketItem.setItemMeta(meta);
        return ticketItem;

    }

}
