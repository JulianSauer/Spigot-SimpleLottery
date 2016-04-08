package de.gmx.endermansend.simpleLottery.config;

/**
 * Defines paths to set values in config.yml.
 */
public class SetValuesInConfig {

    ConfigHandler config;

    SetValuesInConfig(ConfigHandler config) {
        this.config = config;
    }

    public void roundNumber(int roundNumber) {
        config.setIntInConfig("round.roundNumber", roundNumber);
    }

}
