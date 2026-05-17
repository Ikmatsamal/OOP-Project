package views;

import models.user.Manager;
import models.user.Student;
import models.user.Teacher;
import models.academic.Course;
import models.system.News;
import patterns.singleton.DatabaseSingleton;

import services.CourseService;

import java.util.List;
import java.util.Scanner;

import patterns.observer.NewsPublisher;

import models.system.News;
import patterns.observer.NewsPublisher;

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
            System.out.println("7. View students by GPA");
            System.out.println("8. View teachers alphabetically");
            System.out.println("9. Publish news");
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
                
                case 7:
                    viewStudentsSortedByGPA();
                    break;
                    
                case 8:
                    viewTeachersAlphabetically();
                    break;
                    
                case 9:
                    publishNews(manager);
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

    // VIEW STUDENTS SORTED BY GPA

    private void viewStudentsSortedByGPA() {

        System.out.println("\n===== STUDENTS BY GPA =====");

        DatabaseSingleton.getInstance()
                .getUsers()
                .stream()
                .filter(user -> user instanceof Student)
                .map(user -> (Student) user)
                .sorted((s1, s2) ->
                        Double.compare(
                                s2.getGPA(),
                                s1.getGPA()
                        ))
                .forEach(System.out::println);
    }

    // VIEW TEACHERS ALPHABETICALLY

    private void viewTeachersAlphabetically() {

        System.out.println("\n===== TEACHERS A-Z =====");

        DatabaseSingleton.getInstance()
                .getUsers()
                .stream()
                .filter(user -> user instanceof Teacher)
                .map(user -> (Teacher) user)
                .sorted((t1, t2) ->
                        t1.getName()
                                .compareToIgnoreCase(
                                        t2.getName()
                                ))
                .forEach(System.out::println);
    }

    // PUBLISH NEWS

    private void publishNews() {

        System.out.print("Enter news: ");

        String news = sc.nextLine();

        NewsPublisher publisher =
                new NewsPublisher();

        publisher.notifyObservers(news);

        System.out.println("News published successfully");
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

    // METHOD

    private void publishNews(Manager manager) {

        System.out.print("Enter news title: ");
        String title = sc.nextLine();
    
        System.out.print("Enter news content: ");
        String content = sc.nextLine();
    
        if (title.isBlank() || content.isBlank()) {
            System.out.println("News cannot be empty");
            return;
        }
    
        News news = new News(
                title,
                content,
                java.time.LocalDateTime.now(),
                manager.getName()
        );
    
        DatabaseSingleton.getInstance().addNews(news);
    
        System.out.println("News published successfully");
    
        NewsPublisher publisher = new NewsPublisher();
    
        DatabaseSingleton.getInstance()
                .getUsers()
                .forEach(user -> publisher.addObserver(user));
    
        publisher.notifyObservers(
                "New news published: " + title
        );
    }
}