package views;

import comparators.PaperByCitationsComparator;
import comparators.PaperByDateComparator;
import comparators.PaperByPagesComparator;
import interfaces.Researchable;
import models.research.ResearchPaper;
import models.research.ResearchProject;
import patterns.singleton.DatabaseSingleton;
import services.ResearchService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ResearchView {

    private final Scanner sc = new Scanner(System.in);
    private final ResearchService researchService = new ResearchService();

    public void show(Researchable researcher) {
        while (true) {
            System.out.println("\n--- Research Menu ---");
            System.out.println("1. View my papers");
            System.out.println("2. Add research paper");
            System.out.println("3. Print my papers by citations");
            System.out.println("4. Print my papers by date");
            System.out.println("5. Print my papers by pages");
            System.out.println("6. View all university papers by citations");
            System.out.println("7. View all university papers by date");
            System.out.println("8. View all university papers by pages");
            System.out.println("9. Create research project");
            System.out.println("10. Join research project");
            System.out.println("11. Add paper to project");
            System.out.println("12. View top cited researcher");
            System.out.println("0. Back");

            int choice = readInt("Choose: ");

            switch (choice) {
                case 1 -> viewMyPapers(researcher);
                case 2 -> addResearchPaper(researcher);
                case 3 -> researcher.printPapers(new PaperByCitationsComparator());
                case 4 -> researcher.printPapers(new PaperByDateComparator());
                case 5 -> researcher.printPapers(new PaperByPagesComparator());
                case 6 -> researchService.printAllPapersByCitations();
                case 7 -> researchService.printAllPapersByDate();
                case 8 -> researchService.printAllPapersByPages();
                case 9 -> createProject();
                case 10 -> joinProject(researcher);
                case 11 -> addPaperToProject(researcher);
                case 12 -> printTopCitedResearcher();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void viewMyPapers(Researchable researcher) {
        if (researcher.getPapers().isEmpty()) {
            System.out.println("You have no research papers.");
            return;
        }

        researcher.getPapers().forEach(System.out::println);
    }

    private void addResearchPaper(Researchable researcher) {
        ResearchPaper paper = inputPaper();
        researchService.addPaper(researcher, paper);
        System.out.println("Research paper added successfully.");
    }

    private void createProject() {
        System.out.print("Project topic: ");
        String topic = sc.nextLine();

        researchService.createProject(topic);

        System.out.println("Research project created successfully.");
    }

    private void joinProject(Researchable researcher) {
        ResearchProject project = chooseProject();

        if (project == null) {
            return;
        }

        try {
            researchService.joinProject(researcher, project);
            System.out.println("Joined research project successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addPaperToProject(Researchable researcher) {
        ResearchProject project = chooseProject();

        if (project == null) {
            return;
        }

        List<ResearchPaper> papers = researcher.getPapers();

        if (papers.isEmpty()) {
            System.out.println("You have no papers. Add a research paper first.");
            return;
        }

        System.out.println("=== My Research Papers ===");

        for (int i = 0; i < papers.size(); i++) {
            System.out.println((i + 1) + ". " + papers.get(i));
        }

        int choice = readInt("Choose paper: ");

        if (choice < 1 || choice > papers.size()) {
            System.out.println("Invalid paper selection.");
            return;
        }

        ResearchPaper selectedPaper = papers.get(choice - 1);

        researchService.addPaperToProject(project, selectedPaper);

        System.out.println("Paper added to project successfully.");
    }

    private void printTopCitedResearcher() {
        Researchable top = researchService.getTopCitedResearcher();

        if (top == null) {
            System.out.println("No researchers found.");
            return;
        }

        System.out.println("Top cited researcher:");
        System.out.println(top);
        System.out.println("Total citations: " + researchService.getTotalCitations(top));
    }

    private ResearchProject chooseProject() {
        List<ResearchProject> projects = DatabaseSingleton.getInstance().getProjects();

        if (projects.isEmpty()) {
            System.out.println("No research projects available.");
            return null;
        }

        for (int i = 0; i < projects.size(); i++) {
            System.out.println((i + 1) + ". " + projects.get(i));
        }

        int choice = readInt("Choose project: ");

        if (choice < 1 || choice > projects.size()) {
            System.out.println("Invalid project.");
            return null;
        }

        return projects.get(choice - 1);
    }

    private ResearchPaper inputPaper() {
        System.out.print("Title: ");
        String title = sc.nextLine();

        System.out.print("Authors separated by comma: ");
        String authorsInput = sc.nextLine();

        List<String> authors = new ArrayList<>();

        for (String author : authorsInput.split(",")) {
            authors.add(author.trim());
        }

        System.out.print("Journal: ");
        String journal = sc.nextLine();

        int pages = readInt("Pages: ");

        System.out.print("Publication date yyyy-mm-dd: ");
        LocalDate date = LocalDate.parse(sc.nextLine());

        System.out.print("DOI: ");
        String doi = sc.nextLine();

        int citations = readInt("Citations: ");

        return new ResearchPaper(
                title,
                authors,
                journal,
                pages,
                date,
                doi,
                citations
        );
    }

    private int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }
}