public class WhatsAppMessage {
    public final String phone;
    public final String body;

    public WhatsAppMessage(String phone, String body) {
        if (phone == null || !phone.startsWith("+")) {
            throw new IllegalArgumentException("phone must start with + and country code");
        }
        this.phone = phone;
        this.body = body;
    }
}