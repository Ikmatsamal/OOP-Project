package models.system;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Complaint implements Serializable {
    private String senderEmail;
    private String text;
    private LocalDateTime date;

    public Complaint(String senderEmail, String text) {
        this.senderEmail = senderEmail;
        this.text = text;
        this.date = LocalDateTime.now();
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Complaint from " + senderEmail +
                " | " + date +
                " | " + text;
    }
}