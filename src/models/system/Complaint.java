package models.system;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Complaint implements Serializable {
    private String senderEmail;
    private String text;
    private LocalDateTime createdAt;

    public Complaint(String senderEmail, String text) {
        this.senderEmail = senderEmail;
        this.text = text;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "[" + createdAt + "] Complaint from: " + senderEmail +
                "\nText: " + text;
    }
}