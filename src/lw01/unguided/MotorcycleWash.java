package lw01.unguided;

public class MotorcycleWash extends WashService {
    public static final int RATE_PER_DAY = 15000;

    public MotorcycleWash(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        return 15000 * getDays() + 5000;
    }

    @Override
    public String label() {
        return "Motorcycle Wash";
    }
}
