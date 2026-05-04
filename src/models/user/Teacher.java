package models.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import models.academic.Course;
import models.academic.Mark;

public class Teacher extends User {

    private String employeeId;
    private String department;
    private String qualification;

    private List<Course> courses;
    private double rating;
    private List<String> teachingSubjects;

    public Teacher(int id, String name, String email, String phoneNumber,
                   String password, String employeeId, String department, String qualification) {
        super(id, name, email, phoneNumber, password, "TEACHER");

        this.employeeId = employeeId;
        this.department = department;
        this.qualification = qualification;

        this.courses = new ArrayList<>();
        this.teachingSubjects = new ArrayList<>();
        this.rating = 0.0;
    }

    public void putMarks(Student s, Mark m) {
        System.out.println(name + " put marks for " + s.getName());
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

    public String getEmployeeId() {
        return employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public String getQualification() {
        return qualification;
    }

    public double getRating() {
        return rating;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<String> getTeachingSubjects() {
        return teachingSubjects;
    }

    public void setDepartment(String department) {
        this.department = department;
        this.updatedAt = System.currentTimeMillis();
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
        this.updatedAt = System.currentTimeMillis();
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Department: " + department);
        System.out.println("Qualification: " + qualification);
        System.out.println("Rating: " + rating);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(employeeId, teacher.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", department='" + department + '\'' +
                ", rating=" + rating +
                '}';
    }
}