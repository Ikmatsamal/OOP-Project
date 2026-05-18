package models.user;

import java.util.ArrayList;
import interfaces.Researchable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import models.research.*;
import java.util.Comparator;
import enums.TeacherTitle;
import models.academic.Course;
import models.academic.Mark;

public class Teacher extends User implements Researchable{

    private String department;
    

    private List<Course> courses;
    private double rating;
    private Set<String> teachingSubjects;
    private ResearcherDecorator researcherPart;
    private TeacherTitle title;

    public Teacher(int id, String name, String email, 
                   String password, String department,TeacherTitle title) {
        super(id, name, email, password, "TEACHER");

        
        this.department = department;
        this.title = title;
        

        this.courses = new ArrayList<>();
        this.teachingSubjects = new HashSet<>();
        this.rating = 0.0;
        this.researcherPart = new ResearcherDecorator(this, 5);
    }

    public void putMark(Student s, Mark m) {

        s.getTranscript().addMark(m);
    
        System.out.println(
                name + " put mark for " + s.getName()
        );
    }

    public List<Course> viewCourses() {
        return courses;
    }

    public void manageCourse(Course c) {
        if (!courses.contains(c)) {
            courses.add(c);
        }
    }

    public void generateMarkReport() {
        System.out.println("Generating mark report for teacher: " + name);
    }

    public List<Student> viewStudents(Course c) {
        return c.getEnrolledStudents();
    }

    public void addRating(double r) {
        this.rating = (this.rating + r) / 2;
    }

    public void addTeachingSubject(String subject) {
        teachingSubjects.add(subject);
    }

    public void gradeStudent(String studentId, double grade) {
        System.out.println(name + " graded student " + studentId + " with: " + grade);
    }

    

    public String getDepartment() {
        return department;
    }
    
    public TeacherTitle getTitle() {
        return title;
    }

    

    public double getRating() {
        return rating;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Set<String> getTeachingSubjects() {
        return teachingSubjects;
    }

    public void setDepartment(String department) {
        this.department = department;
        this.updatedAt = System.currentTimeMillis();
    }
    
    @Override
    public void addPaper(ResearchPaper paper) {
        researcherPart.addPaper(paper);
    }

    @Override
    public void addProject(ResearchProject project) {
        researcherPart.addProject(project);
    }

    @Override
    public void printPapers(Comparator<ResearchPaper> comparator) {
        researcherPart.printPapers(comparator);
    }

    @Override
    public int getHIndex() {
        return researcherPart.getHIndex();
    }

    @Override
    public List<ResearchPaper> getPapers() {
        return researcherPart.getPapers();
    }

   
    

    @Override
    public void displayInfo() {
        super.displayInfo();
        
        System.out.println("Department: " + department);
        System.out.println("Rating: " + rating);
    }


   
    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", title=" + title +
                ", rating=" + rating +
                '}';
    }
}