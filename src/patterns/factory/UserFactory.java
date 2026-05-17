package patterns.factory;
import enums.Major;

import models.user.*;

public class UserFactory {

    public static User createUser(
            String role,
            int id,
            String name,
            String email,
            String password
    ) {

        switch (role.toUpperCase()) {

            case "STUDENT":
                return new Student(
                        id,
                        name,
                        email,
                        password,
                        enums.Major.COMPUTER_SCIENCE,
                        2
                );

            case "TEACHER":
                return new Teacher(
                        id,
                        name,
                        email,
                        password,
                        "FIT"
                        
                );

            case "MANAGER":
                return new Manager(
                        id,
                        name,
                        email,
                        password,
                        enums.ManagerType.OR
                );

            case "ADMIN":
                return new Admin(
                        id,
                        name,
                        email,
                        password
                );

            default:
                throw new IllegalArgumentException("Invalid role");
        }
    }
}