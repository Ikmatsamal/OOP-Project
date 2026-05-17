package views;

import java.util.List;
import java.util.Scanner;

import models.academic.Course;
import models.user.Student;
import models.user.Teacher;
import services.CourseService;
import services.ReportService;
import utils.Logger;

public class    StudentView {

    private final Scanner sc = new Scanner(System.in);
    private final CourseService courseService = new CourseService();
    private final ReportService reportService = new ReportService();

    public void show(Student student) {

        while (true) {
            System.out.println("\n=== STUDENT MENU ===");
            System.out.println("1. View available courses");
            System.out.println("2. Enroll in a course");
            System.out.println("3. View my enrolled courses");
            System.out.println("4. View transcript");
            System.out.println("5. View marks for a course");
            System.out.println("6. View teacher info for a course");
            System.out.println("7. Rate a teacher");
            System.out.println("8. Research menu");
            System.out.println("0. Logout");

            int choice = readInt("Choose option: ");

            switch (choice) {
                case 1 -> viewAvailableCourses(student);
                case 2 -> enrollInCourse(student);
                case 3 -> viewMyCourses(student);
                case 4 -> viewTranscript(student);
                case 5 -> viewMarks(student);
                case 6 -> viewCourseTeacherInfo(student);
                case 7 -> rateTeacher(student);
                case 8 -> new ResearchView().show(student);
                case 0 -> {
                    Logger.info("Logout: " + student.getEmail());
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void viewAvailableCourses(Student student) {
        List<Course> courses = courseService.getAvailableCoursesFor(student);

        if (courses.isEmpty()) {
            System.out.println("No available courses at this time.");
            return;
        }

        System.out.println("=== Available Courses ===");
        for (Course course : courses) {
            System.out.printf("%s - %s (%d credits) [%s]\n",
                    course.getCourseId(), course.getName(), course.getCredits(), course.getMajor());
        }
    }

    private void enrollInCourse(Student student) {
        List<Course> courses = courseService.getAvailableCoursesFor(student);

        if (courses.isEmpty()) {
            System.out.println("No available courses to enroll.");
            return;
        }

        viewAvailableCourses(student);
        System.out.print("Enter course id to enroll: ");
        String courseId = sc.nextLine().trim();

        courseService.findCourseById(courseId).ifPresentOrElse(course -> {
            try {
                courseService.enrollStudent(student, course);
                System.out.println("Enrolled successfully in " + course.getName());
            } catch (Exception e) {
                System.out.println("Enrollment failed: " + e.getMessage());
            }
        }, () -> System.out.println("Course not found: " + courseId));
    }

    private void viewMyCourses(Student student) {
        List<Course> courses = courseService.getEnrolledCourses(student);

        if (courses.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
            return;
        }

        System.out.println("=== My Courses ===");
        for (Course course : courses) {
            System.out.printf("%s - %s (%d credits) [%s]\n",
                    course.getCourseId(), course.getName(), course.getCredits(), course.getMajor());
        }
    }

    private void viewTranscript(Student student) {
        reportService.printTranscript(student);
    }

    private void viewMarks(Student student) {
        List<Course> courses = courseService.getEnrolledCourses(student);

        if (courses.isEmpty()) {
            System.out.println("You are not enrolled in any course yet.");
            return;
        }

        System.out.println("=== Select a course to view marks ===");
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            System.out.printf("%d. %s - %s\n", i + 1, course.getCourseId(), course.getName());
        }

        int choice = readInt("Choose course number: ");
        if (choice < 1 || choice > courses.size()) {
            System.out.println("Invalid course selection.");
            return;
        }

        reportService.printMarksForCourse(student, courses.get(choice - 1));
    }

    private void viewCourseTeacherInfo(Student student) {
        List<Course> courses = courseService.getAllCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }

        System.out.println("=== Select a course to view instructors ===");
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            System.out.printf("%d. %s - %s\n", i + 1, course.getCourseId(), course.getName());
        }

        int choice = readInt("Choose course number: ");
        if (choice < 1 || choice > courses.size()) {
            System.out.println("Invalid course selection.");
            return;
        }

        Course course = courses.get(choice - 1);
        List<Teacher> instructors = course.getInstructors();

        if (instructors.isEmpty()) {
            System.out.println("No instructors assigned to this course.");
            return;
        }

        System.out.println("=== Instructors for " + course.getName() + " ===");
        for (Teacher t : instructors) {
            System.out.printf("Name: %s, Email: %s, Dept: %s, Rating: %.2f\n",
                    t.getName(), t.getEmail(), t.getDepartment(), t.getRating());
        }
    }

    private void rateTeacher(Student student) {
        List<Course> courses = courseService.getEnrolledCourses(student);

        if (courses.isEmpty()) {
            System.out.println("You are not enrolled in any courses to rate their teachers.");
            return;
        }

        System.out.println("=== Select a course to choose a teacher to rate ===");
        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            System.out.printf("%d. %s - %s\n", i + 1, c.getCourseId(), c.getName());
        }

        int cchoice = readInt("Choose course number: ");
        if (cchoice < 1 || cchoice > courses.size()) {
            System.out.println("Invalid course selection.");
            return;
        }

        Course course = courses.get(cchoice - 1);
        List<Teacher> instructors = course.getInstructors();

        if (instructors.isEmpty()) {
            System.out.println("No instructors assigned to this course.");
            return;
        }

        System.out.println("=== Select an instructor to rate ===");
        for (int i = 0; i < instructors.size(); i++) {
            Teacher t = instructors.get(i);
            System.out.printf("%d. %s (%s)\n", i + 1, t.getName(), t.getEmail());
        }

        int tchoice = readInt("Choose instructor number: ");
        if (tchoice < 1 || tchoice > instructors.size()) {
            System.out.println("Invalid instructor selection.");
            return;
        }

        Teacher teacher = instructors.get(tchoice - 1);
        int rating = readInt("Enter rating (1-5): ");
        if (rating < 1 || rating > 5) {
            System.out.println("Rating must be between 1 and 5.");
            return;
        }

        student.rateTeacher(teacher, rating);
        System.out.println("Thank you. Rating submitted.");
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
