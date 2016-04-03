package de.gmx.endermansend.game;

public class Ticket {

    private int ticketNumber;

    private String owner;

    public Ticket(int ticketNumber) {
        this.ticketNumber = ticketNumber;
        owner = null;
    }

    public Ticket(String owner, int ticketNumber) {
        this.owner = owner;
        this.ticketNumber = ticketNumber;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Ticket) {
            Ticket t = (Ticket) o;
            if (this.owner.equalsIgnoreCase(t.owner))
                return this.ticketNumber == t.ticketNumber;
        }

        return false;

    }

    @Override
    public String toString() {
        return "\nOwner: " + owner + " Ticket Number: " + ticketNumber;
    }
}
