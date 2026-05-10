package patterns.factory;

import models.user.*;

public class UserFactory {

    public static User createUser(
            String role,
            int id,
            String name,
            String email,
            String phone,
            String password
    ) {

        switch (role.toUpperCase()) {

            case "STUDENT":
                return new Student(
                        id,
                        name,
                        email,
                        phone,
                        password,
                        1000 + id,
                        "Computer Science",
                        2
                );

            case "TEACHER":
                return new Teacher(
                        id,
                        name,
                        email,
                        phone,
                        password,
                        "T-" + id,
                        "FIT",
                        "Professor"
                );

            case "MANAGER":
                return new Manager(
                        id,
                        name,
                        email,
                        phone,
                        password,
                        "M-" + id,
                        "FIT",
                        "Manager",
                        enums.ManagerType.OR
                );

            case "ADMIN":
                return new Admin(
                        id,
                        name,
                        email,
                        phone,
                        password,
                        "ADMIN-" + id
                );

            default:
                throw new IllegalArgumentException("Invalid role");
        }
    }
}