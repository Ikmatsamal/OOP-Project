package models.research;

import interfaces.Researchable;

import java.util.ArrayList;
import java.util.List;

public class ResearchProject {

    private String topic;
    private List<Researchable> participants;
    private List<ResearchPaper> publishedPapers;

    public ResearchProject(String topic) {
        this.topic = topic;
        this.participants = new ArrayList<>();
        this.publishedPapers = new ArrayList<>();
    }

    public void addParticipant(Researchable researcher) {
        participants.add(researcher);
    }

    public void removeParticipant(Researchable researcher) {
        participants.remove(researcher);
    }

    public void addPublishedPaper(ResearchPaper paper) {
        publishedPapers.add(paper);
    }

    public List<Researchable> getParticipants() {
        return participants;
    }

    public List<ResearchPaper> getPublishedPapers() {
        return publishedPapers;
    }

    public String getTopic() {
        return topic;
    }

    @Override
    public String toString() {
        return "ResearchProject{" +
                "topic='" + topic + '\'' +
                ", participants=" + participants.size() +
                ", papers=" + publishedPapers.size() +
                '}';
    }
}