package de.gmx.endermansend.game;

import de.gmx.endermansend.interfaces.LotteryCalculatorInterface;
import de.gmx.endermansend.interfaces.LotteryCoordinatorInterface;

import java.util.logging.Logger;

public class LotteryCoordinatorAuto extends LotteryCoordinatorInterface {

    public LotteryCoordinatorAuto(LotteryCalculatorInterface calc, Logger logger) {
        super(calc, logger);
    }

}
