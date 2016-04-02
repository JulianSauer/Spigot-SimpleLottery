package de.gmx.endermansend.game;

import de.gmx.endermansend.interfaces.ConfigHandlerInterface;
import de.gmx.endermansend.interfaces.LotteryCalculatorInterface;
import de.gmx.endermansend.interfaces.LotteryCoordinatorInterface;

public class LotteryCoordinatorAuto extends LotteryCoordinatorInterface {

    public LotteryCoordinatorAuto(ConfigHandlerInterface config, LotteryCalculatorInterface calc) {
        super(config, calc);
    }

}
