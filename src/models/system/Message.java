package models.system;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
    private String senderEmail;
    private String receiverEmail;
    private String text;
    private LocalDateTime sentAt;

    public Message(String senderEmail, String receiverEmail, String text) {
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.text = text;
        this.sentAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "[" + sentAt + "] From: " + senderEmail +
                " To: " + receiverEmail +
                "\nMessage: " + text;
    }
}