package models.user;

import models.academic.Course;
import models.academic.Mark;
import models.academic.Transcript;
import models.research.ResearcherDecorator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student extends User {
    private int studentId;
    private String major;
    private int year;
    private double gpa;
    private int credits;
    private int failCount;
    private ResearcherDecorator supervisor;
    private List<Course> enrolledCourses;
    private Transcript transcript;

    public Student(int id, String name, String email, String phoneNumber,
                   String password, int studentId, String major, int year) {
        super(id, name, email, phoneNumber, password, "STUDENT");
        this.studentId = studentId;
        this.major = major;
        this.year = year;
        this.gpa = 0.0;
        this.credits = 0;
        this.failCount = 0;
        this.enrolledCourses = new ArrayList<>();
        this.transcript = new Transcript(this);
    }

    public void registerCourse(Course course) {
        enrolledCourses.add(course);
    }

    public List<Mark> viewMarks(Course course) {
        return transcript.getMarks(course);
    }

    public void rateTeacher(Teacher teacher, int rating) {
        teacher.setRating(rating);
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public double getGPA() {
        return gpa;
    }

    public void incrementFailCount() {
        failCount++;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", major='" + major + '\'' +
                ", year=" + year +
                ", gpa=" + gpa +
                ", credits=" + credits +
                ", failCount=" + failCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student student)) return false;
        return studentId == student.studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}