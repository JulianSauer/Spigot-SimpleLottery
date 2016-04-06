package de.gmx.endermansend.game;

import de.gmx.endermansend.main.SimpleLottery;
import org.bukkit.scheduler.BukkitRunnable;

public class LotteryCoordinatorAuto extends LotteryCoordinatorInterface {

    public LotteryCoordinatorAuto(SimpleLottery main) {

        super(main);

        (new Counter(false)).runTaskTimerAsynchronously(main, 60L, 20L);

    }

    /**
     * Starts/stops a round after the set intervals
     */
    class Counter extends BukkitRunnable {

        int interval;

        boolean intervalIsARound = true;

        RoundInterface.Status lastStatus;

        public Counter(boolean intervalIsARound) {

            this.intervalIsARound = intervalIsARound;

            if (intervalIsARound) {
                interval = config.get.autoModeRoundInterval();
                startNewGame(null);
                lastStatus = RoundInterface.Status.GAME_IS_RUNNING;
                chat.broadcast.timeUntilNextBreak(round.getRoundNumber(), interval);
            } else {
                interval = config.get.autoModeBreakInterval();
                lastStatus = RoundInterface.Status.GAME_HAS_FINISHED;
                chat.broadcast.timeUntilNextRound(interval);
            }

        }

        public void run() {

            if (round != null)
                processInterval();
            else {
                interval--;
                countdown();
            }

            if (interval < 1) {
                startNewInterval();
            }

        }

        /**
         * Resets the interval if a round was started/stopped manually.
         */
        private void processInterval() {

            if ((lastStatus == RoundInterface.Status.GAME_IS_RUNNING
                    && round.getStatus() == RoundInterface.Status.GAME_HAS_FINISHED)
                    || (lastStatus == RoundInterface.Status.GAME_IS_HALTED
                    && round.getStatus() == RoundInterface.Status.GAME_HAS_FINISHED)) {

                intervalIsARound = false;
                interval = config.get.autoModeBreakInterval();

            } else if (lastStatus == RoundInterface.Status.GAME_HAS_FINISHED
                    && round.getStatus() == RoundInterface.Status.GAME_IS_RUNNING) {

                intervalIsARound = true;
                interval = config.get.autoModeRoundInterval();

            }
            lastStatus = round.getStatus();

            if (round.getStatus() != RoundInterface.Status.GAME_IS_HALTED) {
                interval--;
                countdown();
            }

        }

        /**
         * Prints a countdown to public chat.
         */
        private void countdown() {
            if (interval <= 3 && interval > 0) {
                if (intervalIsARound)
                    chat.broadcast.statusStopsSoon(roundNumber, interval);
                else
                    chat.broadcast.statusStartsSoon(roundNumber + 1, interval);
            }
        }

        /**
         * Starts a new timer interval that performs a new round/break depending on intervalIsARound.
         */
        private void startNewInterval() {

            if (intervalIsARound) {
                finishGame(null);
                interval = config.get.autoModeBreakInterval();
                lastStatus = RoundInterface.Status.GAME_HAS_FINISHED;
                chat.broadcast.timeUntilNextRound(interval);
            } else {
                interval = config.get.autoModeRoundInterval();
                startNewGame(null);
                lastStatus = RoundInterface.Status.GAME_IS_RUNNING;
                chat.broadcast.timeUntilNextBreak(round.getRoundNumber(), interval);
            }
            intervalIsARound = !intervalIsARound;

        }

    }

}
