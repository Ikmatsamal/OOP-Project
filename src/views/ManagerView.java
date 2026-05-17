package views;

import models.user.Manager;
import services.ManagerService;
import models.user.Student;
import models.user.Teacher;
import models.academic.Course;

import enums.Major;
import services.CourseService;
import java.util.List;
import java.util.Scanner;
import models.system.News;
import models.system.Message;
import services.MessageService;


public class ManagerView {

    private final Scanner sc = new Scanner(System.in);

    private final CourseService courseService =
            new CourseService();
    private final ManagerService managerService =
            new ManagerService();
    private final MessageService messageService =
            new MessageService();

    

    	public void show(Manager manager) {

    	    while (true) {

    	        System.out.println("\n===== MANAGER MENU =====");
    	        System.out.println("1. Users");
    	        System.out.println("2. Courses");
    	        System.out.println("3. Reports");
    	        System.out.println("4. News");
    	        System.out.println("5. Messages");
    	        System.out.println("0. Logout");

    	        int choice = readInt("Choose option: ");

    	        switch (choice) {
    	            case 1 -> usersMenu();
    	            case 2 -> coursesMenu();
    	            case 3 -> reportsMenu();
    	            case 4 -> newsMenu(manager);
    	            case 5 -> messagesMenu(manager);
    	            case 0 -> {
    	                System.out.println("Logout...");
    	                return;
    	            }
    	            default -> System.out.println("Invalid option");
    	        }
    	    }
    	}
    	
    	private void usersMenu() {

    	    while (true) {
    	        System.out.println("\n===== USERS MENU =====");
    	        System.out.println("1. View students");
    	        System.out.println("2. View teachers");
    	        System.out.println("3. View students by GPA");
    	        System.out.println("4. View teachers alphabetically");
    	        System.out.println("0. Back");

    	        int choice = readInt("Choose option: ");

    	        switch (choice) {
    	            case 1 -> viewStudents();
    	            case 2 -> viewTeachers();
    	            case 3 -> viewStudentsSortedByGPA();
    	            case 4 -> viewTeachersAlphabetically();
    	            case 0 -> {
    	                return;
    	            }
    	            default -> System.out.println("Invalid option");
    	        }
    	    }
    	}
    	
    	private void coursesMenu() {

    	    while (true) {
    	        System.out.println("\n===== COURSES MENU =====");
    	        System.out.println("1. View courses");
    	        System.out.println("2. Assign course to teacher");
    	        System.out.println("3. Add course for registration");
    	        System.out.println("4. Approve student registration");
    	        System.out.println("0. Back");

    	        int choice = readInt("Choose option: ");

    	        switch (choice) {
    	            case 1 -> viewCourses();
    	            case 2 -> assignCourseToTeacher();
    	            case 3 -> addCourseForRegistration();
    	            case 4 -> approveRegistration();
    	            case 0 -> {
    	                return;
    	            }
    	            default -> System.out.println("Invalid option");
    	        }
    	    }
    	}
    	
    	private void reportsMenu() {

    	    while (true) {
    	        System.out.println("\n===== REPORTS MENU =====");
    	        System.out.println("1. Create report");
    	        System.out.println("0. Back");

    	        int choice = readInt("Choose option: ");

    	        switch (choice) {
    	            case 1 -> createReport();
    	            case 0 -> {
    	                return;
    	            }
    	            default -> System.out.println("Invalid option");
    	        }
    	    }
    	}
    	
    	private void newsMenu(Manager manager) {

    	    while (true) {
    	        System.out.println("\n===== NEWS MENU =====");
    	        System.out.println("1. Publish news");
    	        System.out.println("2. View news");
    	        System.out.println("0. Back");

    	        int choice = readInt("Choose option: ");

    	        switch (choice) {
    	            case 1 -> publishNews(manager);
    	            case 2 -> viewNews();
    	            case 0 -> {
    	                return;
    	            }
    	            default -> System.out.println("Invalid option");
    	        }
    	    }
    	}
    	
    	private void messagesMenu(Manager manager) {

    	    while (true) {
    	        System.out.println("\n===== MESSAGES MENU =====");
    	        System.out.println("1. View inbox");
    	        System.out.println("2. View sent messages");
    	        System.out.println("3. Send message");
    	        System.out.println("0. Back");

    	        int choice = readInt("Choose option: ");

    	        switch (choice) {
    	            case 1 -> viewInbox(manager);
    	            case 2 -> viewSentMessages(manager);
    	            case 3 -> sendMessage(manager);
    	            case 0 -> {
    	                return;
    	            }
    	            default -> System.out.println("Invalid option");
    	        }
    	    }
    	}

    // VIEW STUDENTS
    private void viewStudents() {

        List<Student> students =
                managerService.getStudents();

        if (students.isEmpty()) {

            System.out.println(
                    "No students found"
            );

            return;
        }

        System.out.println(
                "\n===== STUDENTS ====="
        );

        students.forEach(System.out::println);
    }

