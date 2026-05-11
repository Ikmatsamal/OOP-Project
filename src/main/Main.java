package main;

import models.user.*;
import models.research.*;
import interfaces.Researchable;
import comparators.PaperByCitationsComparator;
import exceptions.NotResearcherException;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // create user
    	User user = new Student(
    	        1,
    	        "Aidana",
    	        "aidana@example.com",
    	        
    	        "1234",
    	        "Computer Science",
    	        2
    	);

        // wrap user as researcher (Decorator)
        Researchable researcher = new ResearcherDecorator(user, 5);

        // create papers
        ResearchPaper p1 = new ResearchPaper(
                "AI Research",
                List.of("Author1"),
                "Journal1",
                10,
                LocalDate.now(),
                "doi1",
                50
        );

        ResearchPaper p2 = new ResearchPaper(
                "ML Study",
                List.of("Author2"),
                "Journal2",
                8,
                LocalDate.now(),
                "doi2",
                30
        );

        // add papers
        researcher.addPaper(p1);
        researcher.addPaper(p2);

        // sort papers (Strategy)
        System.out.println("Sorted papers:");
        researcher.printPapers(new PaperByCitationsComparator());

        // create project
        ResearchProject project = new ResearchProject("AI Project");

        // add participant
        project.addParticipant(researcher);

        // add paper to project
        project.addPublishedPaper(p1);

        // test exception
        try {
            project.addParticipant(null);
        } catch (NotResearcherException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        
     // test fail count exception
        try {
            Student student = (Student) user;

            student.incrementFailCount();
            student.incrementFailCount();
            student.incrementFailCount();
            student.incrementFailCount();

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // print project
        System.out.println(project);
    }
}