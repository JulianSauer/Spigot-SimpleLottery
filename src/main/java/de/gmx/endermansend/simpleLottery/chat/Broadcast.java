package de.gmx.endermansend.simpleLottery.chat;

import de.gmx.endermansend.simpleLottery.game.RoundInterface;
import de.gmx.endermansend.simpleLottery.game.Ticket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Implements methods for broadcasting methods to public chat.
 */
public class Broadcast {

    private ChatHandler chat;
    private HashMap<String, String> messages;
    private HashMap<String, String> adminMessages;

    protected Broadcast(ChatHandler chat) {
        this.chat = chat;
        this.messages = chat.messages;
        this.adminMessages = chat.adminMessages;
    }

    public void roundStart(int roundNumber) {
        String msg = messages.get("round.start").replace("<<roundNumber>>", "" + roundNumber);
        chat.broadcastMessage(msg);
    }

    public void roundEnd(int roundNumber) {
        String msg = messages.get("round.end").replace("<<roundNumber>>", "" + roundNumber);
        chat.broadcastMessage(msg);
    }

    public void timeUntilNextBreak(int roundNumber, int seconds) {
        String msg = messages.get("round.time.nextBreak").replace("<<roundNumber>>", "" + roundNumber).replace("<<seconds>>", "" + seconds);
        chat.broadcastMessage(msg);
    }

    public void timeUntilNextRound(int seconds) {
        String msg = messages.get("round.time.nextRound").replace("<<seconds>>", "" + seconds);
        chat.broadcastMessage(msg);
    }

    /**
     * Displays the status of the current/last round.
     *
     * @param round Interface of the round object which's status should be displayed
     */
    public void status(RoundInterface round) {

        chat.broadcastMessage(chat.getRoundStatus(round));

    }

    public void statusStartsSoon(int roundNumber, int seconds) {
        String msg = messages.get("round.status.startsSoon").replace("<<roundNumber>>", "" + roundNumber).replace("<<seconds>>", "" + seconds);
        chat.broadcastMessage(msg);
    }

    public void statusStopsSoon(int roundNumber, int seconds) {
        String msg = messages.get("round.status.stopsSoon").replace("<<roundNumber>>", "" + roundNumber).replace("<<seconds>>", "" + seconds);
        chat.broadcastMessage(msg);
    }

    /**
     * Displays the winning number as well as every player that has bought a ticket in the round with that number. If
     * nobody won, an alternative message will be displayed instead.
     *
     * @param roundNumber   Number of the finished round
     * @param winningNumber Number that won the lottery
     * @param winners       Collection of winners
     */
    public void winners(int roundNumber, int winningNumber, Collection<String> winners) {

        roundEnd(roundNumber);
        String msg = messages.get("round.winningNumber").replace("<<winningNumber>>", "" + winningNumber);
        chat.broadcastMessage(msg);

        if (!winners.isEmpty()) {

            if (winners.size() == 1) {
                chat.broadcastMessage(messages.get("round.winners") + " " + winners.toArray()[0]);
            } else {
                chat.broadcastMessage(messages.get("round.winners"));
                for (String player : winners)
                    chat.broadcastListEntry(player);
            }
        } else {
            chat.broadcastMessage(messages.get("round.noWinners"));
        }

    }

    /**
     * Displays all players, that bought tickets as well as their numbers.
     *
     * @param round Round object containing the ticket list
     */
    public void boughtTickets(RoundInterface round) {

        ArrayList<Ticket> tickets = (ArrayList<Ticket>) round.getTickets();
        chat.broadcastMessage(messages.get("ticket.list"));
        for (Ticket ticket : tickets)
            chat.broadcastListEntry(ticket.toString());
    }

}
