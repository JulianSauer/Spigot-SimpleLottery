package de.gmx.endermansend.handlers;

import de.gmx.endermansend.interfaces.ConfigHandlerInterface;
import de.gmx.endermansend.main.SimpleLottery;

import java.util.HashMap;

/**
 * Paths for config.yml values
 */
public class ConfigHandler extends ConfigHandlerInterface {

    public ConfigHandler(SimpleLottery plugin) {
        super(plugin);
    }

    public boolean getAutomodeEnabled() {
        return getBooleanFromConfig("automode.enabled");
    }

    public int getAutomodeInterval() {
        return getIntFromConfig("automode.interval");
    }

    public int getRoundDuration() {
        return getIntFromConfig("round.duration");
    }

    public int getRoundMultiplier() {
        return getIntFromConfig("round.multiplier");
    }

    public HashMap<String, String> getMessages() {

        HashMap<String, String> messages = new HashMap<String, String>();
        messages.put("round.start", getStringFromConfig("messages.round.start"));
        messages.put("round.end", getStringFromConfig("messages.round.end"));
        messages.put("round.status", getStringFromConfig("messages.round.status"));
        messages.put("winnerAnnouncement", getStringFromConfig("messages.winnerAnnouncement"));

        return messages;

    }

}
