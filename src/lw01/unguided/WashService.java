package lw01.unguided;

public abstract class WashService implements Billable {
    private String id;
    private int days;

    protected WashService(String id, int days) {
          if (days <= 0) {
            throw new IllegalArgumentException("Days must be a positive number.");
        }
        this.id = id;
        this.days = days;
    }

    public String getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

     public String label() {
        return "WashService";
    }

    @Override
    public abstract int calculateCharge();

    public int calculateCharge(int copies) {
        if (copies <= 0) {
            throw new IllegalArgumentException("Copies must be a positive number.");
        }
        return copies * calculateCharge();
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}