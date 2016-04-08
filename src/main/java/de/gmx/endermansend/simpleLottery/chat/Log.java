package de.gmx.endermansend.simpleLottery.chat;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Implements methods for logging.
 */
public class Log {

    private ChatHandler chat;
    private HashMap<String, String> adminMessages;
    private Logger logger;

    protected Log(ChatHandler chat) {
        this.chat = chat;
        this.adminMessages = chat.adminMessages;
        this.logger = chat.logger;
    }

    public void pluginEnabled() {
        logger.info(adminMessages.get("plugin.enabled"));
    }

    public void pluginDisabled() {
        logger.info(adminMessages.get("plugin.disabled"));
    }

}
