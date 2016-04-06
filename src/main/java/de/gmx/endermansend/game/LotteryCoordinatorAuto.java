package de.gmx.endermansend.game;

import de.gmx.endermansend.main.SimpleLottery;
import org.bukkit.scheduler.BukkitRunnable;

public class LotteryCoordinatorAuto extends LotteryCoordinatorInterface {

    public LotteryCoordinatorAuto(SimpleLottery main) {

        super(main);

        (new Counter(config.get.autoModeBreakInterval(), false)).runTaskTimerAsynchronously(main, 60L, 20L);

    }

    /**
     * Starts/stops a round after the set intervals
     */
    class Counter extends BukkitRunnable {

        int interval;

        boolean intervalIsARound = true;

        RoundInterface.Status lastStatus;

        public Counter(int interval, boolean intervalIsARound) {

            this.interval = interval;
            this.intervalIsARound = intervalIsARound;

            if (intervalIsARound) {
                startNewGame(null);
                lastStatus = RoundInterface.Status.GAME_IS_RUNNING;
            } else {
                lastStatus = RoundInterface.Status.GAME_HAS_FINISHED;
            }

        }

        public void run() {

            if (round != null)
                checkForReset();
            interval--;

            if (interval < 1) {
                startNewInterval();
            }

            // Countdown
            if (interval <= 3 && interval > 0) {
                if (intervalIsARound)
                    chat.message.broadcastStatusStopsSoon(roundNumber, interval);
                else
                    chat.message.broadcastStatusStartsSoon(roundNumber + 1, interval);
            }

        }

        /**
         * Resets the interval if a round was started/stopped manually
         */
        private void checkForReset() {

            if (lastStatus == RoundInterface.Status.GAME_IS_RUNNING
                    && round.getStatus() == RoundInterface.Status.GAME_HAS_FINISHED) {
                intervalIsARound = false;
                interval = config.get.autoModeBreakInterval();

            } else if (lastStatus == RoundInterface.Status.GAME_HAS_FINISHED
                    && round.getStatus() == RoundInterface.Status.GAME_IS_RUNNING) {
                intervalIsARound = true;
                interval = config.get.autoModeRoundInterval();
            }
            lastStatus = round.getStatus();

        }

        /**
         * Starts a new timer interval that performs a new round/break depending on intervalIsARound.
         */
        private void startNewInterval() {

            if (intervalIsARound) {
                finishGame(null);
                interval = config.get.autoModeBreakInterval();
            } else {
                interval = config.get.autoModeRoundInterval();
            }
            intervalIsARound = !intervalIsARound;

            if (intervalIsARound) {
                startNewGame(null);
                lastStatus = RoundInterface.Status.GAME_IS_RUNNING;
            } else {
                lastStatus = RoundInterface.Status.GAME_HAS_FINISHED;
            }

        }

    }

}
