package services;

import models.academic.Course;
import utils.Logger;
import enums.Major;
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
        
        Logger.info(student.getName() + " enrolled in course " + course.getName());
    }
    
    
    public void addCourseForRegistration(
            String id,
            String name,
            String description,
            Major major,
            int credits
    ) {

        boolean exists = db.getCourses()
                .stream()
                .anyMatch(course ->
                        course.getCourseId()
                                .equalsIgnoreCase(id));

        if (exists) {
            throw new IllegalArgumentException(
                    "Course with this ID already exists."
            );
        }

        Course course = new Course(
                id,
                name,
                description,
                major,
                credits
        );

        
        
        db.addCourse(course);

        Logger.info("Course added for registration: " + course.getName());
    }
}

