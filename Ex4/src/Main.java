import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");
        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        
        // 1. Configure pricing dynamically
        Map<Integer, Double> roomRates = Map.of(
            LegacyRoomTypes.SINGLE, 14000.0,
            LegacyRoomTypes.DOUBLE, 15000.0,
            LegacyRoomTypes.TRIPLE, 12000.0
        );
        
        Map<AddOn, Double> addOnRates = Map.of(
            AddOn.MESS, 1000.0,
            AddOn.LAUNDRY, 500.0,
            AddOn.GYM, 300.0
        );

        // 2. Inject components
        List<FeeComponent> components = List.of(
            new RoomPricing(roomRates, 16000.0),
            new AddOnPricing(addOnRates)
        );

        // 3. Run calculator
        HostelFeeCalculator calc = new HostelFeeCalculator(new FakeBookingRepo(), components);
        calc.process(req);
    }
}