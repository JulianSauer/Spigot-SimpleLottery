package de.gmx.endermansend.game;

public class Ticket {

    private int roundNumber;
    private int ticketNumber;

    private String owner;

    public Ticket(int ticketNumber) {
        this.ticketNumber = ticketNumber;
        roundNumber = 1;
        owner = null;
    }

    public Ticket(int ticketNumber, String owner) {
        this.ticketNumber = ticketNumber;
        this.owner = owner;
        roundNumber = 1;
    }

    public Ticket(int roundNumber, int ticketNumber) {
        this.roundNumber = roundNumber;
        this.ticketNumber = ticketNumber;
        owner = null;
    }

    public Ticket(int roundNumber, int ticketNumber, String owner) {
        this.roundNumber = roundNumber;
        this.ticketNumber = ticketNumber;
        this.owner = owner;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getOwner() {
        return owner;
    }
}
