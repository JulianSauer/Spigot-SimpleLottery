package de.gmx.endermansend.handlers;

import de.gmx.endermansend.interfaces.ConfigHandlerInterface;
import de.gmx.endermansend.main.SimpleLottery;

import java.util.HashMap;
import java.util.List;

/**
 * Paths for config.yml values
 */
public class ConfigHandler extends ConfigHandlerInterface {

    public ConfigHandler(SimpleLottery plugin) {
        super(plugin);
    }

    public boolean getAutoModeEnabled() {
        return getBooleanFromConfig("autoMode.enabled");
    }

    public int getAutoModeInterval() {
        return getIntFromConfig("autoMode.interval");
    }

    public int getRoundDuration() {
        return getIntFromConfig("round.duration");
    }

    public int getRoundMultiplier() {
        return getIntFromConfig("round.multiplier");
    }

    public List<String> getAllowedMaterials() {
        return getListFromConfig("allowedBlocks");
    }

    public HashMap<String, String> getMessages() {

        HashMap<String, String> messages = new HashMap<String, String>();
        messages.put("round.start", getStringFromConfig("messages.round.start"));
        messages.put("round.end", getStringFromConfig("messages.round.end"));
        messages.put("round.status.statusMessage", getStringFromConfig("messages.round.status.statusMessage"));
        messages.put("round.status.running", getStringFromConfig("messages.round.status.running"));
        messages.put("round.status.halted", getStringFromConfig("messages.round.status.halted"));
        messages.put("round.status.ended", getStringFromConfig("messages.round.status.ended"));
        messages.put("round.winningNumber", getStringFromConfig("messages.round.winningNumber"));
        messages.put("round.winners", getStringFromConfig("messages.round.winners"));
        messages.put("round.noWinners", getStringFromConfig("messages.round.noWinners"));
        messages.put("ticket.bought", getStringFromConfig("messages.ticket.bought"));
        messages.put("ticket.failure", getStringFromConfig("messages.ticket.failure"));
        messages.put("ticket.rewardFailure", getStringFromConfig("messages.ticket.rewardFailure"));

        return messages;

    }

}
