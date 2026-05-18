package models.academic;

import models.user.Student;
import enums.Major;
import models.user.Teacher;

import java.util.ArrayList;
import java.util.List;

import java.io.Serializable;

import java.util.List;

public class Course implements Serializable{
    private String courseId;
    private String name;
    private String description;
    private Major major;
    private int credits;
    private List<Teacher> instructors;
    private List<Student> enrolledStudents;
    private List<Lesson> lessons;

    public Course(String courseId, String name, String description,  Major major, int credits) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        this.major = major;
        this.credits = credits;
        this.instructors = new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
        this.lessons = new ArrayList<>();
    }

    public void addInstructor(Teacher t) {
        if (!instructors.contains(t)) {
            instructors.add(t);
            System.out.println(t.getName() + " added as instructor to " + name);
        }
    }

    public void addLesson(Lesson l) {
        lessons.add(l);
    }

    public void enrollStudent(Student s) {
        if (!enrolledStudents.contains(s)) {
            enrolledStudents.add(s);
            
        }
    }
    

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course c = (Course) o;
        return courseId.equals(c.courseId);
    }

    public int hashCode() {
        return courseId.hashCode();
    }

    // Getters
    public String getCourseId() { return courseId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Major getMajor() { return major; }
    public int getCredits() { return credits; }
    public List<Teacher> getInstructors() { return instructors; }
    public List<Student> getEnrolledStudents() { return enrolledStudents; }
    public List<Lesson> getLessons() { return lessons; }

    @Override
    public String toString() {
        return "Course{id='" + courseId + "', name='" + name + "', credits=" + credits + "}";
    }
}