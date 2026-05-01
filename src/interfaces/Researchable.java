package interfaces;

import models.research.ResearchPaper;
import models.research.ResearchProject;

import java.util.Comparator;
import java.util.List;

public interface Researchable {

    void addPaper(ResearchPaper paper);

    void addProject(ResearchProject project);

    void printPapers(Comparator<ResearchPaper> comparator);

    int getHIndex();

    List<ResearchPaper> getPapers();
}