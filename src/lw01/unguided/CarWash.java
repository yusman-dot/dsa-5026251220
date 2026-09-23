package lw01.unguided;

public class CarWash extends WashService {
    private static final int RATE_PER_DAY = 35000;

    public CarWash(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        if (getDays() <= 3) {
            return RATE_PER_DAY * getDays();
        } else {
            return (RATE_PER_DAY * 3) + ((getDays() - 3) * (RATE_PER_DAY - 5000));
        }
    }

    @Override
    public String label() {
        return "Car Wash";
    }
}
