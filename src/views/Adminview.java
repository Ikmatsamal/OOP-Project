package views;
import models.user.User;

import java.util.Scanner;

import models.user.*;
import enums.*;
import patterns.singleton.DatabaseSingleton;
import utils.Logger;

public class Adminview {

    private final Scanner sc = new Scanner(System.in);

    public void show(Admin admin) {

        while (true) {
        	System.out.println("1. View students");
        	System.out.println("2. View teachers");
        	System.out.println("3. View managers");
        	System.out.println("4. Add user");
        	System.out.println("5. Delete user");
        	System.out.println("6. Update user");
        	System.out.println("7. View courses");
        	System.out.println("0. Logout");

        	int choice = readInt("Choose: ");

            switch (choice) {
            case 1 : viewStudents(); break;
            case 2 : viewTeachers(); break;
            case 3 : viewManagers(); break;
            case 4 : addUser(); break;
            case 5 : deleteUser(); break;
            case 6 : updateUser(); break;
            case 7 : viewCourses(); break;
            case 0 : {
                Logger.info("Logout: " + admin.getEmail());
                return;
            }
            default : System.out.println("Invalid option");
        }
        }
    }

    private void viewStudents() {
        DatabaseSingleton.getInstance().getUsers().stream()
                .filter(user -> user instanceof Student)
                .forEach(System.out::println);
    }

    private void viewTeachers() {
        DatabaseSingleton.getInstance().getUsers().stream()
                .filter(user -> user instanceof Teacher)
                .forEach(System.out::println);
    }

    private void viewManagers() {
        DatabaseSingleton.getInstance().getUsers().stream()
                .filter(user -> user instanceof Manager)
                .forEach(System.out::println);
    }
    
    private void addUser() {
        System.out.println("What type of user do you want to add?");
        System.out.println("1. Student");
        System.out.println("2. Teacher");
        System.out.println("3. Manager");
        System.out.println("0. Back");

        int option = readInt("Choose: ");

        switch (option) {
            case 1 -> addStudent();
            case 2 -> addTeacher();
            case 3 -> addManager();
            case 0 -> System.out.println("Back to admin menu.");
            default -> System.out.println("Invalid option.");
        }
    }

    private void viewCourses() {
        DatabaseSingleton db = DatabaseSingleton.getInstance();

        if (db.getCourses().isEmpty()) {
            System.out.println("No courses available");
        } else {
            db.getCourses().forEach(System.out::println);
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

        String major = "";

        switch (majorOption) {

            case 1:
                major = "Information Systems";
                break;

            case 2:
                major = "Computer Science";
                break;

            case 3:
                major = "Cyber Security";
                break;

            case 4:
                major = "Software Engineering";
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

        Student student = new Student(
                id,
                name,
                email,
                password,
                major,
                year
        );

        DatabaseSingleton.getInstance().addUser(student);

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

        Teacher teacher = new Teacher(id, name, email, password, department);
        DatabaseSingleton.getInstance().addUser(teacher);

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

        Manager manager = new Manager(id, name, email, password,  type);
        DatabaseSingleton.getInstance().addUser(manager);

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

        User user = DatabaseSingleton.getInstance().findUserById(id);

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

        DatabaseSingleton.getInstance().deleteUser(id);
        System.out.println("User deleted successfully.");
    }

    private void updateUser() {
        System.out.print("Enter user ID to update: ");
        int id = Integer.parseInt(sc.nextLine());

        User user = DatabaseSingleton.getInstance().findUserById(id);

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
                user.setName(sc.nextLine());
                break;

            case 2:
                System.out.print("Enter new email: ");
                user.setEmail(sc.nextLine());
                break;


            case 3:
                System.out.print("Enter new password: ");
                user.setPassword(sc.nextLine());
                break;
        }

        System.out.println("User updated successfully.");
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