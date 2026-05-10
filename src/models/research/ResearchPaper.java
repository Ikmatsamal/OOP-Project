package models.research;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import java.io.Serializable;

public class ResearchPaper implements Comparable<ResearchPaper>, Serializable{
    private String title;
    private List<String> authors;
    private String journal;
    private int pages;
    private LocalDate publicationDate;
    private String doi;
    private int citations;

    public ResearchPaper(String title, List<String> authors, String journal,
                         int pages, LocalDate publicationDate,
                         String doi, int citations) {
        this.title = title;
        this.authors = authors;
        this.journal = journal;
        this.pages = pages;
        this.publicationDate = publicationDate;
        this.doi = doi;
        this.citations = citations;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getJournal() {
        return journal;
    }

    public int getPages() {
        return pages;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public String getDoi() {
        return doi;
    }

    public int getCitations() {
        return citations;
    }

    @Override
    public int compareTo(ResearchPaper other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    @Override
    public String toString() {
        return "ResearchPaper{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", journal='" + journal + '\'' +
                ", pages=" + pages +
                ", publicationDate=" + publicationDate +
                ", doi='" + doi + '\'' +
                ", citations=" + citations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ResearchPaper that)) return false;
        return Objects.equals(doi, that.doi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doi);
    }
}