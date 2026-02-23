import java.util.Map;

public class RoomPricing implements FeeComponent {
    private final Map<Integer, Double> rates;
    private final double defaultRate;

    public RoomPricing(Map<Integer, Double> rates, double defaultRate) {
        this.rates = rates;
        this.defaultRate = defaultRate;
    }

    @Override
    public double monthlyCost(BookingRequest req) {
        // No switch statement! Fully OCP compliant.
        return rates.getOrDefault(req.roomType, defaultRate);
    }
}