package de.gmx.endermansend.main;

import de.gmx.endermansend.handlers.ConfigHandler;
import de.gmx.endermansend.interfaces.ConfigHandlerInterface;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class SimpleLottery extends JavaPlugin {

    private Logger logger;
    private ConfigHandlerInterface config;

    @Override
    public void onEnable() {

        this.logger = getLogger();
        this.config = new ConfigHandler(this);

        logger.info("SimpleLottery enabled");

    }

    @Override
    public void onDisable() {
        System.out.println("SimpleLottery disabled");
    }

}
