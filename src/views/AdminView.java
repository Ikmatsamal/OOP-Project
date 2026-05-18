package views;
import models.user.User;
import models.academic.Course;
import models.system.Message;
import java.util.List;
import services.AdminService;
import services.MessageService;

import java.util.Scanner;
import enums.Major;

import models.user.*;
import enums.*;
import utils.Logger;

public class AdminView {

    private final Scanner sc = new Scanner(System.in);
    private final AdminService adminService = new AdminService();
    private final MessageService messageService =
            new MessageService();

    public void show(Admin admin) {

        while (true) {

            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Users");
            System.out.println("2. Courses");
            System.out.println("3. Messages");
            System.out.println("4. View logs");
            System.out.println("0. Logout");

            int choice = readInt("Choose: ");

            switch (choice) {

                case 1 -> usersMenu();

                case 2 -> coursesMenu();

                case 3 -> messagesMenu(admin);
                case 4 -> viewLogs();

                case 0 -> {
                    Logger.info("Logout: " + admin.getEmail());
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
            System.out.println("3. View managers");
            System.out.println("4. Add user");
            System.out.println("5. Delete user");
            System.out.println("6. Update user");
            System.out.println("0. Back");

            int choice = readInt("Choose: ");

            switch (choice) {

                case 1 -> viewStudents();

                case 2 -> viewTeachers();

                case 3 -> viewManagers();

                case 4 -> addUser();

                case 5 -> deleteUser();

                case 6 -> updateUser();

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
            System.out.println("0. Back");

            int choice = readInt("Choose: ");

            switch (choice) {

                case 1 -> viewCourses();

                case 0 -> {
                    return;
                }

                default -> System.out.println("Invalid option");
            }
        }
    }
    
    private void messagesMenu(Admin admin) {

        while (true) {

            System.out.println("\n===== MESSAGES MENU =====");
            System.out.println("1. View inbox");
            System.out.println("2. View sent messages");
            System.out.println("3. Send message");
            System.out.println("0. Back");

            int choice = readInt("Choose: ");

            switch (choice) {

                case 1 -> viewInbox(admin);

                case 2 -> viewSentMessages(admin);

                case 3 -> sendMessage(admin);

                case 0 -> {
                    return;
                }

                default -> System.out.println("Invalid option");
            }
        }
    }

    private void viewStudents() {

        List<User> students = adminService.getStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        students.forEach(System.out::println);
    }
    
    private void viewTeachers() {

        List<User> teachers = adminService.getTeachers();

        if (teachers.isEmpty()) {
            System.out.println("No teachers found.");
            return;
        }

        teachers.forEach(System.out::println);
    }

    private void viewManagers() {

        List<User> managers = adminService.getManagers();

        if (managers.isEmpty()) {
            System.out.println("No managers found.");
            return;
        }

        managers.forEach(System.out::println);
    }
    
    
    private void addUser() {
        System.out.println("What type of user do you want to add?");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.println("3. Manager");
        System.out.println("4. Logs");
        System.out.println("0. Back");

        int option = readInt("Choose: ");

        switch (option) {
            case 1 -> addStudent();
            case 2 -> addTeacher();
            case 3 -> addManager();
            case 4 -> viewLogs();
            case 0 -> System.out.println("Back to admin menu.");
            default -> System.out.println("Invalid option.");
        }
    }

   
    
    private void addStudent() {

        int id = readInt("ID: ");

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.println("Select major:");
        System.out.println("1. Information Systems");
        System.out.println("2. Computer Science");
        System.out.println("3. Cyber Security");
        System.out.println("4. Software Engineering");
        System.out.print("Option: ");

        int majorOption = Integer.parseInt(sc.nextLine());

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
            System.out.println("Invalid major option.");
            return;
    }

        System.out.println("Select study year:");
        System.out.println("1. First Year");
        System.out.println("2. Second Year");
        System.out.println("3. Third Year");
        System.out.println("4. Fourth Year");
        System.out.print("Option: ");

        int yearOption = Integer.parseInt(sc.nextLine());

        int year = 0;

        switch (yearOption) {

            case 1:
                year = 1;
                break;

            case 2:
                year = 2;
                break;

            case 3:
                year = 3;
                break;

            case 4:
                year = 4;
                break;

            default:
                System.out.println("Invalid year option.");
                return;
        }

      
        adminService.addStudent(
                id,
                name,
                email,
                password,
                major,
                year
        );

        System.out.println("Student added successfully.");
    }
    
    
    private void addTeacher() {
        int id = readInt("ID: ");

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();


        System.out.print("Password: ");
        String password = sc.nextLine();


        System.out.println("Select department:");
        System.out.println("1. FIT");
        System.out.println("2. BS");
        System.out.println("3. KMA");
        System.out.println("4. ISE");
        System.out.print("Option: ");

        int depOption = readInt("Choose: ");

        String department;

        switch (depOption) {
            case 1:
                department = "FIT";
                break;
            case 2:
                department = "BS";
                break;
            case 3:
                department = "KMA";
                break;
            case 4:
                department = "ISE";
                break;
            default:
                System.out.println("Invalid department.");
                return;
        }

        System.out.println("Select teacher title:");
        System.out.println("1. Tutor");
        System.out.println("2. Lecturer");
        System.out.println("3. Senior Lecturer");
        System.out.println("4. Professor");

        int choice = readInt("Choose: ");

        TeacherTitle title = switch (choice) {
            case 1 -> TeacherTitle.TUTOR;
            case 2 -> TeacherTitle.LECTURER;
            case 3 -> TeacherTitle.SENIOR_LECTURER;
            case 4 -> TeacherTitle.PROFESSOR;
            default -> TeacherTitle.LECTURER;
        };

        adminService.addTeacher(
                id,
                name,
                email,
                password,
                department,
                title
        );

        System.out.println("Teacher added successfully.");
    }
    
    
    private void addManager() {
        int id = readInt("ID: ");

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();


        System.out.print("Password: ");
        String password = sc.nextLine();


        System.out.println("Manager type:");
        System.out.println("1. OR");
        System.out.println("2. DEPARTMENT");
        System.out.print("Choose: ");
        int typeChoice = Integer.parseInt(sc.nextLine());

        ManagerType type = switch (typeChoice) {
            case 1 -> ManagerType.OR;
            case 2 -> ManagerType.DEPARTMENT;
            default -> ManagerType.DEPARTMENT;
        };

        adminService.addManager(
                id,
                name,
                email,
                password,
                type
        );

        System.out.println("Manager added successfully.");
    }
    
    private void deleteUser() {
        System.out.println("What type of user do you want to delete?");
        System.out.println("0. Back to Admin Menu");
        System.out.println("1. Teacher");
        System.out.println("2. Student");
        System.out.println("3. Manager");
        System.out.print("Option: ");

        int option = Integer.parseInt(sc.nextLine());

        if (option == 0) {
            return;
        }

        if (option < 1 || option > 3) {
            System.out.println("Invalid option.");
            return;
        }

        System.out.print("Enter user ID: ");
        int id = Integer.parseInt(sc.nextLine());

        User user = adminService.findUserById(id);

        if (user == null) {
            System.out.println("Error: USER NOT FOUND!");
            return;
        }

        System.out.println("User found:");
        System.out.println(user);

        System.out.print("Are you sure you want to delete this user? (yes/no): ");
        String answer = sc.nextLine();

        if (!answer.equalsIgnoreCase("yes")) {
            System.out.println("Delete cancelled.");
            return;
        }

        adminService.deleteUser(id);
        System.out.println("User deleted successfully.");
    }

    private void updateUser() {
        System.out.print("Enter user ID to update: ");
        int id = Integer.parseInt(sc.nextLine());

        User user = adminService.findUserById(id);

        if (user == null) {
            System.out.println("Error: USER NOT FOUND!");
            return;
        }

        System.out.println("User found:");
        System.out.println(user);

        System.out.println("What do you want to update?");
        System.out.println("0. Cancel");
        System.out.println("1. Name");
        System.out.println("2. Email");
        System.out.println("3. Password");
        System.out.print("Option: ");

        int choice = Integer.parseInt(sc.nextLine());

        if (choice == 0) {
            System.out.println("Update cancelled.");
            return;
        }

        if (choice < 1 || choice > 3) {
            System.out.println("Invalid option.");
            return;
        }

        switch (choice) {

        case 1:
            System.out.print("Enter new name: ");

            adminService.updateName(
                    user,
                    sc.nextLine()
            );

            break;

        case 2:
            System.out.print("Enter new email: ");

            adminService.updateEmail(
                    user,
                    sc.nextLine()
            );

            break;

        case 3:
            System.out.print("Enter new password: ");

            adminService.updatePassword(
                    user,
                    sc.nextLine()
            );

            break;
    }
        

        System.out.println("User updated successfully.");
    }
    
    private void viewCourses() {

        List<Course> courses =
                adminService.getCourses();

        if (courses.isEmpty()) {

            System.out.println("No courses available");

        } else {

            courses.forEach(System.out::println);
        }
    }
    
    private void sendMessage(Admin admin) {

        System.out.print("Receiver email: ");
        String email = sc.nextLine();

        System.out.print("Message: ");
        String text = sc.nextLine();

        try {

        	messageService.sendMessage(
        	        admin,
        	        email,
        	        text
        	);

            System.out.println(
                    "Message sent successfully"
            );

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
    
    private void viewInbox(Admin admin) {

        List<Message> messages =
        		messageService.getInbox(admin);

        if (messages.isEmpty()) {

            System.out.println("Inbox is empty");
            return;
        }

        System.out.println("\n===== INBOX =====");

        messages.forEach(System.out::println);
    }
    
    private void viewSentMessages(Admin admin) {

        List<Message> messages =
        		messageService.getSentMessages(admin);

        if (messages.isEmpty()) {

            System.out.println("No sent messages");
            return;
        }

        System.out.println(
                "\n===== SENT MESSAGES ====="
        );

        messages.forEach(System.out::println);
    }
    
    private void viewLogs() {

        List<String> logs = Logger.getLogs();

        if (logs.isEmpty()) {
            System.out.println("No logs available.");
            return;
        }

        System.out.println("\n===== SYSTEM LOGS =====");

        logs.forEach(System.out::println);
    }
    
    private int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }
    
}