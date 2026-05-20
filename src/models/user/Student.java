package models.user;

import models.academic.Course;
import enums.Major;
import interfaces.Researchable;
import models.academic.Mark;
import models.academic.Transcript;
import models.research.ResearcherDecorator;

import exceptions.CreditLimitExceededException;
import exceptions.TooManyFailsException;
import exceptions.InvalidSupervisorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student extends User implements Researchable{

    
	private Major major;
    private int year;
    private double gpa;
    private int credits;
    private int failCount;
    private Researchable supervisor;
    private List<Course> enrolledCourses;
    private Transcript transcript;
    private ResearcherDecorator researcherPart;

    public Student(int id, String name, String email,
                   String password, Major major, int year) {
    	super(id, name, email, password, "STUDENT");
    	this.major = major;
        this.year = year;
        this.gpa = 0.0;
        this.credits = 0;
        this.failCount = 0;
        this.enrolledCourses = new ArrayList<>();
        this.transcript = new Transcript(this);
        this.researcherPart = new ResearcherDecorator(this, 0);
    }

    
    public void registerCourse(Course course) {
        if (enrolledCourses.contains(course)) {
            System.out.println("Student is already registered for this course");
            return;
        }

        if (credits + course.getCredits() > 21) {
            throw new CreditLimitExceededException("Credit limit exceeded (max 21)");
        }

        enrolledCourses.add(course);
        credits += course.getCredits();

        course.enrollStudent(this);
    }

    public List<Mark> viewMarks(Course course) {
        return transcript.getMarks(course);
    }

    public void rateTeacher(Teacher teacher, int rating) {
        teacher.addRating(rating);
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public double getGPA() {
        return transcript.calculateGPA();
    }
    public Major getMajor() {
        return major;
    }

    
    public void incrementFailCount() {
        failCount++;

        if (failCount > 3) {
            throw new TooManyFailsException("Too many failed courses");
        }
    }
    
    public int getYear() {
        return year;
    }

   
//    public void setSupervisor(ResearcherDecorator supervisor) {
//        if (supervisor.getHIndex() < 3) {
//            throw new InvalidSupervisorException("Supervisor must have h-index >= 3");
//        }
//        this.supervisor = supervisor;
//    }
    
    public void setSupervisor(Researchable supervisor) {
        if (this.year < 4) {
            throw new InvalidSupervisorException("Only 4th year students can have supervisor");
        }

        if (supervisor.getHIndex() < 3) {
            throw new InvalidSupervisorException("Supervisor must have h-index >= 3");
        }

        this.supervisor = supervisor;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", major='" + major + '\'' +
                ", year=" + year +
                ", gpa=" + gpa +
                ", credits=" + credits +
                ", failCount=" + failCount +
                '}';
    }
    
    @Override
    public void addPaper(models.research.ResearchPaper paper) {
        researcherPart.addPaper(paper);
    }

    @Override
    public void addProject(models.research.ResearchProject project) {
        researcherPart.addProject(project);
    }

    @Override
    public void printPapers(java.util.Comparator<models.research.ResearchPaper> comparator) {
        researcherPart.printPapers(comparator);
    }

    @Override
    public int getHIndex() {
        return researcherPart.getHIndex();
    }

    @Override
    public java.util.List<models.research.ResearchPaper> getPapers() {
        return researcherPart.getPapers();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;
        return this.getId() == student.getId();
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(getId());
    }

}