public class EmailMessage {
    public final String email;
    public final String subject;
    public final String body;

    public EmailMessage(String email, String subject, String body) {
        if (body != null && body.length() > 40) {
            throw new IllegalArgumentException("email body cannot exceed 40 chars");
        }
        this.email = email;
        this.subject = subject;
        this.body = body;
    }
}