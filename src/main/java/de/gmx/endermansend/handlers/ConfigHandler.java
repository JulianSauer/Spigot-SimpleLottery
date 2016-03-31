package de.gmx.endermansend.handlers;

import de.gmx.endermansend.interfaces.ConfigHandlerInterface;
import de.gmx.endermansend.main.SimpleLottery;

import java.util.HashMap;

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

        if (!config.isSet("messages.round.start"))
            noValueFound("messages.round.start");
        if (!config.isSet("messages.round.end"))
            noValueFound("messages.round.end");
        if (!config.isSet("messages.round.status"))
            noValueFound("messages.round.status");
        if (!config.isSet("messages.winnerAnnouncement"))
            noValueFound("messages.winnerAnnouncement");

        HashMap<String, String> messages = new HashMap<String, String>();
        messages.put("round.start", config.getString("messages.round.start"));
        messages.put("round.end", config.getString("messages.round.end"));
        messages.put("round.status", config.getString("messages.round.status"));
        messages.put("winnerAnnouncement", config.getString("messages.winnerAnnouncement"));

        return messages;

    }

}
