package de.gmx.endermansend.main;

import de.gmx.endermansend.game.LotteryCalculator;
import de.gmx.endermansend.game.LotteryCoordinatorAuto;
import de.gmx.endermansend.handlers.ChatHandler;
import de.gmx.endermansend.handlers.ConfigHandler;
import de.gmx.endermansend.interfaces.ConfigHandlerInterface;
import de.gmx.endermansend.interfaces.LotteryCalculatorInterface;
import de.gmx.endermansend.interfaces.LotteryCoordinatorInterface;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class SimpleLottery extends JavaPlugin {

    private ConfigHandlerInterface config;
    private LotteryCoordinatorInterface lottery;
    private ChatHandler chat;
    private LotteryCalculatorInterface calc;

    @Override
    public void onEnable() {

        this.config = new ConfigHandler(this);
        this.chat = new ChatHandler(this);
        this.calc = new LotteryCalculator(1, 10);

        lottery = new LotteryCoordinatorAuto(this);
        this.getCommand("lottery").setExecutor(new SimpleLotteryCommandExecutor(this));

        chat.logPluginEnabled();

    }

    @Override
    public void onDisable() {
        chat.logPluginDisabled();
    }

    public ConfigHandlerInterface getConfigHandler() {
        return config;
    }

    public LotteryCoordinatorInterface getLottery() {
        return lottery;
    }

    public ChatHandler getChat() {
        return chat;
    }

    public LotteryCalculatorInterface getCalc() {
        return calc;
    }
}
