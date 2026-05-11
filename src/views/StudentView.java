package views;

import java.util.Scanner;

import models.user.Student;
import patterns.singleton.DatabaseSingleton;
import utils.Logger;

public class StudentView {

    private final Scanner sc = new Scanner(System.in);

    public void show(Student student) {

        while (true) {
            System.out.println("\n=== STUDENT MENU ===");
            System.out.println("1. View courses");
            System.out.println("0. Logout");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> viewCourses();
                case 0 -> {
                    Logger.info("Logout: " + student.getEmail());
                    return;
                }
                default -> System.out.println("Invalid option");
            }
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
}