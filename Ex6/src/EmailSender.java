public class EmailSender extends NotificationSender<EmailMessage> {
    public EmailSender(AuditLog audit) { super(audit); }

    @Override
    public void send(EmailMessage m) {
        // No more silent truncation hidden in the sender logic!
        System.out.println("EMAIL -> to=" + m.email + " subject=" + m.subject + " body=" + m.body);
        audit.add("email sent");
    }
}