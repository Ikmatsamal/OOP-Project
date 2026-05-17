package services;

import models.academic.Course;
import models.academic.Mark;
import models.user.Student;

import java.util.List;

public class ReportService {

    public void printTranscript(Student student) {
        System.out.println(student.getTranscript());
    }

    public void printMarksForCourse(Student student, Course course) {
        List<Mark> marks = student.viewMarks(course);

        if (marks.isEmpty()) {
            System.out.println("No marks found for course: " + course.getName());
            return;
        }

        System.out.println("=== Marks for " + course.getName() + " ===");
        marks.forEach(System.out::println);
    }
}

