package de.gmx.endermansend.game;

import de.gmx.endermansend.interfaces.LotteryCoordinatorInterface;
import de.gmx.endermansend.main.SimpleLottery;

// TODO: Add functionality so that lottery rounds are started automatically based on the given time interval found in config.yml
public class LotteryCoordinatorAuto extends LotteryCoordinatorInterface {

    public LotteryCoordinatorAuto(SimpleLottery main) {
        super(main);
    }

}
