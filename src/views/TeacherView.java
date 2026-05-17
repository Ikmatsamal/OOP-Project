package views;

import java.util.List;
import java.util.Scanner;
import models.system.Message;

import models.academic.Course;
import models.system.News;
import models.user.Student;
import models.user.Teacher;

import patterns.singleton.DatabaseSingleton;

import services.TeacherService;

public class TeacherView {

    private final Scanner sc = new Scanner(System.in);
    private final TeacherService teacherService = new TeacherService();

    public void show(Teacher teacher) {

        while (true) {

            System.out.println("\n===== TEACHER MENU =====");
            System.out.println("1. View my courses");
            System.out.println("2. Manage course");
            System.out.println("3. View news");
            System.out.println("4. Generate report");
            System.out.println("5. Send message");
            System.out.println("6. Send complaint");
            System.out.println("7. View inbox");
            System.out.println("8. View sent messages");
            System.out.println("0. Logout");

            System.out.print("Choose option: ");

            int choice = readInt();

            switch (choice) {

            case 1 -> viewCourses(teacher);

            case 2 -> manageCourse(teacher);

            case 3 -> viewNews();

            case 4 -> teacherService.generateReport(teacher);
            case 5 -> sendMessage(teacher);
            case 6 -> sendComplaint(teacher);
            case 7 -> viewInbox(teacher);
            case 8 -> viewSentMessages(teacher);

            case 0 -> {
                System.out.println("Logout...");
                return;
            }

            default -> System.out.println("Invalid option");
        }
        }
    }

    // VIEW COURSES

    private void viewCourses(Teacher teacher) {

        List<Course> courses =
                teacherService.getTeacherCourses(teacher);

        if (courses.isEmpty()) {

            System.out.println("No assigned courses");
            return;
        }

        System.out.println("\n===== MY COURSES =====");

        for (Course course : courses) {

            System.out.println(
                    course.getCourseId()
                            + " - "
                            + course.getName()
            );
        }
    }

    // MANAGE COURSE

    private void manageCourse(Teacher teacher) {

        Course course = chooseCourse(teacher);

        if (course == null) return;

        while (true) {

            System.out.println("\n===== MANAGE COURSE =====");
            System.out.println("Course: " + course.getName());

            System.out.println("1. View students");
            System.out.println("2. Put marks");
            System.out.println("0. Back");

            System.out.print("Choose option: ");

            int choice = readInt();

            switch (choice) {

                case 1 -> viewStudents(course);

                case 2 -> putMark(teacher, course);

                case 0 -> {
                    return;
                }

                default -> System.out.println("Invalid option");
            }
        }
    }

    // VIEW STUDENTS

    private void viewStudents(Course course) {

        List<Student> students =
                course.getEnrolledStudents();

        if (students.isEmpty()) {

            System.out.println("No students enrolled");
            return;
        }

        System.out.println("\n===== STUDENTS =====");

        for (Student student : students) {

            System.out.println(
                    "ID: " + student.getId()
                            + " | Name: " + student.getName()
                            + " | GPA: " + student.getGPA()
            );
        }
    }

    // PUT MARK

    private void putMark(Teacher teacher, Course course) {

        List<Student> students =
                course.getEnrolledStudents();

        if (students.isEmpty()) {

            System.out.println("No students enrolled");
            return;
        }

        System.out.println("\n===== STUDENTS =====");

        for (int i = 0; i < students.size(); i++) {

            Student student = students.get(i);

            System.out.println(
                    (i + 1)
                            + ". "
                            + student.getName()
            );
        }

        System.out.print("Choose student: ");

        int choice = readInt();

        if (choice < 1 || choice > students.size()) {

            System.out.println("Invalid student");
            return;
        }

        Student student =
                students.get(choice - 1);

                System.out.print("Attestation 1 (0-30): ");

                double att1 = readDouble();
                
                if (att1 < 0 || att1 > 30) {
                
                    System.out.println(
                            "Attestation 1 must be between 0 and 30"
                    );
                
                    return;
                }
                
                System.out.print("Attestation 2 (0-30): ");
                
                double att2 = readDouble();
                
                if (att2 < 0 || att2 > 30) {
                
                    System.out.println(
                            "Attestation 2 must be between 0 and 30"
                    );
                
                    return;
                }
                
                System.out.print("Final exam (0-40): ");
                
                double finalExam = readDouble();
                
                if (finalExam < 0 || finalExam > 40) {
                
                    System.out.println(
                            "Final exam must be between 0 and 40"
                    );
                
                    return;
        }

        teacherService.putMark(
                teacher,
                student,
                course,
                att1,
                att2,
                finalExam
        );

        System.out.println("Mark added successfully");
    }

    // VIEW NEWS

    private void viewNews() {

        List<News> news =
                DatabaseSingleton
                        .getInstance()
                        .getNews();

        if (news.isEmpty()) {

            System.out.println("No news available");
            return;
        }

        System.out.println("\n===== NEWS =====");

        news.forEach(System.out::println);
    }

    // CHOOSE COURSE

    private Course chooseCourse(Teacher teacher) {

        List<Course> courses =
                teacherService.getTeacherCourses(teacher);

        if (courses.isEmpty()) {

            System.out.println("No assigned courses");
            return null;
        }

        System.out.println("\n===== COURSES =====");

        for (int i = 0; i < courses.size(); i++) {

            Course course = courses.get(i);

            System.out.println(
                    (i + 1)
                            + ". "
                            + course.getCourseId()
                            + " - "
                            + course.getName()
            );
        }

        System.out.print("Choose course: ");

        int choice = readInt();

        if (choice < 1 || choice > courses.size()) {

            System.out.println("Invalid course");
            return null;
        }

        return courses.get(choice - 1);
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
                        "Enter valid number: "
                );
            }
        }
    }

    // READ DOUBLE

    private double readDouble() {

        while (true) {

            try {

                return Double.parseDouble(
                        sc.nextLine()
                );

            } catch (Exception e) {

                System.out.print(
                        "Enter valid number: "
                );
            }
        }
    }
    
 // SEND MESSAGE

    private void sendMessage(Teacher teacher) {
        System.out.print("Receiver email: ");
        String email = sc.nextLine();

        System.out.print("Message: ");
        String text = sc.nextLine();

        try {
            teacherService.sendMessage(teacher, email, text);
            System.out.println("Message sent successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendComplaint(Teacher teacher) {
        System.out.print("Complaint text: ");
        String text = sc.nextLine();

        try {
            teacherService.sendComplaint(teacher, text);
            System.out.println("Complaint submitted successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void viewInbox(Teacher teacher) {
        List<Message> messages =
                teacherService.getInbox(teacher);

        if (messages.isEmpty()) {
            System.out.println("Inbox is empty");
            return;
        }

        System.out.println("\n===== INBOX =====");
        messages.forEach(System.out::println);
    }

    private void viewSentMessages(Teacher teacher) {
        List<Message> messages =
                teacherService.getSentMessages(teacher);

        if (messages.isEmpty()) {
            System.out.println("No sent messages");
            return;
        }

        System.out.println("\n===== SENT MESSAGES =====");
        messages.forEach(System.out::println);
    }
}