    // VIEW TEACHERS
    private void viewTeachers() {

        List<Teacher> teachers =
                managerService.getTeachers();

        if (teachers.isEmpty()) {

            System.out.println(
                    "No teachers found"
            );

            return;
        }

        System.out.println(
                "\n===== TEACHERS ====="
        );

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
    
    
    private void addCourseForRegistration() {

        System.out.print("Course ID: ");
        String id = sc.nextLine();

        System.out.print("Course name: ");
        String name = sc.nextLine();

        System.out.print("Description: ");
        String description = sc.nextLine();

        System.out.println("Select major:");
        System.out.println("1. INFORMATION_SYSTEMS");
        System.out.println("2. COMPUTER_SCIENCE");
        System.out.println("3. CYBER_SECURITY");
        System.out.println("4. SOFTWARE_ENGINEERING");
        
        System.out.print("Option: ");
        int majorOption = readInt();

        Major major;

        switch (majorOption) {

            case 1:
                major = Major.INFORMATION_SYSTEMS;
                break;

            case 2:
                major = Major.COMPUTER_SCIENCE;
                break;

            case 3:
                major = Major.CYBER_SECURITY;
                break;

            case 4:
                major = Major.SOFTWARE_ENGINEERING;
                break;

            default:
                System.out.println("Invalid major");
                return;
        }
        
        System.out.print("Credits: ");
        int credits = readInt();

        

            courseService.addCourseForRegistration(
                    id,
                    name,
                    description,
                    major,
                    credits
            );

            System.out.println(
                    "Course added successfully"
            );

        
    }
    // ASSIGN COURSE

    private void assignCourseToTeacher() {

        List<Course> courses = courseService.getAllCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses available");
            return;
        }

        System.out.println("\n===== COURSES =====");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i));
        }

        int courseChoice = readInt("Choose course: ");

        if (courseChoice < 1 || courseChoice > courses.size()) {
            System.out.println("Invalid course choice");
            return;
        }

        Course course = courses.get(courseChoice - 1);

        List<Teacher> teachers =
                managerService.getTeachers();

        if (teachers.isEmpty()) {
            System.out.println("No teachers available");
            return;
        }

        System.out.println("\n===== TEACHERS =====");
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println((i + 1) + ". " + teachers.get(i));
        }

        int teacherChoice = readInt("Choose teacher: ");

        if (teacherChoice < 1 || teacherChoice > teachers.size()) {
            System.out.println("Invalid teacher choice");
            return;
        }

        Teacher teacher = teachers.get(teacherChoice - 1);

        managerService.assignCourseToTeacher(
                course,
                teacher
        );

        System.out.println("Course assigned successfully");
    }
      
    private int readInt(String message) {
        System.out.print(message);
        return readInt();
    }
    
   

    // APPROVE REGISTRATION
    private void approveRegistration() {

        List<Student> students = managerService.getStudents();

        if (students.isEmpty()) {
            System.out.println("No students found");
            return;
        }

        System.out.println("\n===== STUDENTS =====");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }

        int studentChoice = readInt("Choose student: ");

        if (studentChoice < 1 || studentChoice > students.size()) {
            System.out.println("Invalid student choice");
            return;
        }

        Student student = students.get(studentChoice - 1);

        List<Course> courses = courseService.getAllCourses();

        if (courses.isEmpty()) {
            System.out.println("No courses available");
            return;
        }

        System.out.println("\n===== COURSES =====");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println((i + 1) + ". " + courses.get(i));
        }

        int courseChoice = readInt("Choose course: ");

        if (courseChoice < 1 || courseChoice > courses.size()) {
            System.out.println("Invalid course choice");
            return;
        }

        Course course = courses.get(courseChoice - 1);

        try {
            managerService.approveRegistration(student, course);
            System.out.println("Registration approved successfully");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // VIEW STUDENTS SORTED BY GPA

    private void viewStudentsSortedByGPA() {

        System.out.println(
                "\n===== STUDENTS BY GPA ====="
        );

        List<Student> students =
                managerService
                        .getStudentsSortedByGPA();

        students.forEach(System.out::println);
    }
    // VIEW TEACHERS ALPHABETICALLY

    private void viewTeachersAlphabetically() {

        System.out.println(
                "\n===== TEACHERS A-Z ====="
        );

        List<Teacher> teachers =
                managerService
                        .getTeachersAlphabetically();

        teachers.forEach(System.out::println);
    }

    private void createReport() {

        System.out.println(
                "\n===== REPORT ====="
        );

        System.out.println(
                managerService.generateReport()
        );
    }
    private void viewNews() {

        List<News> newsList =
                managerService.getNews();

        if (newsList.isEmpty()) {

            System.out.println(
                    "No news available"
            );

            return;
        }

        System.out.println(
                "\n===== NEWS ====="
        );

        newsList.forEach(System.out::println);
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

    // PUBLISH NEWS

    private void publishNews(Manager manager) {

        System.out.print("Enter news title: ");
        String title = sc.nextLine();

        System.out.print("Enter news content: ");
        String content = sc.nextLine();

        if (title.isBlank() || content.isBlank()) {
            System.out.println("News cannot be empty");
            return;
        }

        managerService.publishNews(
                manager,
                title,
                content
        );

        System.out.println("News published successfully");
    }
    
    private void sendMessage(Manager manager) {

        System.out.print("Receiver email: ");
        String email = sc.nextLine();

        System.out.print("Message: ");
        String text = sc.nextLine();

        messageService.sendMessage(
                manager,
                email,
                text
        );

        System.out.println("Message sent successfully");
    }
    
    private void viewInbox(Manager manager) {

        List<Message> messages =
                messageService.getInbox(manager);

        if (messages.isEmpty()) {
            System.out.println("Inbox is empty");
            return;
        }

        System.out.println("\n===== INBOX =====");
        messages.forEach(System.out::println);
    }
    
    private void viewSentMessages(Manager manager) {

        List<Message> messages =
                messageService.getSentMessages(manager);

        if (messages.isEmpty()) {
            System.out.println("No sent messages");
            return;
        }

        System.out.println("\n===== SENT MESSAGES =====");
        messages.forEach(System.out::println);
    }
    
}