package main;
import models.system.UniversitySystem;

import models.user.Admin;
import patterns.singleton.DatabaseSingleton;
import system.UniversitySystem;

public class SystemMain {
    public static void main(String[] args) {

        DatabaseSingleton db = DatabaseSingleton.getInstance();

        Admin admin = new Admin(
                1,
                "Admin",
                "admin@gmail.com",
                "+77000000000",
                "123",
                "ADMIN"
        );

        db.addUser(admin);

        UniversitySystem system = new UniversitySystem();
        system.start();
    }
}
