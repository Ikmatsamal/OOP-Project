package views;

import models.user.Manager;
import models.user.Student;
import models.user.Teacher;
import models.academic.Course;

import patterns.singleton.DatabaseSingleton;

import services.CourseService;

import java.util.List;
import java.util.Scanner;

public class ManagerView {

    private final Scanner sc = new Scanner(System.in);

    private final CourseService courseService =
            new CourseService();

    public void show(Manager manager) {

        while (true) {

            System.out.println("\n===== MANAGER MENU =====");
            System.out.println("1. View students");
            System.out.println("2. View teachers");
            System.out.println("3. View courses");
            System.out.println("4. Assign course to teacher");
            System.out.println("5. Approve student registration");
            System.out.println("6. Create report");
            System.out.println("0. Logout");

            System.out.print("Choose option: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    viewStudents();
                    break;

                case 2:
                    viewTeachers();
                    break;

                case 3:
                    viewCourses();
                    break;

                case 4:
                    assignCourseToTeacher();
                    break;

                case 5:
                    approveRegistration();
                    break;

                case 6:
                    manager.createReport();
                    break;

                case 0:
                    System.out.println("Logout...");
                    return;

                default:
                    System.out.println("Invalid option");
            }
        }
    }

    // VIEW STUDENTS

    private void viewStudents() {

        List<Student> students =
                DatabaseSingleton.getInstance()
                        .getUsers()
                        .stream()
                        .filter(user -> user instanceof Student)
                        .map(user -> (Student) user)
                        .toList();

        if (students.isEmpty()) {
            System.out.println("No students found");
            return;
        }

        System.out.println("\n===== STUDENTS =====");

        students.forEach(System.out::println);
    }

    // VIEW TEACHERS

    private void viewTeachers() {

        List<Teacher> teachers =
                DatabaseSingleton.getInstance()
                        .getUsers()
                        .stream()
                        .filter(user -> user instanceof Teacher)
                        .map(user -> (Teacher) user)
                        .toList();

        if (teachers.isEmpty()) {
            System.out.println("No teachers found");
            return;
        }

        System.out.println("\n===== TEACHERS =====");

        teachers.forEach(System.out::println);
    }

    // VIEW COURSES

    private void viewCourses() {

        List<Course> courses =
                courseService.getAllCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses available");
            return;
        }

        System.out.println("\n===== COURSES =====");

        courses.forEach(System.out::println);
    }

    // ASSIGN COURSE

    private void assignCourseToTeacher() {

        System.out.print("Enter course ID: ");
        String courseId = sc.nextLine();

        System.out.print("Enter teacher email: ");
        String teacherEmail = sc.nextLine();

        Course course =
                courseService.findCourseById(courseId)
                        .orElse(null);

        if (course == null) {
            System.out.println("Course not found");
            return;
        }

        Teacher teacher =
                DatabaseSingleton.getInstance()
                        .getUsers()
                        .stream()
                        .filter(user -> user instanceof Teacher)
                        .map(user -> (Teacher) user)
                        .filter(t -> t.getEmail()
                                .equalsIgnoreCase(teacherEmail))
                        .findFirst()
                        .orElse(null);

        if (teacher == null) {
            System.out.println("Teacher not found");
            return;
        }

        teacher.manageCourse(course);

        System.out.println("Course assigned successfully");
    }

    // APPROVE REGISTRATION

    private void approveRegistration() {

        System.out.print("Student email: ");
        String studentEmail = sc.nextLine();

        System.out.print("Course ID: ");
        String courseId = sc.nextLine();

        Student student =
                DatabaseSingleton.getInstance()
                        .getUsers()
                        .stream()
                        .filter(user -> user instanceof Student)
                        .map(user -> (Student) user)
                        .filter(s -> s.getEmail()
                                .equalsIgnoreCase(studentEmail))
                        .findFirst()
                        .orElse(null);

        if (student == null) {
            System.out.println("Student not found");
            return;
        }

        Course course =
                courseService.findCourseById(courseId)
                        .orElse(null);

        if (course == null) {
            System.out.println("Course not found");
            return;
        }

        try {

            course.enrollStudent(student);

            System.out.println(
                    "Registration approved successfully"
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    // READ INT

    private int readInt() {

        while (true) {

            try {

                return Integer.parseInt(
                        sc.nextLine()
                );

            } catch (Exception e) {

                System.out.print(
                        "Enter number: "
                );
            }
        }
    }
}