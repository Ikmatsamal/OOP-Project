package services;

import comparators.PaperByCitationsComparator;
import comparators.PaperByDateComparator;
import comparators.PaperByPagesComparator;
import interfaces.Researchable;
import models.research.ResearchPaper;
import models.research.ResearchProject;
import patterns.singleton.DatabaseSingleton;
import models.user.User;
import exceptions.NotResearcherException;
import utils.Logger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResearchService {

    private final DatabaseSingleton db = DatabaseSingleton.getInstance();

    public void addPaper(Researchable researcher, ResearchPaper paper) {
        researcher.addPaper(paper);
        db.save();
    }

    public void createProject(String topic) {
        ResearchProject project = new ResearchProject(topic);
        db.addProject(project);
        db.save();
        Logger.info(
        	    "Research project created: " +
        	    topic
        	);
        
    }

    public void joinProject(Researchable researcher, ResearchProject project) {
        project.addParticipant(researcher);
        researcher.addProject(project);
        db.save();
        
        Logger.info(
        	    "Researcher joined project: " +
        	    project.getTopic()
        	);
    }
    
    
    public void joinProject(models.user.User user, ResearchProject project) {
        if (!(user instanceof Researchable researcher)) {
            throw new exceptions.NotResearcherException(
                    "Only researchers can join research projects"
            );
        }

        joinProject(researcher, project);
    }
    
//    public void joinProject(User user, ResearchProject project) {
//        if (!(user instanceof Researchable researcher)) {
//            throw new NotResearcherException("Only researchers can join research projects");
//        }
//
//        project.addParticipant(researcher);
//        researcher.addProject(project);
//        db.save();
//    }
//    

    public void addPaperToProject(ResearchProject project, ResearchPaper paper) {
        project.addPublishedPaper(paper);
        db.save();
    }

    public List<ResearchPaper> getAllPapers() {
        List<ResearchPaper> allPapers = new ArrayList<>();

        for (ResearchProject project : db.getProjects()) {
            allPapers.addAll(project.getPublishedPapers());
        }

        for (var user : db.getUsers()) {
            if (user instanceof Researchable researcher) {
                allPapers.addAll(researcher.getPapers());
            }
        }

        return allPapers;
    }

    public void printAllPapersByCitations() {
        printPapers(getAllPapers(), new PaperByCitationsComparator());
    }

    public void printAllPapersByDate() {
        printPapers(getAllPapers(), new PaperByDateComparator());
    }

    public void printAllPapersByPages() {
        printPapers(getAllPapers(), new PaperByPagesComparator());
    }

    public Researchable getTopCitedResearcher() {
        Researchable top = null;
        int maxCitations = -1;

        for (var user : db.getUsers()) {
            if (user instanceof Researchable researcher) {
                int total = researcher.getPapers()
                        .stream()
                        .mapToInt(ResearchPaper::getCitations)
                        .sum();

                if (total > maxCitations) {
                    maxCitations = total;
                    top = researcher;
                }
            }
        }

        return top;
    }

    public int getTotalCitations(Researchable researcher) {
        return researcher.getPapers()
                .stream()
                .mapToInt(ResearchPaper::getCitations)
                .sum();
    }

    private void printPapers(List<ResearchPaper> papers, Comparator<ResearchPaper> comparator) {
        if (papers.isEmpty()) {
            System.out.println("No research papers found.");
            return;
        }

        papers.stream()
                .sorted(comparator)
                .forEach(System.out::println);
    }
}