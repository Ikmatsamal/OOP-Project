package system;

import models.user.User;
import patterns.singleton.DatabaseSingleton;
import services.AuthService;
import utils.Logger;

import java.util.Optional;
import java.util.Scanner;

public class UniversitySystem {

    private final AuthService authService = new AuthService();
    private final Scanner sc = new Scanner(System.in);

    private User currentUser;

    public void start() {
        Logger.info("System started");

        while (currentUser == null) {
            login();
        }

        showMenu();
    }

    private void login() {
        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        Optional<User> user = authService.login(email, password);

        if (user.isPresent()) {
            currentUser = user.get();
        } else {
            System.out.println("Invalid credentials\n");
        }
    }

    private void showMenu() {
        while (true) {

            System.out.println("\n=== MENU ===");

            switch (currentUser.getRole()) {

                case "ADMIN" -> adminMenu();
                case "STUDENT" -> studentMenu();

                default -> {
                    System.out.println("Unknown role");
                    return;
                }
            }
        }
    }

    // ---------- ADMIN MENU ----------
    private void adminMenu() {
        System.out.println("1. View all users");
        System.out.println("2. View courses");
        System.out.println("3. Logout");

        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1 -> viewAllUsers();
            case 2 -> viewCourses();
            case 3 -> logout();
            default -> System.out.println("Invalid option");
        }
    }

    // ---------- STUDENT MENU ----------
    private void studentMenu() {
        System.out.println("1. View courses");
        System.out.println("2. Logout");

        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1 -> viewCourses();
            case 2 -> logout();
            default -> System.out.println("Invalid option");
        }
    }

    // ---------- METHODS ----------
    private void viewAllUsers() {
        DatabaseSingleton db = DatabaseSingleton.getInstance();
        db.getUsers().forEach(System.out::println);
    }

    private void viewCourses() {
        DatabaseSingleton db = DatabaseSingleton.getInstance();

        if (db.getCourses().isEmpty()) {
            System.out.println("No courses available");
        } else {
            db.getCourses().forEach(System.out::println);
        }
    }

    private void logout() {
        Logger.info("Logout: " + currentUser.getEmail());
        currentUser = null;
        start();
    }
}
