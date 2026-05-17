package models.system;

import java.io.Serializable;
import java.time.LocalDateTime;

public class News implements Serializable {

    private String title;
    private String content;
    private LocalDateTime publishedAt;
    private String author;

    public News(String title, String content,
                LocalDateTime publishedAt,
                String author) {

        this.title = title;
        this.content = content;
        this.publishedAt = publishedAt;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "\n========== NEWS ==========\n" +
                "Title: " + title + "\n" +
                "Content: " + content + "\n" +
                "Author: " + author + "\n" +
                "Published at: " + publishedAt + "\n";
    }
}
