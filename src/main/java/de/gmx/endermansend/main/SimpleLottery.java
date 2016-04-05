package de.gmx.endermansend.main;

import de.gmx.endermansend.Config.ConfigHandler;
import de.gmx.endermansend.chat.ChatHandler;
import de.gmx.endermansend.helper.LotteryCalculator;
import de.gmx.endermansend.game.LotteryCoordinatorAuto;
import de.gmx.endermansend.helper.LotteryCalculatorInterface;
import de.gmx.endermansend.game.LotteryCoordinatorInterface;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleLottery extends JavaPlugin {

    private ConfigHandler config;
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

        chat.message.logPluginEnabled();

    }

    @Override
    public void onDisable() {
        chat.message.logPluginDisabled();
    }

    public ConfigHandler getConfigHandler() {
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
