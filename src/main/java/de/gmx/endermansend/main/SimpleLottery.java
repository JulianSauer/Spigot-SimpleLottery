package de.gmx.endermansend.main;

import de.gmx.endermansend.game.LotteryCalculator;
import de.gmx.endermansend.game.LotteryCoordinatorAuto;
import de.gmx.endermansend.handlers.ConfigHandler;
import de.gmx.endermansend.interfaces.ConfigHandlerInterface;
import de.gmx.endermansend.interfaces.LotteryCalculatorInterface;
import de.gmx.endermansend.interfaces.LotteryCoordinatorInterface;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class SimpleLottery extends JavaPlugin {

    private Logger logger;
    private ConfigHandlerInterface config;
    LotteryCoordinatorInterface lottery;

    @Override
    public void onEnable() {

        this.logger = getLogger();
        this.config = new ConfigHandler(this);

        lottery = new LotteryCoordinatorAuto(new LotteryCalculator(1, 10), logger);

        logger.info("SimpleLottery enabled");

    }

    @Override
    public void onDisable() {
        System.out.println("SimpleLottery disabled");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("lottery")) {

            if(args.length == 1) {

                if(args[0].equalsIgnoreCase("start")) {
                    lottery.startNewGame();
                    return true;
                } else if(args[0].equalsIgnoreCase("stop")) {
                    lottery.finishGame();
                    return true;
                }

            } else if (args.length == 2) {

                if(args[0].equalsIgnoreCase("buyTicket") && sender instanceof Player) {
                    Player player = (Player) sender;
                    int luckyNumber = Integer.parseInt(args[1]);
                    lottery.addPlayer(player, luckyNumber);
                }

            }

            return true;

        }

        return false;

    }

}
