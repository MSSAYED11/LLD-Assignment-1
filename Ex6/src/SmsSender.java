public class SmsSender extends NotificationSender<SmsMessage> {
    public SmsSender(AuditLog audit) { super(audit); }

    @Override
    public void send(SmsMessage m) {
        System.out.println("SMS -> to=" + m.phone + " body=" + m.body);
        audit.add("sms sent");
    }
}