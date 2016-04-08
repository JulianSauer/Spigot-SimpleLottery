package de.gmx.endermansend.simpleLottery.chat;

import org.bukkit.ChatColor;

/**
 * Initializes variables for ChatHandler
 */
public class InitializeChat {

    protected static void init(ChatHandler chat) {

        chat.pluginTag = ChatColor.GOLD + "[Simple Lottery]" + ChatColor.RESET;
        chat.errorTag = ChatColor.RED + "[Simple Lottery]" + ChatColor.RESET;
        chat.listTag = "                     -";

        chat.adminMessages.put("plugin.enabled", "SimpleLottery enabled");
        chat.adminMessages.put("plugin.disabled", "SimpleLottery disabled");
        chat.adminMessages.put("error.noPlayer", "Only players can perform this command.");

    }

}
