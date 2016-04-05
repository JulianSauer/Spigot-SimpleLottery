package de.gmx.endermansend.handlers;

import de.gmx.endermansend.interfaces.ConfigHandlerInterface;
import de.gmx.endermansend.main.SimpleLottery;

import java.util.HashMap;
import java.util.List;

// TODO: Getter methods should be declared here rather than in the interface
/**
 * Paths for config.yml values.
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

    /**
     * Reads messages that can be changed by server admin.
     *
     * @return Collection of the defined values
     */
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
        messages.put("ticket.list", getStringFromConfig("messages.ticket.list"));
        messages.put("ticket.error.general", getStringFromConfig("messages.ticket.error.general"));
        messages.put("ticket.error.alreadyExists", getStringFromConfig("messages.ticket.error.alreadyExists"));
        messages.put("ticket.error.tooMany", getStringFromConfig("messages.ticket.error.tooMany"));
        messages.put("ticket.error.roundOver", getStringFromConfig("messages.ticket.error.roundOver"));
        messages.put("ticket.error.tooPoor", getStringFromConfig("messages.ticket.error.tooPoor"));
        messages.put("ticket.error.reward", getStringFromConfig("messages.ticket.error.reward"));
        messages.put("permissionError", getStringFromConfig("messages.permissionError"));

        return messages;

    }

}
