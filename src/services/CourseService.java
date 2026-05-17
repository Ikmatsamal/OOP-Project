package services;

import models.academic.Course;
import models.user.Student;
import patterns.singleton.DatabaseSingleton;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CourseService {

    private final DatabaseSingleton db = DatabaseSingleton.getInstance();

    public List<Course> getAllCourses() {
        return db.getCourses();
    }

    public List<Course> getAvailableCoursesFor(Student student) {
        return db.getCourses().stream()
                .filter(course -> !course.getEnrolledStudents().contains(student))
                .collect(Collectors.toList());
    }

    public List<Course> getEnrolledCourses(Student student) {
        return db.getCourses().stream()
                .filter(course -> course.getEnrolledStudents().contains(student))
                .collect(Collectors.toList());
    }

    public Optional<Course> findCourseById(String courseId) {
        return db.getCourses().stream()
                .filter(course -> course.getCourseId().equalsIgnoreCase(courseId.trim()))
                .findFirst();
    }

    public void enrollStudent(Student student, Course course) {
        if (course.getEnrolledStudents().contains(student)) {
            throw new IllegalStateException("You are already enrolled in this course.");
        }

        student.registerCourse(course);
        course.enrollStudent(student);
        db.save();
    }
}

