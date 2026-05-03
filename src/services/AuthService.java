package services;

import models.user.User;
import patterns.singleton.DatabaseSingleton;
import utils.Logger;

import java.util.Optional;

public class AuthService {

    private final DatabaseSingleton db = DatabaseSingleton.getInstance();

    public Optional<User> login(String email, String password) {
        Optional<User> userOpt = db.getUserByEmail(email);

        if (userOpt.isEmpty()) {
            Logger.warn("User not found: " + email);
            return Optional.empty();
        }

        User user = userOpt.get();

        if (!user.getPassword().equals(password)) {
            Logger.warn("Wrong password: " + email);
            return Optional.empty();
        }

        Logger.info("Login success: " + email);
        return Optional.of(user);
    }
}
