public abstract class PrintJob implements Chargeable {
    protected String id;
    protected int pages;

    public PrintJob(String id, int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("Pages must be a positive number.");
        }
        this.id = id;
        this.pages = pages;
    }

    public String label() {
        return "Print";
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