package main;

import models.user.Admin;
import patterns.singleton.DatabaseSingleton;
import system.UniversitySystem;

public class SystemMain {
    public static void main(String[] args) {

        DatabaseSingleton db = DatabaseSingleton.getInstance();

        // тестовый пользователь
        Admin admin = new Admin(
                "1",
                "Admin",
                "Admin",
                "admin@gmail.com",
                "123"
        );

        db.addUser(admin);

        UniversitySystem system = new UniversitySystem();
        system.start();
    }
}
