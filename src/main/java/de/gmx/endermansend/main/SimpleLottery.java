package de.gmx.endermansend.main;

import de.gmx.endermansend.game.LotteryCalculator;
import de.gmx.endermansend.handlers.ConfigHandler;
import de.gmx.endermansend.interfaces.ConfigHandlerInterface;
import de.gmx.endermansend.interfaces.LotteryCalculatorInterface;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("lottery")) {

            LotteryCalculatorInterface calc;
            if (args.length == 2)
                calc = new LotteryCalculator(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            else
                calc = new LotteryCalculator(1, 100);

            logger.info("Winner: " + calc.getWinnerTicket().getLuckyNumber());

            return true;

        }

        return false;

    }

}
