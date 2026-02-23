import java.util.*;

public class BillCalculator {

    public static class Bill {
        public final double subtotal;
        public final double taxPct;
        public final double tax;
        public final double discount;
        public final double total;

        public Bill(double subtotal,double taxPct,double tax,double discount,double total){
            this.subtotal=subtotal;
            this.taxPct=taxPct;
            this.tax=tax;
            this.discount=discount;
            this.total=total;
        }
    }

    public Bill calculate(String customerType, List<OrderLine> lines, Map<String,MenuItem> menu){

        double subtotal = 0.0;

        for(OrderLine l:lines){
            MenuItem item = menu.get(l.itemId);
            subtotal += item.price * l.qty;
        }

        double taxPct = TaxRules.taxPercent(customerType);
        double tax = subtotal * (taxPct/100.0);

        double discount = DiscountRules.discountAmount(customerType, subtotal, lines.size());

        double total = subtotal + tax - discount;

        return new Bill(subtotal,taxPct,tax,discount,total);
    }
}