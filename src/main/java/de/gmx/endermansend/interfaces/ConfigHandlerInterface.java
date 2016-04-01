package de.gmx.endermansend.interfaces;

import de.gmx.endermansend.main.SimpleLottery;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public abstract class ConfigHandlerInterface {

    private SimpleLottery plugin;
    protected Logger logger;
    protected FileConfiguration config;

    public ConfigHandlerInterface(SimpleLottery plugin) {

        this.plugin = plugin;
        this.logger = this.plugin.getLogger();

        if (!loadConfig())
            createDefaultConfig();


    }

    /**
     * Saves default configuration file and assigns it's content to config
     */
    public void createDefaultConfig() {

        logger.info("Creating default config");
        plugin.saveDefaultConfig();

        config = plugin.getConfig();
        logger.info("Config loaded");

    }

    public boolean loadConfig() {

        logger.info("Loading config");

        if (this.configExists()) {
            config = plugin.getConfig();
            logger.info("Config loaded");
            return true;
        }

        return false;

    }

    public void saveConfig() {
        logger.info("Saving config");
        plugin.saveConfig();
        logger.info("Config saved");
    }

    public abstract boolean getAutomodeEnabled();

    public abstract int getAutomodeInterval();

    public abstract int getRoundDuration();

    public abstract int getRoundMultiplier();

    public abstract HashMap<String, String> getMessages();

    protected boolean getBooleanFromConfig(String path) {
        if (!config.isSet(path) || !config.isBoolean(path))
            noValueFound(path);
        return config.getBoolean(path);
    }

    protected int getIntFromConfig(String path) {
        if (!config.isSet(path) || !config.isInt(path))
            noValueFound(path);
        return config.getInt(path);
    }

    protected double getDoubleFromConfig(String path) {
        if (!config.isSet(path) || !config.isDouble(path))
            noValueFound(path);
        return config.getDouble(path);
    }

    protected String getStringFromConfig(String path) {
        if (!config.isSet(path) || !config.isString(path))
            noValueFound(path);
        return config.getString(path);
    }

    protected List getListFromConfig(String path) {
        if (!config.isSet(path) || !config.isList(path))
            noValueFound(path);
        return config.getList(path);
    }

    protected void noValueFound(String value) {
        logger.warning("Value is missing or wrong type: " + value);
        logger.warning("Using default value");
        logger.warning("Delete config.yml to get a default one");
    }

    private boolean configExists() {

        File[] files = plugin.getDataFolder().listFiles();
        if(files == null)
            return false;

        for (File file : files) {
            if (file.getName().equals("config.yml")) {
                return true;
            }
        }

        return false;

    }


}
