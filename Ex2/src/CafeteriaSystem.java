import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoicePrinter printer = new InvoicePrinter();
    private final InvoiceStore store = new FileStore();
    private int invoiceSeq = 1000;

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    // Intentionally SRP-violating: menu mgmt + tax + discount + format +
    // persistence.
    public void checkout(String customerType, List<OrderLine> lines) {

        String invId = "INV-" + (++invoiceSeq);

        BillCalculator calc = new BillCalculator();
        BillCalculator.Bill bill = calc.calculate(customerType, lines, menu);

        double subtotal = bill.subtotal;
        double taxPct = bill.taxPct;
        double tax = bill.tax;
        double discount = bill.discount;
        double total = bill.total;

        String printable = printer.buildInvoice(
                invId,
                lines,
                menu,
                subtotal,
                taxPct,
                tax,
                discount,
                total);

        printable = InvoiceFormatter.identityFormat(printable);

        printer.print(printable);

        store.save(invId, printable);

        printer.printSaved(invId, store.countLines(invId));
    }
}
