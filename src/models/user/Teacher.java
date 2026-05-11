package models.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import models.academic.Course;
import models.academic.Mark;

public class Teacher extends User {

    private String department;
    

    private List<Course> courses;
    private double rating;
    private Set<String> teachingSubjects;

    public Teacher(int id, String name, String email, 
                   String password, String department) {
        super(id, name, email, password, "TEACHER");

        
        this.department = department;
        

        this.courses = new ArrayList<>();
        this.teachingSubjects = new HashSet<>();
        this.rating = 0.0;
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
    public void displayInfo() {
        super.displayInfo();
        
        System.out.println("Department: " + department);
        System.out.println("Rating: " + rating);
    }


   

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\''  +
                ", department='" + department + '\'' +
                ", rating=" + rating +
                '}';
    }
}