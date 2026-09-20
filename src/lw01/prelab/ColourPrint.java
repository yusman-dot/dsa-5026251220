public class ColourPrint extends PrintJob {
    private static final int RATE_FIRST_10 = 1500;
    private static final int RATE_AFTER_10 = 1000;
    private static final int SETUP_FEE = 2000;

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pagesCharge;
        if (pages <= 10) {
            pagesCharge = pages * RATE_FIRST_10;
        } else {
            pagesCharge = (10 * RATE_FIRST_10) + ((pages - 10) * RATE_AFTER_10);
        }
        return pagesCharge + SETUP_FEE;
    }

    @Override
    public String label() {
        return "Colour";
    }
}