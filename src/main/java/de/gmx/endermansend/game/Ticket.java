package de.gmx.endermansend.game;

public class Ticket {

    private int luckyNumber;

    private String owner;

    public Ticket(int luckyNumber) {
        this.luckyNumber = luckyNumber;
        owner = null;
    }

    public Ticket(String owner, int luckyNumber) {
        this.owner = owner;
        this.luckyNumber = luckyNumber;
    }

    public int getLuckyNumber() {
        return luckyNumber;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof Ticket) {
            Ticket t = (Ticket) o;
            if (this.owner.equalsIgnoreCase(t.owner))
                return this.luckyNumber == t.luckyNumber;
        }

        return false;

    }

    @Override
    public String toString() {
        return "\nOwner: " + owner + " Lucky Number: " + luckyNumber;
    }
}
