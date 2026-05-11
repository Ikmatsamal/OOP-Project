package models.system;

import java.util.Optional;
import java.util.Scanner;

import models.user.User;
import services.AuthService;
import utils.Logger;
import views.MainView;

public class UniversitySystem {

    private final AuthService authService = new AuthService();
    private final Scanner sc = new Scanner(System.in);
    
    public User loginByEmail(String email, String password) {
        return authService.login(email, password).orElse(null);
    }

    

    private User login() {
        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        Optional<User> user = authService.login(email, password);

        if (user.isPresent()) {
            return user.get();
        }

        System.out.println("Invalid credentials\n");
        return null;
    }
}