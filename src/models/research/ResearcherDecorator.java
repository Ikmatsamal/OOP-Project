package models.research;

import interfaces.Researchable;
import models.user.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.io.Serializable;

public class ResearcherDecorator implements Researchable, Serializable {

    private User user;
    private int hIndex;
    private List<ResearchPaper> papers;
    private List<ResearchProject> projects;

    public ResearcherDecorator(User user, int hIndex) {
        this.user = user;
        this.hIndex = hIndex;
        this.papers = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    @Override
    public void addPaper(ResearchPaper paper) {
        papers.add(paper);
    }

    @Override
    public void addProject(ResearchProject project) {
        if (project != null) {
            projects.add(project);
        }
    }
    @Override
    public void printPapers(Comparator<ResearchPaper> comparator) {
        papers.stream()
                .sorted(comparator)
                .forEach(System.out::println);
    }

    @Override
    public int getHIndex() {
        return hIndex;
    }

    @Override
    public List<ResearchPaper> getPapers() {
        return papers;
    }
    
    public List<ResearchProject> getProjects() {
        return projects;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "ResearcherDecorator{" +
                "user=" + user +
                ", hIndex=" + hIndex +
                ", papers=" + papers.size() +
                ", projects=" + projects.size() +
                '}';
    }
}