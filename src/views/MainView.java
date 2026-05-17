package views;

import java.util.Scanner;

import models.system.UniversitySystem;
import models.user.*;

public class MainView {

    private UniversitySystem system;
    private Scanner sc;

    public MainView() {
        system = new UniversitySystem();
        sc = new Scanner(System.in);
    }

    public void start() {

        while (true) {

            System.out.println("\n===== UNIVERSITY SYSTEM =====");
            System.out.println("1. Login");
            System.out.println("0. Exit");

            System.out.print("Choose option: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1 -> login();

                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }

                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void login() {

        System.out.print("Email: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = system.loginByEmail(username, password);

        if (user == null) {
            System.out.println("Wrong username or password.");
            return;
        }

        System.out.println("Welcome, " + user.getEmail());

        if (user instanceof Admin admin) {
            new Adminview().show(admin);
        }

        else if (user instanceof Student student) {
            new StudentView().show(student);
        }

        else if (user instanceof Teacher teacher) {
            new TeacherView().show(teacher);
        }

        else if (user instanceof Manager manager) {
            new ManagerView().show(manager);
        }
    }
    
    public static void main(String[] args) {

        MainView view = new MainView();
        view.start();
    }
}