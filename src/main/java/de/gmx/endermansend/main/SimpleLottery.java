package de.gmx.endermansend.main;

import de.gmx.endermansend.game.LotteryCalculator;
import de.gmx.endermansend.game.LotteryCoordinatorAuto;
import de.gmx.endermansend.handlers.ChatHandler;
import de.gmx.endermansend.handlers.ConfigHandler;
import de.gmx.endermansend.interfaces.ConfigHandlerInterface;
import de.gmx.endermansend.interfaces.LotteryCoordinatorInterface;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class SimpleLottery extends JavaPlugin {

    private Logger logger;
    private ConfigHandlerInterface config;
    private LotteryCoordinatorInterface lottery;
    private ChatHandler chat;

    @Override
    public void onEnable() {

        this.logger = getLogger();
        this.config = new ConfigHandler(this);
        this.chat = new ChatHandler(config);

        lottery = new LotteryCoordinatorAuto(config, new LotteryCalculator(1, 10));
        this.getCommand("lottery").setExecutor(new SimpleLotteryCommandExecutor(lottery, chat));

        logger.info("SimpleLottery enabled");

    }

    @Override
    public void onDisable() {
        System.out.println("SimpleLottery disabled");
    }

}
