public class WhatsAppSender extends NotificationSender<WhatsAppMessage> {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    public void send(WhatsAppMessage m) {
        System.out.println("WA -> to=" + m.phone + " body=" + m.body);
        audit.add("wa sent");
    }
}