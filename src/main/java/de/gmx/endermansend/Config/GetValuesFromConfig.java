package de.gmx.endermansend.Config;

import java.util.HashMap;
import java.util.List;

/**
 * Defines paths for config.yml values.
 */
public class GetValuesFromConfig {

    ConfigHandler config;

    GetValuesFromConfig(ConfigHandler config) {
        this.config = config;
    }

    public boolean autoModeEnabled() {
        return config.getBooleanFromConfig("autoMode.enabled");
    }

    public int autoModeRoundInterval() {
        return config.getIntFromConfig("autoMode.roundInterval");
    }

    public int autoModeBreakInterval() {
        return config.getIntFromConfig("autoMode.breakInterval");
    }

    public int roundMultiplier() {
        return config.getIntFromConfig("round.multiplier");
    }

    public boolean roundAllowSameTicketNumbers() {
        return config.getBooleanFromConfig("round.allowSameTicketNumbers");
    }

    public boolean roundAllowMoreThanOneEntryPerPlayer() {
        return config.getBooleanFromConfig("round.allowMoreThanOneEntryPerPlayer");
    }

    public List<String> allowedMaterials() {
        return config.getListFromConfig("allowedBlocks");
    }

    /**
     * Reads messages that can be changed by server admin.
     *
     * @return Collection of the defined values
     */
    public HashMap<String, String> messages() {

        HashMap<String, String> messages = new HashMap<String, String>();
        messages.put("round.start", config.getStringFromConfig("messages.round.start"));
        messages.put("round.end", config.getStringFromConfig("messages.round.end"));
        messages.put("round.status.statusMessage", config.getStringFromConfig("messages.round.status.statusMessage"));
        messages.put("round.status.running", config.getStringFromConfig("messages.round.status.running"));
        messages.put("round.status.halted", config.getStringFromConfig("messages.round.status.halted"));
        messages.put("round.status.finished", config.getStringFromConfig("messages.round.status.finished"));
        messages.put("round.status.startsSoon", config.getStringFromConfig("messages.round.status.startsSoon"));
        messages.put("round.status.stopsSoon", config.getStringFromConfig("messages.round.status.stopsSoon"));
        messages.put("round.winningNumber", config.getStringFromConfig("messages.round.winningNumber"));
        messages.put("round.winners", config.getStringFromConfig("messages.round.winners"));
        messages.put("round.noWinners", config.getStringFromConfig("messages.round.noWinners"));
        messages.put("ticket.bought", config.getStringFromConfig("messages.ticket.bought"));
        messages.put("ticket.list", config.getStringFromConfig("messages.ticket.list"));
        messages.put("ticket.error.general", config.getStringFromConfig("messages.ticket.error.general"));
        messages.put("ticket.error.alreadyExists", config.getStringFromConfig("messages.ticket.error.alreadyExists"));
        messages.put("ticket.error.tooMany", config.getStringFromConfig("messages.ticket.error.tooMany"));
        messages.put("ticket.error.roundOver", config.getStringFromConfig("messages.ticket.error.roundOver"));
        messages.put("ticket.error.tooPoor", config.getStringFromConfig("messages.ticket.error.tooPoor"));
        messages.put("ticket.error.reward", config.getStringFromConfig("messages.ticket.error.reward"));
        messages.put("permissionError", config.getStringFromConfig("messages.permissionError"));

        return messages;

    }

}
