import java.util.Map;

public class AddOnPricing implements FeeComponent {
    private final Map<AddOn, Double> rates;

    public AddOnPricing(Map<AddOn, Double> rates) {
        this.rates = rates;
    }

    @Override
    public double monthlyCost(BookingRequest req) {
        double total = 0.0;
        if (req.addOns != null) {
            for (AddOn a : req.addOns) {
               
                total += rates.getOrDefault(a, 0.0);
            }
        }
        return total;
    }
}